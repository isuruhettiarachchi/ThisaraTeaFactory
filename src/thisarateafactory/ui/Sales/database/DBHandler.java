/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.ui.Sales.database;

import thisarateafactory.ui.Sales.dao.DAO;
import thisarateafactory.ui.Sales.dao.DeductionDAO;
import thisarateafactory.ui.Sales.dao.FinalizingDAO;
import thisarateafactory.ui.Sales.dao.ReturnSalesDAO;
import thisarateafactory.ui.Sales.dao.SalesSummaryDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import thisarateafactory.database.databaseHandler;

/**
 *
 * @author Mihitha Hansani
 */
public class DBHandler
{
    private static DBHandler instance;
    private Connection connection;
    
    private DBHandler()
    {
        connection = databaseHandler.getConnection();
    }
    
    public static DBHandler getInstance()
    {
        if ( instance == null )
        {
            instance = new DBHandler();
        }
        
        return instance;
    }
    
    public String insert( DAO dao )
    {
        if ( connection == null )
        {
            return "Database Connection not established";
        }
        
        if ( dao != null )
        {
            try
            {
                dao.insert( connection );
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
                return getErrorMessage( ex );
            }
        }
        return null;
    } 
    
    public String update( DAO dao )
    {
        if ( connection == null )
        {
            return "Database Connection not established";
        }
                
        if ( dao != null )
        {
            try
            {
                dao.update(connection );
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
                return getErrorMessage( ex );
            }
        }
        return null;
    } 
    
