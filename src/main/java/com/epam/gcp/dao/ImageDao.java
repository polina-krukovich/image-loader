package com.epam.gcp.dao;

import com.epam.gcp.dao.exception.DaoException;
import com.epam.gcp.entity.Image;
import com.google.api.client.util.DateTime;
import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.*;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Storage.BlobListOption;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ImageDao {

    private static class SingletonHolder {
        private static final ImageDao instance = new ImageDao();
    }

    private static final Logger logger = LogManager.getLogger(ImageDao.class);

    private static Storage storage = null;
    static {
        try {
            storage = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(
                            "/home/polina/java/image-loader/image-loader-265208-c7e8a0654c61.json")))
                    .build().getService();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private final String BUCKET_NAME = "image-loader-265208.appspot.com";

    private ImageDao() {}

    public static ImageDao getInstance() {
        return SingletonHolder.instance;
    }

    public String create(FileItemStream item) throws DaoException {
        BlobInfo blobInfo = null;
        try {
            blobInfo = storage.create(
                    BlobInfo.newBuilder(BUCKET_NAME, item.getName())
                            .setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER)))).build(),
                    item.openStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new DaoException(e.getMessage());
        }
        return blobInfo.getMediaLink();
    }

    public List<Image> list() {
        Page<Blob> blobs = storage.list(BUCKET_NAME, BlobListOption.currentDirectory());
        Iterator<Blob> blobIterator = blobs.iterateAll().iterator();
        List<Image> images = new ArrayList<>();
        while (blobIterator.hasNext()) {
            Blob blob = blobIterator.next();
            if (!blob.isDirectory()) {
                Image image = new Image(new DateTime(blob.getCreateTime()).toStringRfc3339(), blob.getSize(),
                        blob.getMediaLink(), blob.getBlobId());
                images.add(image);
            }
        }
        return images;
    }

    public void delete(BlobId blobId) {
        storage.delete(blobId);
    }
}
