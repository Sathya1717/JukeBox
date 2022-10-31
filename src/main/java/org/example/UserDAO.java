package org.example;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO extends DAO<User> {

    String login(List<Song> songList, List<Podcast> podcastList);

    User getUser(int userID) throws SQLException, ClassNotFoundException;


}