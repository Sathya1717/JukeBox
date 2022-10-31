package org.example;

import java.sql.SQLException;
import java.util.List;

public interface PodCastDAO extends DAO<Podcast>{


    List<Podcast> searchByPodcastName(List<Podcast> podcastList, String str) throws SQLException, ClassNotFoundException;

    List<Podcast> sortByPodcastName(List<Podcast> podcastList) throws SQLException, ClassNotFoundException;

    List<Podcast> searchByPodcastType(List<Podcast> podcastList, String str) throws SQLException, ClassNotFoundException;

    List<Podcast> searchByArtist(List<Podcast> podcastList, String str) throws SQLException, ClassNotFoundException;

    List<Podcast> sortByArtist(List<Podcast> podcastList) throws SQLException, ClassNotFoundException;

    List<Podcast> searchByGenre(List<Podcast> podcastList, String str) throws SQLException, ClassNotFoundException;

    List<Podcast> sortByGenre(List<Podcast> podcastList) throws SQLException, ClassNotFoundException;
}
