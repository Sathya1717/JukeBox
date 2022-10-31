package org.example;

public class PlayListsTable {

        private int playListID;
        private String playListName;
        private String userName;

        public PlayListsTable(int playListID,String playListName,String userName){
            this.playListID=playListID;
            this.playListName=playListName;
            this.userName=userName;

        }

        public PlayListsTable(){

        }

        public int getPlayListID() {
            return playListID;
        }

        public void setPlayListID(int playListID) {
            this.playListID = playListID;
        }

        public String getPlayListName() {
            return playListName;
        }

        public void setPlayListName(String playListName) {
            this.playListName = playListName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "playListsTable{" +
                    "playListID=" + playListID +
                    ", playListName='" + playListName + '\'' +
                    ", userName='" + userName + '\'' +
                    '}';
        }

}
