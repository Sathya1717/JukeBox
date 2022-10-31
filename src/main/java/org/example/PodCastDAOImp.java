package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PodCastDAOImp implements PodCastDAO{
    private String podCastID=" ";
    private String podCastName;
    private String artist;
    private String podCastType;
    private String genre;
    private String publishedDate;
    private String location;

    Connection con=null;
    Statement stat=null;
    ResultSet rs=null;
    PreparedStatement prep=null;
    String query="";
    User u=new User();
    Scanner sc=new Scanner(System.in);

    String pod="P";
    int num=0;
    @Override
    public void insert() throws SQLException, ClassNotFoundException {
        con=u.get_connection();
        stat=con.createStatement();
        rs=stat.executeQuery("select podCastID from podcast");
        while (rs.next()){
            podCastID=rs.getString(1);
        }
        if(podCastID.equals("")){
            podCastID=pod.concat(String.valueOf(num));
        }
        else
        {
            num=num+1;
            podCastID=pod.concat(String.valueOf(num));
        }
        System.out.println("Enter the PodCastName:");
        podCastName=sc.next();
        System.out.println("Enter the Artist:");
        artist=sc.next();
        System.out.println("Enter the PodCast Type:");
        podCastType=sc.next();
        System.out.println("Enter the genre:");
        genre= sc.next();
        System.out.println("Enter the Published Date:");
        publishedDate= sc.next();
        System.out.println("Enter the Location:");
        location= sc.next();


        query="insert into podcast values(?,?,?,?,?,?,?)";
        prep=con.prepareStatement(query);
        prep.setString(1,podCastID);
        prep.setString(2,podCastName);
        prep.setString(3,artist);
        prep.setString(4,podCastType);
        prep.setString(5,genre);
        prep.setDate(6, Date.valueOf(publishedDate));
        prep.setString(7,location);
        prep.executeUpdate();
        con.close();

    }

    @Override
    public List<Podcast> getAll() throws SQLException, ClassNotFoundException {
        List<Podcast> podCastList=new ArrayList<>();
        Podcast podcast=null;
        con=u.get_connection();

        query= "Select * from podcast";
        stat=con.createStatement();
        rs=stat.executeQuery(query);

        while(rs.next())
        {
            podCastID=rs.getString(1);
            podCastName=rs.getString(2);
            artist=rs.getString(3);
            podCastType=rs.getString(4);
            genre=rs.getString(5);
            publishedDate=rs.getString(6);
            location=rs.getString(7);

            podcast=new Podcast(podCastID,podCastName,artist,podCastType,genre,publishedDate,location);
            podCastList.add(podcast);
        }

        return podCastList;
    }
    String str;
    @Override
    public List<Podcast> searchByPodcastName(List<Podcast> podcastList, String str) throws SQLException, ClassNotFoundException {
        if(podcastList.contains(str)){
            System.out.println("Song exist");
        }
        else{
            System.out.println("Podcast do not exist");
        }
        List<Podcast> searchbyPodByName=null;

        searchbyPodByName=podcastList.stream().filter(s->s.getPodCastName().equalsIgnoreCase(str)).collect(Collectors.toList());

        for(Podcast p:searchbyPodByName){
            System.out.println(p);
        }
        return searchbyPodByName;
    }

    @Override
    public List<Podcast> sortByPodcastName(List<Podcast> podcastList) throws SQLException, ClassNotFoundException {

        List<Podcast> sortPodByName=null;
        System.out.println("Sorting by PodCast Name");
        sortPodByName=podcastList.stream().sorted((s1,s2)->s1.getPodCastName().compareToIgnoreCase(s2.getPodCastName())).collect(Collectors.toList());

        for(Podcast p:sortPodByName){
            System.out.println(p);
        }
        return sortPodByName;
    }

    @Override
    public List<Podcast> searchByPodcastType(List<Podcast> podcastList, String str) throws SQLException, ClassNotFoundException {
        if(podcastList.contains(str)){
            System.out.println("Song exist");
        }
        else{
            System.out.println("Podcast Type do not exist");
        }
        List<Podcast> podCastType=null;

        podCastType=podcastList.stream().filter(s->s.getPodCastType().equalsIgnoreCase(str)).collect(Collectors.toList());

        for(Podcast p:podCastType){
            System.out.println(p);
        }
        return podCastType;
    }

    @Override
    public List<Podcast> searchByArtist(List<Podcast> podcastList, String str) throws SQLException, ClassNotFoundException {
        if(podcastList.contains(str)){
            System.out.println("Song exist");
        }
        else{
            System.out.println("Artist do not exist");
        }
        List<Podcast> podCastArtist=null;

        podCastArtist=podcastList.stream().filter(s->s.getArtist().equalsIgnoreCase(str)).collect(Collectors.toList());

        for(Podcast p:podCastArtist){
            System.out.println(p);
        }
        return podCastArtist;
    }

    @Override
    public List<Podcast> sortByArtist(List<Podcast> podcastList) throws SQLException, ClassNotFoundException {
        List<Podcast> sortPodByArtist=null;
        System.out.println("Sorting by PodCast Artist");
        sortPodByArtist=podcastList.stream().sorted((s1,s2)->s1.getArtist().compareToIgnoreCase(s2.getArtist())).collect(Collectors.toList());

        for(Podcast p:sortPodByArtist){
            System.out.println(p);
        }
        return sortPodByArtist;

    }

    @Override
    public List<Podcast> searchByGenre(List<Podcast> podcastList, String str) throws SQLException, ClassNotFoundException {
        if(podcastList.contains(str)){
            System.out.println("Song exist");
        }
        else{
            System.out.println("Genre do not exist");
        }
        List<Podcast> podCastGenre=null;
        podCastGenre=podcastList.stream().filter(s->s.getGenre().equalsIgnoreCase(str)).collect(Collectors.toList());

        for(Podcast p:podCastGenre){
            System.out.println(p);
        }
        return podCastGenre;
    }

    @Override
    public List<Podcast> sortByGenre(List<Podcast> podcastList) throws SQLException, ClassNotFoundException {

        List<Podcast> sortPodByGenre=null;
        System.out.println("Sorting by PodCast Artist");
        sortPodByGenre=podcastList.stream().sorted((s1,s2)->s1.getGenre().compareToIgnoreCase(s2.getGenre())).collect(Collectors.toList());

        for(Podcast p:sortPodByGenre){
            System.out.println(p);
        }
        return sortPodByGenre;

    }
    int option;
    String ans="";
    String path=null;
    public void podCastJukeBox() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        PlayListImp plImp=new PlayListImp();
        PodCastDAOImp podCastDAOimp=new PodCastDAOImp();
        List<Podcast> podcastList=podCastDAOimp.getAll();
        SongDAOImp simp=new SongDAOImp();
        System.out.println("Entering into PodCast JukeBox.....");
        do{
            System.out.println("1.search By PodCastName");
            System.out.println("2.search By search By Podcast Type");
            System.out.println("3.search By Artist");
            System.out.println("4.search By genre");
            System.out.println("5.Sort By PodCastName");
            System.out.println("6.Sort By Artist");
            System.out.println("7.Sort By genre");
            System.out.println("8.PodCast PlayList");

            System.out.println("Select option From above list....");
            option= sc.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter the Podcast name:");
                    str=sc.next();
                    searchByPodcastName(podcastList,str);
                    path=playPodcast(str);
                    simp.play(path);
                    break;
                case 2:
                    System.out.println("Enter the Podcast Type(Live/Recorded):");
                    str=sc.next();
                    searchByPodcastType(podcastList,str);
                    break;
                case 3:
                    System.out.println("Enter the Artist:");
                    str=sc.next();
                    searchByArtist(podcastList,str);
                    break;
                case 4:
                    System.out.println("Enter the Genre:");
                    str=sc.next();
                    searchByGenre(podcastList,str);
                    break;
                case 5:
                    sortByPodcastName(podcastList) ;
                    break;
                case 6:
                    sortByArtist(podcastList);
                    break;
                case 7:
                    sortByGenre(podcastList);
                    break;
                case 8:
                    plImp.createJukeBoX();
                    break;

            }

            System.out.println("Do you want to continue:");
            ans= sc.next();
        }
        while (ans.equalsIgnoreCase("yes"));

    }

    public String playPodcast(String podCastName) throws SQLException, ClassNotFoundException {
        PodCastDAOImp songDAOImp=new PodCastDAOImp();
        List<Podcast> podcastList=getAll();
        String songPath=null;
        for(Podcast s:podcastList){
            if(s.getPodCastName().equalsIgnoreCase(podCastName)){
                songPath=s.getLocation();
            }
        }
        return songPath;
    }


}
