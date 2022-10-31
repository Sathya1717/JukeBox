package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SongDAOImp implements SongDAO{
    private String songID="";
    private String songName;
    private String duration;
    private String artist;
    private String genre;
    private String album;
    private String location;

    Connection con=null;
    Statement stat=null;
    ResultSet rs=null;
    PreparedStatement prep=null;
    String query="";
    User u=new User();
    Scanner sc=new Scanner(System.in);
    String sID="S";
    int num=1;

    public SongDAOImp() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void insert() throws SQLException, ClassNotFoundException{
        con=u.get_connection();
        stat=con.createStatement();
        rs=stat.executeQuery("select songID from song");
        while (rs.next()){
            songID=rs.getString(1);
        }
        if(songID.equals("")){
            songID=sID.concat(String.valueOf(num));
        }
        else{
            num=num+1;
            songID=sID.concat(String.valueOf(num));
        }

        System.out.println("Enter the songName:");
        songName=sc.next();
        System.out.println("Enter the duration:");
        duration=sc.next();
        System.out.println("Enter the artist name:");
        artist=sc.next();
        System.out.println("Enter the genre:");
        genre= sc.next();
        System.out.println("Enter the album");
        album= sc.next();
        System.out.println("Enter the Location:");
        location=sc.next();

        query="insert into song values(?,?,?,?,?,?,?)";
        prep=con.prepareStatement(query);
        prep.setString(1,songID);
        prep.setString(2,songName);
        prep.setString(3,duration);
        prep.setString(4,artist);
        prep.setString(5,genre);
        prep.setString(6,album);
        prep.setString(7,location);
        prep.executeUpdate();
        con.close();

    }

    @Override
    public List<Song> getAll() throws SQLException, ClassNotFoundException {

        List<Song> songList=new ArrayList<>();
        Song song=null;
        con=u.get_connection();

        query= "Select * from song";
        stat=con.createStatement();
        rs=stat.executeQuery(query);

        while(rs.next())
        {
            songID=rs.getString(1);
            songName=rs.getString(2);
            duration= rs.getString(3);
            artist=rs.getString(4);
            genre=rs.getString(5);
            album=rs.getString(6);
            location=rs.getString(7);
            song=new Song(songID,songName,duration,artist,genre,album,location);
            songList.add(song);
        }

        return songList;
    }

    String str;
    @Override
    public List<Song> searchBySongName(List<Song> songList, String str) throws SQLException, ClassNotFoundException {
        if(songList.contains(str)){
            System.out.println("Song exist");
        }
        else{
            System.out.println("Song do not exist");
        }

        List<Song> song=null;

        song=songList.stream().filter(s->s.getSongName().equalsIgnoreCase(str)).collect(Collectors.toList());

        for(Song s:song){
            System.out.println(s);
        }


        return song;
    }

    @Override
    public List<Song> sortBySongName(List<Song> songList) throws SQLException, ClassNotFoundException {
        List<Song> sortSongList=null;
        System.out.println("Sorting by songName");
        sortSongList=songList.stream().sorted((s1,s2)->s1.getSongName().compareToIgnoreCase(s2.getSongName())).collect(Collectors.toList());

        for(Song s:sortSongList){
            System.out.println(s);
        }
        return sortSongList;
    }


    @Override
    public List<Song> searchByAlbum(List<Song> songList,String str) throws SQLException, ClassNotFoundException {
        if(songList.contains(str)){
            System.out.println("Song exist");
        }
        else{
            System.out.println("Album do not exist");
        }
        List<Song> songByAlbum=null;

        songByAlbum=songList.stream().filter(s->s.getAlbum().equalsIgnoreCase(str)).collect(Collectors.toList());

        for(Song s:songByAlbum){
            System.out.println(s);
        }
        return songByAlbum;
    }

    @Override
    public List<Song> sortbyAlbum(List<Song> songList) throws SQLException, ClassNotFoundException {
        List<Song> sortByAlbum=null;
        System.out.println("Sorting by AlbumName");
        sortByAlbum=songList.stream().sorted((s1,s2)->s1.getAlbum().compareToIgnoreCase(s2.getAlbum())).collect(Collectors.toList());

        for(Song s:sortByAlbum){
            System.out.println(s);
        }
        return sortByAlbum;
    }

    @Override
    public List<Song> searchByArtist(List<Song> songList,String str) throws SQLException, ClassNotFoundException {
        if(songList.contains(str)){
            System.out.println("Song exist");
        }
        else{
            System.out.println("Artist do not exist");
        }
        List<Song> searchbyArtist=null;
        searchbyArtist=songList.stream().filter(s->s.getArtist().equalsIgnoreCase(str)).collect(Collectors.toList());

        for(Song s:searchbyArtist){
            System.out.println(s);
        }
        return searchbyArtist;
    }

    @Override
    public List<Song> sortByArtist(List<Song> songList) throws SQLException, ClassNotFoundException {
        List<Song> sortbyArtist=null;
        System.out.println("Sorting by Artist");
        sortbyArtist=songList.stream().sorted((s1,s2)->s1.getArtist().compareToIgnoreCase(s2.getArtist())).collect(Collectors.toList());

        for(Song s:sortbyArtist){
            System.out.println(s);
        }
        return sortbyArtist;
    }

    @Override
    public List<Song> searchByGenre(List<Song> songList,String str) throws SQLException, ClassNotFoundException {
        if(songList.contains(str)){
            System.out.println("Song exist");
        }
        else{
            System.out.println("Genre do not exist");
        }
        List<Song> searchbyGenre=null;
        searchbyGenre=songList.stream().filter(s->s.getGenre().equalsIgnoreCase(str)).collect(Collectors.toList());

        for(Song s:searchbyGenre){
            System.out.println(s);
        }
        return searchbyGenre;
    }

    @Override
    public List<Song> sortByGnere(List<Song> songList) throws SQLException, ClassNotFoundException {

        List<Song> sortbyGenre=null;
        System.out.println("Sorting by Genre");
        sortbyGenre=songList.stream().sorted((s1,s2)->s1.getGenre().compareToIgnoreCase(s2.getGenre())).collect(Collectors.toList());

        for(Song s:sortbyGenre){
            System.out.println(s);
        }
        return sortbyGenre;
    }
    int option;
    String ans="";
    String path=null;
    PlayListImp plImp=new PlayListImp();
    public void songJukeBox() throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        System.out.println("Entering into Song JukeBox.....");
        SongDAOImp songDAOImp=new SongDAOImp();
        List<Song> songList=getAll();
        do{
            System.out.println("1.search By SongName");
            System.out.println("2.search By Album");
            System.out.println("3.search By Artist");
            System.out.println("4.search By genre");
            System.out.println("5.Sort By SongName");
            System.out.println("6.Sort By Album");
            System.out.println("7.Sort By Artist");
            System.out.println("8.Sort By genre");
            System.out.println("9.Song PlayList");
            System.out.println("Select option From above list....");
            option= sc.nextInt();
            switch (option){
                case 1:
                    System.out.println("Enter the song name:");
                    str=sc.next();
                    searchBySongName(songList,str);
                    path=playSong(str);
                    play(path);
                    break;
                case 2:
                    System.out.println("Enter the Album Name");
                    str= sc.next();
                    searchByAlbum(songList,str);
                    break;
                case 3:
                    System.out.println("Enter the Artist");
                    str= sc.next();
                    searchByArtist(songList,str);
                    break;
                case 4:
                    System.out.println("Enter the Genre");
                    str= sc.next();
                    searchByGenre(songList,str);
                    break;
                case 5:
                    sortBySongName(songList);
                    break;
                case 6:
                    sortbyAlbum(songList);
                    break;
                case 7:
                    sortByArtist(songList);
                    break;
                case 8:
                    sortByGnere(songList);
                    break;
                case 9:
                    plImp.createJukeBoX();
                    break;

            }

            System.out.println("Do you want to continue:");
            ans= sc.next();
        }
        while (ans.equalsIgnoreCase("yes"));


    }

    public String playSong(String songName) throws SQLException, ClassNotFoundException {
        SongDAOImp songDAOImp=new SongDAOImp();
        List<Song> songList=getAll();
        String songPath=null;
        for(Song s:songList){
            if(s.getSongName().equalsIgnoreCase(songName)){
                songPath=s.getLocation();
            }
        }
        return songPath;
    }
    public void play(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException, SQLException, ClassNotFoundException {
        try{
            String res = "";
            File file = new File(path);
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
        catch (NullPointerException n){
            System.out.println(n);
        }

        }
}



