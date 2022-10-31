package org.example;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
void userTest() throws SQLException, ClassNotFoundException {
        SongDAOImp songDAOImp=new SongDAOImp();
        PodCastDAO podcastimp=new PodCastDAOImp();
        List<Song> songList=songDAOImp.getAll();
        List<Podcast> podcastList=podcastimp.getAll();
        assertEquals(7,songList.size());
        assertEquals(3,podcastList.size());
    }



}
