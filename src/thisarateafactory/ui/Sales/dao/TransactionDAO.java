/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Mihitha Hansani
 */
public class TransactionDAO implements DAO
{
    private int transactionId;
    private Date date;
    private double amount;
    private String type;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @Override
    public void insert(Connection connection) throws SQLException
    {
        String insertQuery = "INSERT INTO transactions (RefID, Type, Date, Amount) " +
              " VALUES ( ?, ?, ?, ?)";
        
        int index = 1;
        PreparedStatement preparedStatement = connection.prepareStatement( insertQuery );

        preparedStatement.setInt(index++, 10);
        preparedStatement.setString( index++, type );
        java.sql.Date timestamp = new java.sql.Date( date.getTime());
        preparedStatement.setDate(index++, timestamp );
        preparedStatement.setDouble( index++, amount );

        preparedStatement.execute();
    }

    @Override
    public void update(Connection connection) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Connection connection) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void load(Connection connection, ResultSet resultSet) throws SQLException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
