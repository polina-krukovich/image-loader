package com.epam.gcp.entity;

import com.google.cloud.storage.BlobId;

import java.util.Objects;

public class Image {
    private String creationDate;
    private long size;
    private String url;
    private BlobId blobId;

    public Image(String creationDate, long size, String url, BlobId blobId) {
        this.creationDate = creationDate;
        this.size = size;
        this.url = url;
        this.blobId = blobId;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public long getSize() {
        return size;
    }

    public String getUrl() {
        return url;
    }

    public BlobId getBlobId() {
        return blobId;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setBlobId(BlobId blobId) {
        this.blobId = blobId;
    }

    @Override
    public String toString() {
        return String.format("%s {creationDate: %s, size: %d, url: %s, blobId: %s}",
                getClass().getSimpleName(), creationDate, size, url, blobId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (null == o || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return Long.compare(image.size, size) == 0 &&
                creationDate.equals(image.creationDate) &&
                url.equals(image.url) &&
                blobId.equals(image.blobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate, size, url, blobId);
    }
}

