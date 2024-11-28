package dd.ddinside.domain.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public abstract class JdbcTemplate<T>
{
    private final DataSource dataSource;

    public JdbcTemplate(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public T execute(String query, Consumer<PreparedStatement> statementBinder)
    {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try
        {
            conn = dataSource.getConnection();
            stmt = conn.prepareStatement(query);
            statementBinder.accept(stmt);
            rs = stmt.executeQuery();

            return mapResult(rs);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            closeResources(rs, stmt, conn);
        }
    }

    protected abstract T mapResult(ResultSet rs) throws SQLException;

    private void closeResources(ResultSet rs, PreparedStatement stmt, Connection conn)
    {
        try
        {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
