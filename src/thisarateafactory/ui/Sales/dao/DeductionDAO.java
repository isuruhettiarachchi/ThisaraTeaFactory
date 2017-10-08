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
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Mihitha Hansani
 */
public class DeductionDAO implements DAO{
    private int lotNo;//create objects and return that object can use future purposes
    private int invoiceNumber;
    private Date date;
    private int noOfLots;
    private double PSE;
    private double HChg;
    private double chamberChg;
    private double VAT;
    private double brokerage;
    private double furtherIns;
    private double totalDeduction;
    private String month;
    private int year;

    public int getLotNo() {
        return lotNo;//get from database
    }

    public void setLotNo(int lotNo) {
        this.lotNo = lotNo;//set the value
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

    public int getNoOfLots() {
        return noOfLots;
    }

    public void setNoOfLots(int noOfLots) {
        this.noOfLots = noOfLots;
    }
    
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public double getPSE() {
        return PSE;
    }

    public void setPSE(double PSE) {
        this.PSE = PSE;
    }

    public double getHChg() {
        return HChg;
    }

    public void setHChg(double HChg) {
        this.HChg = HChg;
    }

    public double getChamberChg() {
        return chamberChg;
    }

    public void setChamberChg(double chamberChg) {
        this.chamberChg = chamberChg;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(double brokerage) {
        this.brokerage = brokerage;
    }

    public double getFurtherIns() {
        return furtherIns;
    }

    public void setFurtherIns(double furtherIns) {
        this.furtherIns = furtherIns;
    }

    public double getTotalDeduction() {
        return totalDeduction;
    }

    public void setTotalDeduction(double totalDeduction) {
        this.totalDeduction = totalDeduction;
    }

    
    @Override//override methods get from DAO class
    public void update(Connection connection) throws SQLException{
        String updateQuery = "UPDATE income SET NoOfLots = ?, Date = ?, Month = ?, Year = ?, PSE = ?, HChg = ?, ChamberChg = ?, VAT = ?, " +
                "Brokerage = ?, FurtherIns = ?, TotalDeduction = ? WHERE Date = ? AND LotNo = ?";

        int index = 1;//go to colum to next colum
        PreparedStatement preparedStatement = connection.prepareStatement( updateQuery );

        preparedStatement.setInt(index++, noOfLots );
        
        java.sql.Timestamp timestamp = new java.sql.Timestamp( date.getTime());
        preparedStatement.setTimestamp( index++, timestamp );
    
        preparedStatement.setString(index++, month );
        preparedStatement.setInt(index++, year );
        preparedStatement.setDouble( index++, PSE );
        preparedStatement.setDouble( index++, HChg );
        preparedStatement.setDouble( index++, chamberChg );
        preparedStatement.setDouble( index++, VAT );
        preparedStatement.setDouble( index++, brokerage );
        preparedStatement.setDouble( index++, furtherIns );
        preparedStatement.setDouble( index++, totalDeduction );
        
        //java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        //preparedStatement.setDate(index++, sqlDate);
        
        preparedStatement.setInt( index++, lotNo);

        preparedStatement.executeUpdate();
        //connection.close();
    }

    @Override
    public void insert(Connection connection) throws SQLException{
                String insertQuery = "INSERT INTO income ( LotNo, Date, NoOfLots, Month, Year, PSE, HChg, " +
                "ChamberChg, VAT, Brokerage, FurtherIns, TotalDeduction)"
                + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        int index = 1;
        PreparedStatement preparedStatement = connection.prepareStatement( insertQuery );
        preparedStatement.setInt( index++, lotNo );

        //Calendar calendar = Calendar.getInstance();
        //java.sql.Timestamp timestamp = new java.sql.Timestamp( calendar.getTimeInMillis() );
        java.sql.Timestamp timestamp = new java.sql.Timestamp( date.getTime() );
        preparedStatement.setTimestamp( index++, timestamp );
        
        preparedStatement.setInt(index++, noOfLots );
        preparedStatement.setString(index++, month );
        preparedStatement.setInt(index++, year );
        preparedStatement.setDouble( index++, PSE );
        preparedStatement.setDouble( index++, HChg );
        preparedStatement.setDouble( index++, chamberChg );
        preparedStatement.setDouble( index++, VAT );
        preparedStatement.setDouble( index++, brokerage );
        preparedStatement.setDouble( index++, furtherIns );
        preparedStatement.setDouble( index++, totalDeduction );

        preparedStatement.execute();
        //connection.close();
    }

    @Override
    public void delete(Connection connection) throws SQLException{
        String deleteQuery = "DELETE FROM income WHERE LotNo = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(deleteQuery);
        preparedStmt.setInt(1, lotNo);

        preparedStmt.execute();
        //connection.close();
    }

    @Override//load to table from database
    public void load(Connection connection, ResultSet resultSet) throws SQLException {
        this.lotNo = resultSet.getInt("LotNo" );
        this.date = resultSet.getDate("Date" );
        this.month = resultSet.getString("month" );
        this.year = resultSet.getInt("year" );
        this.noOfLots = resultSet.getInt("NoOfLots" );
        this.PSE = resultSet.getDouble("PSE" );
        this.HChg = resultSet.getDouble( "HChg" );
        this.chamberChg = resultSet.getDouble( "ChamberChg" );
        this.VAT = resultSet.getDouble( "VAT" );
        this.brokerage = resultSet.getDouble( "Brokerage" );
        this.furtherIns = resultSet.getDouble( "FurtherIns" );
        this.totalDeduction = resultSet.getDouble( "TotalDeduction" );
    }
}