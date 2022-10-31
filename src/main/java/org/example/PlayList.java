package org.example;

public class PlayList {
    private int playListID;
    private String audioType;
    private String audioID;

    public PlayList(int playListID, String audioType, String audioID) {
        this.playListID = playListID;
        this.audioType = audioType;
        this.audioID = audioID;
    }
    public PlayList(){

    }

    public int getPlayListID() {
        return playListID;
    }

    public void setPlayListID(int playListID) {
        this.playListID = playListID;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    public String getAudioID() {
        return audioID;
    }

    public void setAudioID(String audioID) {
        this.audioID = audioID;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "playListID=" + playListID +
                ", audioType='" + audioType + '\'' +
                ", audioID=" + audioID +
                '}';
    }
}
