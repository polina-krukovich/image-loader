package com.epam.gcp.service;

import com.epam.gcp.dao.ImageDao;
import com.epam.gcp.dao.exception.DaoException;
import com.epam.gcp.entity.Image;
import com.epam.gcp.service.exception.ServiceException;
import com.google.cloud.storage.BlobId;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class ImageService {

    private static class SingletonHolder {
        private static final ImageService instance = new ImageService();
    }

    private final Logger logger = LogManager.getLogger(getClass());

    private ImageService() {}

    public static ImageService getInstance() {
        return SingletonHolder.instance;
    }

    public String create(HttpServletRequest request) throws ServiceException {
        ImageDao imageDao = ImageDao.getInstance();
        String url = null;
        try {
            FileItemIterator iter = new ServletFileUpload().getItemIterator(request);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                if (!item.isFormField()) {
                    url = imageDao.create(item);
                }
            }
        } catch (FileUploadException | DaoException | IOException e) {
            logger.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        return url;
    }

    public Image[] list() {
        ImageDao imageDao = ImageDao.getInstance();
        List<Image> imageList = imageDao.list();
        Image[] images = new Image[imageList.size()];
        for (int i = 0; i < imageList.size(); i++) {
            images[i] = imageList.get(i);
        }
        return images;
    }

    public void delete(BlobId blobId) {
        ImageDao imageDao = ImageDao.getInstance();
        imageDao.delete(blobId);
    }
}
