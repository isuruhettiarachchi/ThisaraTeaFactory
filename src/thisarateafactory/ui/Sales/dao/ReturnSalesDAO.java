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
public class ReturnSalesDAO implements DAO{
    private int lotNumber;
    private int invoiceNumber;
    private Date date;
    private double weight;
    private String description;

    public int getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(int lotNumber) {
        this.lotNumber = lotNumber;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    @Override
    public void update(Connection connection) throws SQLException{
        System.out.println("check");
        
        String updateQuery = "UPDATE returnsale SET Date = ?, Weight = ?, " +
                "Description = ? WHERE LotNo = ? and InvoiceNo = ?";

        int index = 1;
        PreparedStatement preparedStatement = connection.prepareStatement( updateQuery );
        
        java.sql.Timestamp timestamp = new java.sql.Timestamp( date.getTime());
        preparedStatement.setTimestamp( index++, timestamp );
        
        preparedStatement.setDouble( index++, weight );
        preparedStatement.setString( index++, description );
        
        preparedStatement.setInt( index++, lotNumber );
        preparedStatement.setInt( index++, invoiceNumber );
        
        preparedStatement.executeUpdate();
        //connection.close();
    }

    @Override
    public void insert(Connection connection) throws SQLException{
                String insertQuery = "INSERT INTO returnsale ( LotNo, InvoiceNo, Date, Weight, Description )"
                + " VALUES ( ?, ?, ?, ?, ? )";

        int index = 1;
        PreparedStatement preparedStatement = connection.prepareStatement( insertQuery );
        preparedStatement.setInt( index++, lotNumber );
        preparedStatement.setInt( index++, invoiceNumber );

        java.sql.Timestamp timestamp = new java.sql.Timestamp( date.getTime());
        preparedStatement.setTimestamp( index++, timestamp );
        
        preparedStatement.setDouble( index++, weight );
        preparedStatement.setString( index++, description );

        preparedStatement.execute();
        //connection.close();
    }

    @Override
    public void delete(Connection connection) throws SQLException{
        String deleteQuery = "DELETE FROM returnsale WHERE LotNo = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(deleteQuery);
        preparedStmt.setInt(1, lotNumber);

        preparedStmt.execute();
        //connection.close();
    }

    @Override
    public void load(Connection connection, ResultSet resultSet) throws SQLException {
        this.lotNumber = resultSet.getInt("LotNo" );
        this.invoiceNumber = resultSet.getInt("InvoiceNo" );
        this.date = resultSet.getDate("Date" );
        this.weight = resultSet.getDouble("Weight");
        this.description = resultSet.getString("Description" );

    }
}
