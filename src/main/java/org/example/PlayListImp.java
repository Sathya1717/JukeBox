package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayListImp implements DAO<PlayList>{
    private int playListID;
    private String audioType;
    private String audioID;

    Connection con=null;
    Statement stat=null;
    Statement stat1=null;
    Statement stat2=null;
    ResultSet rs=null;
    ResultSet rs1=null;
    ResultSet rs2=null;
    PreparedStatement prep=null;
    String query="";
    User u=new User();
    Scanner sc=new Scanner(System.in);

    int pIdEntered;
    String songID;
    String podCastID;

    public PlayListImp() throws SQLException, ClassNotFoundException {
    }
    int option;
    String ans="";

    @Override
    public void insert() throws SQLException, ClassNotFoundException {
        List<String> list=new ArrayList<>();
        List<String> podCastIdList=new ArrayList<>();
        con= u.get_connection();
        stat= con.createStatement();
        stat1= con.createStatement();
        stat2= con.createStatement();
        rs= stat.executeQuery("select playListID from playListsTable");
        rs1=stat1.executeQuery("select  songID from Song");
        rs2=stat2.executeQuery("select  PodcastId from PodCast");

        System.out.println("Enter the PlayListID:");
        pIdEntered= sc.nextInt();
        while (rs.next()){
            playListID=rs.getInt(1);
        }
        if(pIdEntered==playListID){
            System.out.println("Enter the Audio Type (Song/PodCast)");
            audioType= sc.next();
            System.out.println("Enter the SongId or podCastID that u want to insert in PlayList");
            audioID= sc.next();
            while (rs1.next()) {
                songID = rs1.getString(1);
                list.add(songID);

            }
            while (rs2.next()) {
                podCastID = rs2.getString(1);
                podCastIdList.add(podCastID);

            }

            if(list.contains(audioID) || podCastIdList.contains(audioID)){    //****************
                query = "Insert into playList values(?,?,?)";
                prep = con.prepareStatement(query);
                prep.setInt(1, playListID);
                prep.setString(2, audioType);
                prep.setString(3, audioID);
                prep.executeUpdate();
                con.close();
                System.out.println("Added Successfully...");
            }
            else {
                System.out.println("Enter Correct SongID or PodCastID");
            }

        }
        else{
            System.out.println("PlayList Do Not Exist.....");
        }

    }

    @Override
    public List<PlayList> getAll() throws SQLException, ClassNotFoundException {
        con = u.get_connection();
        stat = con.createStatement();
        ResultSet rs = stat.executeQuery("Select * from playlist");
        List<PlayList> playList=new ArrayList<>();
        PlayList pl = null;
        while (rs.next()) {
            playListID = rs.getInt(1);
            audioType = rs.getString(2);
            audioID = rs.getString(3);
            pl = new PlayList(playListID, audioType, audioID);
            playList.add(pl);
        }
        for(PlayList p:playList){
            System.out.println(p);
        }
        return playList ;
    }

    public void createJukeBoX() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        PlayListsTableImp plTab=new PlayListsTableImp();
        PlayListImp plimp=new PlayListImp();
        UserDAOImp userdaoimp=new UserDAOImp();
        do{
            System.out.println("1.Create PlayList");
            System.out.println("2.Add Songs or Podcast or Songs&podCast to JukeBox");
            System.out.println("3.View JukeBox");
            System.out.println("4.Play JukeBox");

            System.out.println("Select option From above list....");
            option= sc.nextInt();
            switch (option){
                case 1:
                    plTab.insert();
                    break;
                case 2:
                    insert();
                    break;
                case 3:
                    plTab.getAll();
                    break;
                case 4:
                    play();
                    break;
            }
        }
        while (ans.equalsIgnoreCase("yes"));


    }

    String plName;
    int pid;

    public int selectPlayList() throws SQLException, ClassNotFoundException {
        PlayListsTableImp plTab=new PlayListsTableImp();
        List<PlayListsTable> playList =plTab.getAll();
        System.out.println("Enter PlayList Name");
        plName = sc.next();
        for (PlayListsTable p : playList) {
            if (p.getPlayListName().equalsIgnoreCase(plName)) {
                    pid = p.getPlayListID();
                    System.out.println(pid);
            }
            else{
                System.out.println("PlayList Do Not Exist");
            }
        }

        return pid;
    }


    String audioid;
    String path;
    String path1;

    public List<String> pathList() throws SQLException, LineUnavailableException, IOException, UnsupportedAudioFileException, ClassNotFoundException {
        SongDAOImp sDAOimp=new SongDAOImp();
        PodCastDAOImp pcDAOImp=new PodCastDAOImp();
        pid = selectPlayList();

        List<PlayList> list = getAll();
        List<Song> songlist = sDAOimp.getAll();
        List<Podcast> podcastlist=pcDAOImp.getAll();
        List<String> pathList = new ArrayList<>();

        for (PlayList pl : list){
            if (pl.getPlayListID() == pid) {

                audioid = pl.getAudioID();

                audioType=pl.getAudioType();
            }
            if(audioType.equalsIgnoreCase("Song")){
                for(Song s:songlist){
                    if(s.getSongID().equalsIgnoreCase(audioid)){
                        path=s.getLocation();
                        pathList.add(path);
                    }

                }
            }
            else if(audioType.equalsIgnoreCase("PodCast")){
                for(Podcast p:podcastlist){
                    if(p.getPodCastID().equalsIgnoreCase(audioid)){
                        path1=p.getLocation();
                        pathList.add(path1);
                    }

                }
            }
            else {
                System.out.println("Enter Correct Audio Type....");
            }

        }
        return pathList;
    }

    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException, ClassNotFoundException {
        List<String> list = pathList();
        for (String s : list) {
            System.out.println(s);
            String res = "";
            File file = new File(s);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            while (!res.equalsIgnoreCase("N")) {
                System.out.println("P=play,s=stop,r=reset,n=next,q=quit");
                System.out.println("Enter Your Choice");
                res = sc.next();
                res = res.toUpperCase();
                switch (res) {
                    case ("P"):
                        clip.start();
                        break;
                    case ("S"):
                        clip.stop();
                        break;
                    case ("R"):
                        clip.setMicrosecondPosition(0);
                        break;
                    case ("N"):
                        clip.close();
                        break;

                }
            }
        }
    }

}
