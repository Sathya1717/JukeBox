package org.example;

import java.sql.*;
import java.util.Scanner;

public class Podcast {

    private String podCastID;
    private String podCastName;
    private String artist;
    private String podCastType;
    private String genre;
    private String publishedDate;
    private String location;


    public Podcast(String podCastID,String podCastName, String artist, String podCastType, String genre,String publishedDate,String location) {
        this.podCastID=podCastID;
        this.podCastName = podCastName;
        this.artist = artist;
        this.podCastType = podCastType;
        this.genre = genre;
        this.publishedDate=publishedDate;
        this.location=location;
    }
    public Podcast(){

    }

    public String getPodCastID() {
        return podCastID;
    }

    public void setPodCastID(String podCastID) {
        this.podCastID = podCastID;
    }

    public String getPodCastName() {
        return podCastName;
    }

    public void setPodCastName(String podCastName) {
        this.podCastName = podCastName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPodCastType() {
        return podCastType;
    }

    public void setPodCastType(String podCastType) {
        this.podCastType = podCastType;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "podCastID='" + podCastID + '\'' +
                ", podCastName='" + podCastName + '\'' +
                ", artist='" + artist + '\'' +
                ", podCastType='" + podCastType + '\'' +
                ", genre='" + genre + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

}
