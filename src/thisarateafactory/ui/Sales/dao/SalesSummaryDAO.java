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
public class SalesSummaryDAO implements DAO{
    private int lotNumber;
    private int invoiceNumber;
    private String buyerDetails;
    private double netWeight;
    private double grossWeight;
    private String grade;
    private int chest;
    private double price;
    private double sampleWeight;
    private double grossProceed;

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

    public String getBuyerDetails() {
        return buyerDetails;
    }

    public void setBuyerDetails(String buyerDetails) {
        this.buyerDetails = buyerDetails;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(double netWeight) {
        this.netWeight = netWeight;
    }

    public double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getChest() {
        return chest;
    }

    public void setChest(int chest) {
        this.chest = chest;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSampleWeight() {
        return sampleWeight;
    }

    public void setSampleWeight(double sampleWeight) {
        this.sampleWeight = sampleWeight;
    }

    public double getGrossProceed() {
        return grossProceed;
    }

    public void setGrossProceed(double grossProceed) {
        this.grossProceed = grossProceed;
    }

    
    
    @Override
    public void update(Connection connection) throws SQLException{
        String updateQuery = "UPDATE sales SET Buyerdetails = ?, Grade = ?, " +
                "chest = ?, Price = ?, GrossWeight = ?, Sampleweight = ?, " + 
                "NetWeight = ?, GrossProceed = ? WHERE LotNo = ? AND InvoiceNo = ?";

        int index = 1;
        PreparedStatement preparedStatement = connection.prepareStatement( updateQuery );
        preparedStatement.setString(index++, buyerDetails );
        preparedStatement.setString( index++, grade );
        preparedStatement.setInt(index++, chest );
        preparedStatement.setDouble( index++, price );
        preparedStatement.setDouble( index++, grossWeight );
        preparedStatement.setDouble( index++, sampleWeight );
        preparedStatement.setDouble( index++, netWeight);
        preparedStatement.setDouble( index++, grossProceed );

        preparedStatement.setInt( index++, lotNumber );
        preparedStatement.setInt( index++, invoiceNumber );
        
        preparedStatement.executeUpdate();
        //connection.close();
    }

    @Override
    public void insert(Connection connection) throws SQLException{
                String insertQuery = "INSERT INTO sales ( LotNo, InvoiceNo, Buyerdetails, Grade, " +
                "chest, Price, GrossWeight, Sampleweight, NetWeight, GrossProceed )"
                + " VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

        int index = 1;
        PreparedStatement preparedStatement = connection.prepareStatement( insertQuery );
        preparedStatement.setInt( index++, lotNumber );
        preparedStatement.setInt( index++, invoiceNumber );
        preparedStatement.setString(index++, buyerDetails );
        preparedStatement.setString( index++, grade );
        preparedStatement.setInt(index++, chest );
        preparedStatement.setDouble( index++, price );
        preparedStatement.setDouble( index++, grossWeight );
        preparedStatement.setDouble( index++, sampleWeight );
        preparedStatement.setDouble( index++, netWeight);
        preparedStatement.setDouble( index++, grossProceed );
        

        preparedStatement.execute();
        //connection.close();
    }

    @Override
    public void delete(Connection connection) throws SQLException{
        String deleteQuery = "DELETE FROM sales WHERE LotNo = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(deleteQuery);
        preparedStmt.setInt(1, lotNumber);

        preparedStmt.execute();
        //connection.close();
    }

    @Override
    public void load(Connection connection, ResultSet resultSet) throws SQLException {
        this.lotNumber = resultSet.getInt("LotNo" );
        this.invoiceNumber = resultSet.getInt("InvoiceNo" );
        this.buyerDetails = resultSet.getString("Buyerdetails" );
        this.grade = resultSet.getString("Grade");
        this.chest = resultSet.getInt("chest" );
        this.price = resultSet.getDouble("Price" );
        this.grossWeight = resultSet.getDouble("GrossWeight" );
        this.sampleWeight = resultSet.getDouble("Sampleweight" );
        this.netWeight = resultSet.getDouble("NetWeight");
        this.grossProceed = resultSet.getDouble("GrossProceed" );
    }
    
    public Integer generateLotNo(Connection connection, ResultSet resultSet) throws SQLException{
     String query = "SELECT LotNo FROM sales ORDERBy LotNo asc";
     resultSet = connection.createStatement().executeQuery(query);
     resultSet.last();
     int Lot_No = resultSet.getInt(1);
     return Lot_No + 1;
    }
}
