package simran.api.databaseUtilities;
import java.sql.*;

public class DatabaseUtils {

	public static Connection createConnection(String driver, String url, String username, String password )throws ClassNotFoundException, SQLException
	{
		Class.forName(driver);
		if ((username == null) || (password == null) || (username.trim().length() == 0) || (password.trim().length() == 0))
        {
            return DriverManager.getConnection(url);
        }
		else
        {
            return DriverManager.getConnection(url, username, password);
        }
	}
	
	public static void closeCN(Connection connection)
    {
        try
        {
            if (connection != null)
            {
                connection.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public static void closeST(Statement st)
    {
        try
        {
            if (st != null)
            {
                st.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void closeRS(ResultSet rs)
    {
        try
        {
            if (rs != null)
            {
                rs.close();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection connection)
    {
        try
        {
            if (connection != null)
            {
                connection.rollback();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
	
}
