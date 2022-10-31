package org.example;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class PodCastTest {

    PodCastDAOImp podCastdaoimp=new PodCastDAOImp();
    List<Podcast> podcastList=podCastdaoimp.getAll();

    public PodCastTest() throws SQLException, ClassNotFoundException {
    }
    @Test
    void noOfPodCasts(){
        assertEquals(3,podcastList.size());
    }

    @Test
    void podCastName() throws SQLException, ClassNotFoundException {
        List<Podcast> pl=podCastdaoimp.searchByPodcastName(podcastList,"Bahubali");
        assertEquals(1,pl.size());
    }

    @Test
    void podCastByArtist() throws SQLException, ClassNotFoundException {
        List<Podcast> pl=podCastdaoimp.searchByArtist(podcastList,"SriRam");
        assertEquals(1,pl.size());
    }

    @Test
    void podCastByGenre() throws SQLException, ClassNotFoundException {
        List<Podcast> pl=podCastdaoimp.searchByGenre(podcastList,"Intro");
        assertEquals(1,pl.size());
    }

}
