package org.example;

import java.sql.SQLException;
import java.util.List;

public interface SongDAO extends DAO<Song>{

    List<Song> searchBySongName(List<Song> songList, String str) throws SQLException, ClassNotFoundException;

    List<Song> sortBySongName(List<Song> songList) throws SQLException, ClassNotFoundException;

    List<Song> searchByAlbum(List<Song> songList, String str) throws SQLException, ClassNotFoundException;

    List<Song> sortbyAlbum(List<Song> songList) throws SQLException, ClassNotFoundException;

    List<Song> searchByArtist(List<Song> songList, String str) throws SQLException, ClassNotFoundException;

    List<Song> sortByArtist(List<Song> songList) throws SQLException, ClassNotFoundException;

    List<Song> searchByGenre(List<Song> songList, String str) throws SQLException, ClassNotFoundException;

    List<Song> sortByGnere(List<Song> songList) throws SQLException, ClassNotFoundException;
}
