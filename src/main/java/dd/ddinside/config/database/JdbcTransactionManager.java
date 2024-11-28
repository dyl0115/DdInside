package dd.ddinside.config.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTransactionManager
{
    private final DataSource dataSource;
    private final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public JdbcTransactionManager(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public Connection begin()
    {
        try
        {
            Connection conn = connectionHolder.get();
            if (conn == null)
            {
                conn = dataSource.getConnection();
                connectionHolder.set(conn);
                conn = connectionHolder.get();
            }
            conn.setAutoCommit(false);
            return conn;
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Connection Error! While get Connection from TransactionManager", e);
        }
    }

    public void commit(Connection conn)
    {
        try
        {
            conn.commit();
            conn.setAutoCommit(true);
            conn.close();
        }
        catch (SQLException e)
        {
            try
            {
                conn.rollback();
            }
            catch (SQLException ex)
            {
                throw new RuntimeException("Transaction RollBack Failed!", ex);
            }
            throw new RuntimeException("Transaction Commit Failed", e);
        }
    }

    public void rollback(Connection conn)
    {
        try
        {
            conn.rollback();
            conn.setAutoCommit(true);
            conn.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Transaction RollBack Failed!", e);
        }
    }

    public void update(Connection conn, String sql, StatementSetter setter)
    {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            setter.setValue(pstmt);
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Connection Error! While get Connection from TransactionManager", e);
        }
    }

    public Long updateAndReturnKey(Connection conn, String sql, StatementSetter setter)
    {
        try
        {
            PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            setter.setValue(pstmt);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();

            if (!rs.next())
            {
                throw new RuntimeException("Failed to get generated key");
            }
            long id = rs.getLong(1);

            rs.close();
            pstmt.close();
            return id;
        }
        catch (SQLException e)
        {
            throw new RuntimeException("Connection Error! While get Connection from TransactionManager", e);
        }
    }

    @FunctionalInterface
    public interface StatementSetter
    {
        void setValue(PreparedStatement pstmt) throws SQLException;
    }

    @FunctionalInterface
    public interface ResultMap<T>
    {
        T getResult(ResultSet rs);
    }

}
