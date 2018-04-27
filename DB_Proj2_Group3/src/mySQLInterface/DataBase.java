package mySQLInterface;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Functions from JDBC basics on D2L
 * The retrieve data and prepared statement functions were modified slightly
 */

public class DataBase{
	// For a more in-depth tutorial see: https://www.tutorialspoint.com/jdbc/index.htm

    // IMPORTANT: Make sure all imports for the SQL stuff use java.sql !!!

	private static DataBase db = null;
	public static DataBase getDataBase()
	{
		if(DataBase.db == null)
			DataBase.db = new DataBase();
		return DataBase.db;
	}
	
    /**
     * This is the recommended way to activate the JDBC drivers, but is
     * only setup to work with one specific driver.  Setup to work with
     * a MySQL JDBC driver.
     *
     * If the JDBC Jar file is not in your build path this will not work.
     * I have the Jar file posted in D2L.
     * 
     * @return Returns true if it successfully sets up the driver.
     */
    private boolean activateJDBC()
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return true;
    }
    
   /**
    * You MUST change these values based on the DB you are assigned to work with.
    */
    public static final String DB_LOCATION = "jdbc:mysql://localhost/HW4";
    public static final String LOGIN_NAME = "bartljos";
    public static final String PASSWORD = "Jo12sen$";
    // Make sure and use the java.sql imports.
    protected Connection m_dbConn = null;
    
    public void setUpDatabaseConnection() throws SQLException
    {
 
    		System.out.println("Activating");
    		this.activateJDBC();
    		System.out.println("Creating Connection");
    		this.m_dbConn = this.createConnection();
    		if(this.m_dbConn.isClosed()) //if there is no connection
    			System.exit(0);
    }
    
   /** 
    * Creates a connection to the database that you can then send commands to.
    */
    private Connection createConnection()
    {
    	try {
			m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return m_dbConn;
    }
    
    public void closeConnection() throws SQLException
    {
    	this.m_dbConn.close();
    }
    

   DatabaseMetaData meta = null;
   /**
    * To get the meta data for the DB.
    */
    public DatabaseMetaData getMetaData()
    {
    	try {
			meta = m_dbConn.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return meta;
    }

    //prepared statement variable
    PreparedStatement pstmt = null;
    /**
     * Function that creates a new prepared statement
     * @param s, the prepared statement string
     * @throws SQLException
     */
    public void createPreparedStatement(String s) throws SQLException
    {
    	pstmt = m_dbConn.prepareStatement(s);
    }
    
    /**
     * Function that adds parameters to prepared statement
     * @param col, the column (?) of the statement to edit
     * @param param, the new parameter as a string
     * @throws SQLException
     */
    public void addToPreparedStatement(int col, String param) throws SQLException
    {
    	pstmt.setString(col, param);
    }
    
    /**
     * Function that executes a prepared statement
     * @throws SQLException
     */
    public void executePreparedStatement() throws SQLException
    {
    	pstmt.execute();
    }
    
   /**
    * To execute an SQL statement that is not a SELECT statement.
    */
    public void AddData(String s) throws Exception
    {
        // Using Statement to insert a value
        // Best used when all values are hard coded.
        Statement stmt = m_dbConn.createStatement();
        String insertData = new String(s);
        int rowsAffected = stmt.executeUpdate(insertData);
        if (rowsAffected == 1)
        {
        	System.out.println("Added");
        	return;
        }
    }

    /**
     * Function for retrieving the result set
     * I mostly changed the original function so this only retrieve the result set 
     * 						to be used elsewhere
     * @param s, a command
     * @return, the result set
     * @throws Exception
     */
    public ResultSet retrieveData(String s)throws Exception
    {
   		String selectData = new String(s);
   		Statement stmt = m_dbConn.createStatement();
   		ResultSet rs = stmt.executeQuery(selectData);
   		return rs;
    		
    }
 
}
