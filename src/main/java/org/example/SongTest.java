package org.example;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SongTest {

    SongDAOImp songDAOImp=new SongDAOImp();
    List<Song> songList=songDAOImp.getAll();

    public SongTest() throws SQLException, ClassNotFoundException {
    }


    @Test
    void noOfSongs() throws SQLException, ClassNotFoundException {

        assertEquals(7,songList.size());
    }

    @Test
    void songName() throws SQLException, ClassNotFoundException {
        List<Song> sl=songDAOImp.searchBySongName(songList,"Awaara");
        assertEquals(1,sl.size());
    }
    @Test
    void artistName() throws SQLException, ClassNotFoundException {
        List<Song> sl=songDAOImp.searchByArtist(songList,"SriRam");
        assertEquals(2,sl.size());
    }
    @Test
    void albumName() throws SQLException, ClassNotFoundException {
        List<Song> sl=songDAOImp.searchByAlbum(songList,"MoneyHeist");
        assertEquals(2,sl.size());
    }

}
