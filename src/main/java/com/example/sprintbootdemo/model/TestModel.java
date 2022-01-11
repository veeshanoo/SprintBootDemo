package com.example.sprintbootdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int albumId;
    private String albumName;

    public TestModel() {

    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public TestModel(int albumId, String albumName) {
        this.albumId = albumId;
        this.albumName = albumName;
    }
}