    public String delete( DAO dao )
    {
        if ( connection == null )
        {
            return "Database Connection not established";
        }
                
        if ( dao != null )
        {
            try
            {
                dao.delete(connection );
            }
            catch (SQLException ex) 
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
                return getErrorMessage( ex );
            }
        }
        return null;
    }
    
    public List<FinalizingDAO> getAllFinalizingRecords()
    {
        if ( connection != null )
        {
            List<FinalizingDAO> list = new ArrayList<>();
            
            try
            {
                PreparedStatement loadStatement = connection.prepareStatement( "SELECT * FROM finalization" );
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    FinalizingDAO finalizingDAO = new FinalizingDAO();
                    finalizingDAO.load( connection, resultSet );
                    list.add( finalizingDAO );
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return list;
        }
        return null;
    }
    
    public List<ReturnSalesDAO> getAllReturnSalesRecords()
    {
        if ( connection != null )
        {
            List<ReturnSalesDAO> list = new ArrayList<>();
            
            try
            {
                PreparedStatement loadStatement = connection.prepareStatement( "SELECT * FROM returnsale" );
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    ReturnSalesDAO returnSalesDAO = new ReturnSalesDAO();
                    returnSalesDAO.load( connection, resultSet );
                    list.add( returnSalesDAO );
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return list;
        }
        return null;
    }
    
    public List<SalesSummaryDAO> getAllSalesSummaryRecords()
    {
        if ( connection != null )
        {
            List<SalesSummaryDAO> list = new ArrayList<>();
            
            try
            {
                PreparedStatement loadStatement = connection.prepareStatement( "SELECT * FROM sales" );
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    SalesSummaryDAO salesSummaryDAO = new SalesSummaryDAO();
                    salesSummaryDAO.load( connection, resultSet );
                    list.add( salesSummaryDAO );
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return list;
        }
        return null;
    }
      
    public List<DeductionDAO> getAllDeductionRecords()
    {
        if ( connection != null )
        {
            List<DeductionDAO> list = new ArrayList<>();
            
            try
            {
                PreparedStatement loadStatement = connection.prepareStatement( "SELECT * FROM income" );
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    DeductionDAO deductionDAO = new DeductionDAO();
                    deductionDAO.load( connection, resultSet );
                    list.add( deductionDAO );
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return list;
        }
        return null;
    }
        
    public List<String> getGradeList()
    {
        if ( connection != null )
        {
            List<String> list = new ArrayList<>();

            try
            {
                PreparedStatement loadStatement = connection.prepareStatement( "SELECT Grade FROM packingdetails" );
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    list.add( resultSet.getString("Grade" ) );
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            return list;
        }
        return null;
    }
    
     public List<String> getLotNoList()
    {
        if ( connection != null )
        {
            List<String> list = new ArrayList<>();

            try
            {
                PreparedStatement loadStatement = connection.prepareStatement( "SELECT LotNo FROM sales" );
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    list.add( resultSet.getString("LotNo" ) );
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            return list;
        }
        return null;
    }
    
     public List<String> getInvoiceNoList()
    {
        if ( connection != null )
        {
            List<String> list = new ArrayList<>();

            try
            {
                PreparedStatement loadStatement = connection.prepareStatement( "SELECT InvoiceNo FROM packingDetails" );
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    list.add( resultSet.getString("InvoiceNo" ) );
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            return list;
        }
        return null;
    }
    
    private String getErrorMessage( SQLException ex )
    {
        return ex.getMessage();
    }

    public int getTotalLotNumber(DeductionDAO deductionDAO) {
        int count = 0;
        if ( connection != null )
        {
            try
            { 
                PreparedStatement loadStatement = connection.prepareStatement( 
                        "SELECT COUNT(*) AS count FROM income WHERE Month = ? AND Year = ?" );
                
                int index = 1;
                loadStatement.setString(index++, deductionDAO.getMonth());
                loadStatement.setInt(index++, deductionDAO.getYear());
                
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    count = resultSet.getInt("count");
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return count;
    }

    public double getTotalNetWeight( DeductionDAO deductionDAO ) {        
        double result = 0;
        if ( connection != null )
        {
            try
            { 
                PreparedStatement loadStatement = connection.prepareStatement( 
                        "SELECT NetWeight FROM sales WHERE LotNo = ? AND InvoiceNo = ? " );
                int index = 1;
                loadStatement.setInt(index++, deductionDAO.getLotNo() );
                loadStatement.setInt(index++, deductionDAO.getInvoiceNumber() );
                
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    result = resultSet.getDouble("NetWeight");
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public double getTotalGrossProceed(FinalizingDAO finalizingDAO) {
        DeductionDAO dummy = new DeductionDAO();
        dummy.setLotNo( finalizingDAO.getLotNumber() );
        dummy.setInvoiceNumber( finalizingDAO.getInvoiceNumber() );
        
        return getTotalGrossProceed( dummy );
    }
    
    public double getTotalGrossProceed(DeductionDAO deductionDAO) {
        double result = 0;
        if ( connection != null )
        {
            try
            { 
                PreparedStatement loadStatement = connection.prepareStatement( 
                        "SELECT SUM(GrossProceed) AS result FROM  sales  WHERE LotNo = ? AND InvoiceNo = ? " );
                int index = 1;
                loadStatement.setInt(index++, deductionDAO.getLotNo() );
                loadStatement.setInt(index++, deductionDAO.getInvoiceNumber());
                                
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    result = resultSet.getDouble("result");
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public double getTotalDeduction(FinalizingDAO finalizingDAO) {
        double result = 0;
        if ( connection != null )
        {
            try
            { 
                PreparedStatement loadStatement = connection.prepareStatement( 
                        "SELECT SUM(TotalDeduction) AS result FROM  income  WHERE LotNo = ?" );
                int index = 1;
                loadStatement.setInt(index++, finalizingDAO.getLotNumber());
                //loadStatement.setInt(index++, finalizingDAO.getInvoiceNumber());
                                
                ResultSet resultSet = loadStatement.executeQuery();
                while ( resultSet.next() )
                {
                    result = resultSet.getDouble("result");
                }

                resultSet.close();
                loadStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public int generateNextLotNo() 
    {
        int result = 0;
        if ( connection != null )
        {
            try
            { 
                PreparedStatement nextLotNumberStatement = connection.prepareStatement( 
                        "SELECT MAX(LotNo) AS result FROM sales" );

                ResultSet resultSet = nextLotNumberStatement.executeQuery();
                while ( resultSet.next() )
                {
                    result = resultSet.getInt("result");
                }

                resultSet.close();
                nextLotNumberStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result + 1;
    }

    //get a lastly added entry of sales table colum Total gross proceed
    public double getLatestTotalGrossProceed()
    {
        int maxLot = 0;
        double result = 0.0;
        if ( connection != null )
        {
            try
            { 
                PreparedStatement maxLotStatement = 
                        connection.prepareStatement( "SELECT MAX(LotNo) AS result FROM sales" );
                ResultSet resultSet = maxLotStatement.executeQuery();
                while ( resultSet.next() )
                {
                    maxLot = resultSet.getInt("result");
                }
                resultSet.close();
                maxLotStatement.close();
                
                PreparedStatement resultStatement = connection.prepareStatement( 
                        "SELECT GrossProceed AS result FROM sales WHERE LotNo = ?" );
                
                int index = 1;
                resultStatement.setInt(index++, maxLot);
                
                resultSet = resultStatement.executeQuery();
                while ( resultSet.next() )
                {
                    result = resultSet.getInt("result");
                }
                resultSet.close();
                resultStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    //here get total deduction from deduction interfce income table.
//    
    
    public double getLatestTotalDeduction()
    {
        int maxLot = 0;
        double result = 0.0;
        if ( connection != null )
        {
            try
            { 
                PreparedStatement maxLotStatement = 
                        connection.prepareStatement( "SELECT MAX(LotNo) AS result FROM income" );
                ResultSet resultSet = maxLotStatement.executeQuery();
                while ( resultSet.next() )
                {
                    maxLot = resultSet.getInt("result");
                }
                resultSet.close();
                maxLotStatement.close();
                
                PreparedStatement resultStatement = connection.prepareStatement( 
                        "SELECT TotalDeduction AS result FROM income WHERE LotNo = ?" );
                
                int index = 1;
                resultStatement.setInt(index++, maxLot);
                
                resultSet = resultStatement.executeQuery();
                while ( resultSet.next() )
                {
                    result = resultSet.getInt("result");
                }
                resultSet.close();
                resultStatement.close();
            }
            catch( SQLException ex )
            {
                Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return result;
    }

    
    
}
