package org.example;

import java.sql.*;
import java.util.List;
import java.util.Scanner;
class User{
    private String userName;
    private String password;
    private int userId;
    private String fname;
    private String lname;
    private long phnNo;
    private String emailID;
    private String dob;
    private String nationality;


public User(String userName, String password, int userId, String fname, String lname, long phnNo, String emailID, String dob, String nationality)
{
    this.userName = userName;
    this.password = password;
    this.userId = userId;
    this.fname = fname;
    this.lname = lname;
    this.phnNo = phnNo;
    this.emailID = emailID;
    this.dob = dob;
    this.nationality = nationality;
}

    public User() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword(){
    return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public long getPhnNo() {
        return phnNo;
    }

    public void setPhnNo(long phnNo) {
        this.phnNo = phnNo;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality){
        this.nationality = nationality;
    }

    @Override
    public String toString()  {
        return "Welcome "+this.fname+"\t"+this.lname+"to jukebox Songs and Podcast";
   }


    public Connection get_connection () throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukeBox", "root", "Sathya@18");
        return cn;
    }


}