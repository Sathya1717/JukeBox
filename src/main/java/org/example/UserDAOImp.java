package org.example;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserDAOImp implements UserDAO{

    private String userName;
    private String password;
    private int userId;
    private String fname;
    private String lname;
    private long phnNo;
    private String emailID;
    private String dob;
    private String nationality;

    Connection con=null;
    Statement stat=null;
    ResultSet rs=null;
    ResultSet rs1=null;
    PreparedStatement prep=null;
    String query="";
    User u=new User();
    Scanner sc=new Scanner(System.in);

    public UserDAOImp() throws SQLException, ClassNotFoundException {
    }


    @Override
    public void insert() throws SQLException, ClassNotFoundException {
        try{
            con=u.get_connection();
            stat=con.createStatement();
            rs = stat.executeQuery("Select userID from user");

            while (rs.next()) {
                userId = rs.getInt(1);

            }

            if (userId == 0) {
                userId = 1;

            } else {
                userId = userId + 1;
            }
            System.out.println("Enter userName:");
            userName = sc.next();
            System.out.println("Set Password");
            password = sc.next();
            System.out.println("Enter Your first Name:");
            fname = sc.next();
            System.out.println("Enter Your last Name:");
            lname = sc.next();
            System.out.println("Enter Your PhoneNo:");
            phnNo = sc.nextLong();
            System.out.println("Enter Your Email:");
            emailID = sc.next();
            System.out.println("Enter the date in the given format YYYY-MM-DD");
            System.out.println("Enter Your DOB:");
            dob = sc.next();
            System.out.println("Enter Your Nationality:");
            nationality = sc.next();

            query = "Insert into user values(?,?,?,?,?,?,?,?,?)";
            prep = con.prepareStatement(query);
            prep.setString(1, userName);
            prep.setString(2, password);
            prep.setInt(3, userId);
            prep.setString(4, fname);
            prep.setString(5, lname);
            prep.setLong(6, phnNo);
            prep.setString(7, emailID);
            prep.setDate(8, Date.valueOf(dob));
            prep.setString(9, nationality);
            prep.executeUpdate();
            con.close();
            System.out.println("User Added successfully ....");
            System.out.println("Your user id is "+userId);

        }catch (InputMismatchException mismatch){
            System.out.println("Please enter correct form of data");
        }


    }

    String userMatch;
    String passwordMatch;
    int option;
    SongDAOImp songImp=new SongDAOImp();
    PodCastDAOImp podCastImp=new PodCastDAOImp();

    String ans="";

    @Override
    public String login(List<Song> songList, List<Podcast> podcastList)
    {
        try {
            con = u.get_connection();
            stat = con. createStatement();
            System.out.println("Enter the UserID:");
            userId = sc.nextInt();
            System.out.println("Enter your UserName:");
            userName = sc.next();
            System.out.println("Enter Your Password:");
            password = sc.next();
            rs = stat.executeQuery("select userName,password from user where userID=" +userId);
            while (rs.next()) {
                userMatch = rs.getString(1);
                passwordMatch = rs.getString(2);

            }

            if(userId==3 && userName.equalsIgnoreCase("Admin") && password.equalsIgnoreCase("Admin@123")){
               do{
                   System.out.println("1.Add song");
                   System.out.println("2.Add PodCast");
                   System.out.println("Select any option from above list");
                   option= sc.nextInt();
                   switch (option){
                       case 1:
                           songImp.insert();
                           break;
                       case 2:
                           podCastImp.insert();
                           break;
                   }
                   System.out.println("Do you want to insert more:");
                   ans= sc.next();

               }while (ans.equalsIgnoreCase("yes"));

            }


            if (userName.equals(userMatch) && password.equals(passwordMatch)){
                System.out.println("************************LogIn Successfull******************");
               System.out.println("**************************************************Welcome to NIIT JukeBox***************************************");
                System.out.println("**************************************************************************************************************");
                System.out.println("***************Top Songs************");
                System.out.println("SongID\tSongName\tDuration\tArtist\tGenre\tAlbum");
                for(Song s:songList){
                    System.out.println(s);
                }
                System.out.println("********************PodCast**********************");
                for(Podcast p:podcastList){
                    System.out.println(p);
                }
                System.out.println("***********************************************************");

                do {
                    System.out.println("1.\tSelect Song JukeBox");
                    System.out.println("2.\tSelect Podcast JukeBox");
                    System.out.println("3.\tExit....");
                    System.out.println("Choose the option from above list");
                    option=sc.nextInt();
                    switch (option){
                        case 1:
                            songImp.songJukeBox();
                            break;
                        case 2:
                            podCastImp.podCastJukeBox();
                            break;

                    }

                    System.out.println("Do you want to continue:");
                    ans=sc.next();

                }while (ans.equalsIgnoreCase("yes"));

            }
            else {
                System.out.println("Enter the correct logIn details.....");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userName;
    }


    @Override
    public User getUser(int userID) throws SQLException, ClassNotFoundException {

        con=u.get_connection();
        stat=con.createStatement();
        query= "Select * from user where userID=?";
        prep= con.prepareStatement(query);
        User u=null;
        if(rs.next())
        {
            userName= rs.getString(1);
            password=rs.getString(2);
            userID=rs.getInt(3);
            fname=rs.getString(4);
            lname=rs.getString(5);
            phnNo= rs.getLong(6);
            emailID=rs.getString(7);
            dob=rs.getString(8);
            nationality=rs.getString(9);
            u=new User(userName,password,userID,fname,lname,phnNo,emailID,dob,nationality);

        }

        return u;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {

        List<User> userList=new ArrayList<>();
        con=u.get_connection();
        stat=con.createStatement();
        query= "Select * from user";
        prep= con.prepareStatement(query);
        User u=null;
        while(rs.next())
        {
            userName= rs.getString(1);
            password=rs.getString(2);
            userId=rs.getInt(3);
            fname=rs.getString(4);
            lname=rs.getString(5);
            phnNo= rs.getLong(6);
            emailID=rs.getString(7);
            dob=rs.getString(8);
            nationality=rs.getString(9);
            u=new User(userName,password,userId,fname,lname,phnNo,emailID,dob,nationality);
            userList.add(u);

        }


        return  userList;
    }




}
