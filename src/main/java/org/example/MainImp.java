package org.example;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainImp {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, UnsupportedAudioFileException, LineUnavailableException, IOException {

        Scanner sc = new Scanner(System.in);

        UserDAOImp udao = new UserDAOImp();
        SongDAOImp songImp = new SongDAOImp();
        PodCastDAOImp podcastImp = new PodCastDAOImp();
        List<Song> songlist = songImp.getAll();
        List<Podcast> podcastlist = podcastImp.getAll();
        int option;
        String ans = "";

        do {
            System.out.println("1.\t\tCreate Account");
            System.out.println("2.\t\tLog in to account");
            System.out.println("*****************");
            System.out.println("Choose any option from above list");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    udao.insert();
                    break;
                case 2:
                    udao.login(songlist, podcastlist);
                    break;
            }
            System.out.println("Do you want to continue more operations");
            ans = sc.next();

        }
        while (ans.equalsIgnoreCase("Yes"));


    }
}

