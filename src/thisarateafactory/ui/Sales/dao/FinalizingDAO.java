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
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mihitha Hansani
 */
public class FinalizingDAO implements DAO{
    
    private int lotNumber;
    private int invoiceNumber;
    private Date finalizeDate;
    private double totalGross;
    private String month;
    private double totalDeduction;
    private double totalNet;

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

    public Date getFinalizeDate() {
        return finalizeDate;
    }

    public void setFinalizeDate(Date finalizeDate) {
        this.finalizeDate = finalizeDate;
    }

    public double getTotalGross() {
        return totalGross;
    }

    public void setTotalGross(double totalGross) {
        this.totalGross = totalGross;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(double totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    public double getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(double totalNet) {
        this.totalNet = totalNet;
    }

    
    @Override
    public void update(Connection connection) throws SQLException{
        String updateQuery = "UPDATE finalization SET Date = ?, TotalGrossProceed = ?, " +
                "Month = ?, TotalDeduction = ?, TotalNetProceed = ? WHERE LotNo = ?";

        int index = 1;
        PreparedStatement preparedStatement = connection.prepareStatement( updateQuery );
        //preparedStatement.setInt( index++, invoiceNumber );

        java.sql.Timestamp timestamp = new java.sql.Timestamp( finalizeDate.getTime());
        preparedStatement.setTimestamp( index++, timestamp );
        
        preparedStatement.setDouble(index++, totalGross );
        preparedStatement.setString( index++, month );
        preparedStatement.setDouble( index++, totalDeduction );
        preparedStatement.setDouble( index++, totalNet );
        
        preparedStatement.setInt( index++, lotNumber );

        preparedStatement.executeUpdate();
        //connection.close();
    }

    @Override
    public void insert(Connection connection) throws SQLException{
        String insertQuery = "INSERT INTO finalization ( LotNo, Date, TotalGrossProceed, " +
                "Month, TotalDeduction, TotalNetProceed )"
                + " VALUES ( ?, ?, ?, ?, ?, ? )";

        int index = 1;
        PreparedStatement preparedStatement = connection.prepareStatement( insertQuery );
        
        preparedStatement.setInt( index++, lotNumber );
        //preparedStatement.setInt( index++, invoiceNumber );

        java.sql.Timestamp timestamp = new java.sql.Timestamp( finalizeDate.getTime());
        preparedStatement.setTimestamp( index++, timestamp );
        
        preparedStatement.setDouble(index++, totalGross );
        preparedStatement.setString( index++, month );
        preparedStatement.setDouble( index++, totalDeduction );
        preparedStatement.setDouble( index++, totalNet );
        
        preparedStatement.execute();
        //connection.close();
        
        // Insert Data in to Transaction Table
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.setType("Sales");
        //change
        transactionDAO.setAmount(totalNet);
        transactionDAO.setDate(new Date());
        transactionDAO.insert(connection);
    }

    @Override
    public void delete(Connection connection) throws SQLException{
        String deleteQuery = "DELETE FROM finalization WHERE LotNo = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(deleteQuery);
        preparedStmt.setInt(1, lotNumber);

        preparedStmt.execute();
        //connection.close();
    }

    @Override
    public void load(Connection connection, ResultSet resultSet) throws SQLException {
        this.lotNumber = resultSet.getInt("LotNo" );
        this.totalGross = resultSet.getDouble("TotalGrossProceed" );
        this.month = resultSet.getString( "Month" );
        this.finalizeDate = resultSet.getDate("Date" );
        this.totalDeduction = resultSet.getDouble( "TotalDeduction" );
        this.totalNet = resultSet.getDouble( "TotalNetProceed" );
    } 
}
