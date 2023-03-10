package hig.se.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DbConnectionManager is a Singleton object that manages the
 * connection to the database.
 * All transactions between DAO and the DB is performed by
 * this object.
 * DAO doesn't know anything about 'Connections', 'Transaction' or
 * other DB specific techniques, this is totally managed by this class.
 * Only SQL strings, by query or PreparedStatements, and returned
 * ResultSet is exposed to DAO
 * <br><strong>Note:</strong> This connects to a postgresql database running
 * on localhost. You also need to download suitable JDBC driver (4.2 or higher)
 * from <a href="https://jdbc.postgresql.org/download.html">jdbc.postgresql.org/download</a>
 * For other DB or different locations of the DB, please change connection String accordingly.
 *
 * @author awi (ï¿½ke Wallin)
 * @version 2018-10-18
 */
public class DbConnectionManager {

    private static final String DB_NAME = "dbalim"; // Change value to your database
    private static final String USER = "dbalim"; // Change value to your user
    private static final String PASSWORD = "dbalim123"; // Change value to your password
    //private static final String CONNECTION_URL = "jdbc:postgresql://localhost/" + DB_NAME;
    private static final String CONNECTION_URL = "jdbc:mysql://node96052-mysql.jls-sto3.elastx.net:11107/" + DB_NAME;

    private Connection connection = null;
    private Statement statement = null;

    private static DbConnectionManager instance = null;
    private static final Lock lock = new ReentrantLock();

    private DbConnectionManager() {
    }

    public static DbConnectionManager getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new DbConnectionManager();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }



    private Statement getStatement(Connection connection) {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Could not create Statement");
            System.err.println(e.getMessage());
        }
        return statement;
    }

    /**
     * Wrapper method to the 'statement'.executeQuery(String sqlString)
     * A statement needs a connection and is mandatory to be able
     * to send a query to the DB. This is wrapped to contain all DB
     * specific techniques within this class.
     * This method is one of the few that is exposed as public and it
     * hides the DB-connection mechanism.
     * <strong>Because of the bare sql string this method is susceptible to
     * SQL-injection and should only be used for read only transactions.</strong>
     *
     * @param sqlString The sql string
     * @return A ResultSet with the result from the SQL query
     */
    public ResultSet excecuteQuery(String sqlString) throws SQLException {
        return this.getStatement(this.connection).executeQuery(sqlString);
    }

    /**
     * Wrapper method to the 'connection'.prepareStatement(String sqlString)
     * It hides the DB-connection mechanism but exposes (by public) the prepareStatement
     * method thus needing only a String (the SQL string) and returning a ResultSet
     * <strong>This method is not susceptible to SQL-injection and is therefore safe to use
     * for write operations on the database.<strong>
     */
    public PreparedStatement prepareStatement(String statementString, int returnGeneratedKeys) throws SQLException {
        return this.connection.prepareStatement(statementString, returnGeneratedKeys);
    }

    public void open() {
        if (this.connection == null) {
            try {
                connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
                System.out.println("Connected to MYSQL server successfully");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void close() {
        try {
            if (statement != null) statement.close();
            if (connection != null)
                connection.close(); this.connection = null;
            System.out.println("DB Connection closed");
        } catch (SQLException e) {
            System.err.println("Could not close the statement or the connection");
            System.err.println(e.getMessage());
        }
    }
}
