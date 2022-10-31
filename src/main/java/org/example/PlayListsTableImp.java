package org.example;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayListsTableImp implements DAO<PlayListsTable> {

    private int playListID;
    private String playListName;
    private String userName;

    Connection con = null;
    Statement stat = null;
    Statement stat1 = null;
    Statement stat2 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    PreparedStatement prep = null;
    String query = "";
    User u = new User();
    Scanner sc = new Scanner(System.in);

    String matchPlayListName="";
    String matchUserName="";

    public PlayListsTableImp() throws SQLException, ClassNotFoundException {
    }

    @Override
    public void insert() throws SQLException, ClassNotFoundException {
        UserDAOImp userimp=new UserDAOImp();
        con = u.get_connection();
        stat = con.createStatement();
        stat1 = con.createStatement();
        stat2 = con.createStatement();
        rs = stat.executeQuery("select playListID,playListName from playListsTable");
        rs1 = stat1.executeQuery("select userName from user");
        while (rs.next()) {
            playListID = rs.getInt(1);
            matchPlayListName=rs.getString(2);
        }
        if (playListID == 0) {
            playListID = 1;
        }
        else {
            playListID = playListID + 1;
        }
        System.out.println("Enter the PlayList Name");
        playListName = sc.next();
        while (rs1.next()) {
            matchUserName = rs1.getString(1);
        }

        if (playListName.equalsIgnoreCase(matchPlayListName)) {
            System.out.println("PlayList Already Exist...");
        } else {
            System.out.println("Enter the userName");
            userName = sc.next();

            if (userName.equalsIgnoreCase(matchUserName)){
                query = "insert into PlayListsTable values(?,?,?)";
                prep = con.prepareStatement(query);
                prep.setInt(1, playListID);
                prep.setString(2, playListName);
                prep.setString(3, userName);
                prep.executeUpdate();
                con.close();
                System.out.println("PlayList Added Please Add Songs or PodCast To It...");
            } else {
                System.out.println("User Not Found");
            }

        }

    }

    @Override
    public List<PlayListsTable> getAll() throws SQLException, ClassNotFoundException {
        List<PlayListsTable> playList = new ArrayList<>();
        PlayListsTable list = null;

        con = u.get_connection();
        query = "Select * from PlayListsTable";
        stat = con.createStatement();
        rs = stat.executeQuery(query);
        while (rs.next()) {
            playListID = rs.getInt(1);
            playListName = rs.getString(2);
            userName = rs.getString(3);

            list = new PlayListsTable(playListID, playListName, userName);
            playList.add(list);
        }
        for (PlayListsTable p : playList) {
            System.out.println(p);
        }

        return playList;
    }


}