/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mihitha Hansani
 */
public interface DAO {
    public abstract void insert( Connection connection ) throws SQLException;//override this methods in DAO classes
    public abstract void update( Connection connection ) throws SQLException;
    public abstract void delete( Connection connection ) throws SQLException;
    public abstract void load( Connection connection, ResultSet resultSet ) throws SQLException;
}
