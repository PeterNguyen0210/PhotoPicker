package com.rain.library.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Describe :相册
 * Created by Rain on 17-4-28.
 */
public class PhotoDirectory {

    private String id;
    private String coverPath;
    private String name;
    private String dirPath;
    private long dateAdded;
    private ArrayList<Photo> photos = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhotoDirectory)) return false;

        PhotoDirectory directory = (PhotoDirectory) o;

        if (!id.equals(directory.id)) return false;
        return name.equals(directory.name);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    public List<String> getPhotoPaths() {
        List<String> paths = new ArrayList<>(photos.size());
        for (Photo photo : photos) {
            paths.add(photo.getOriginalImagePath());
        }
        return paths;
    }

    public void addPhoto(int id, String path) {
        photos.add(new Photo(id, path));
    }

    public void addPhoto(int originalImageId, String originalImagePath, long originalImageSize,String thumbnailsImagePath) {
        photos.add(new Photo(originalImageId, originalImagePath, originalImageSize,thumbnailsImagePath));
    }

}
