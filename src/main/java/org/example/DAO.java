package org.example;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T>{

    void insert() throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;


}
