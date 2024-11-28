package dd.ddinside.domain.user.dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate<T>
{
    private final DataSource dataSource;

    public JdbcTemplate(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public T query(String sql, StatementSetter setter, ResultMapper<T> mapper)
    {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            setter.setValues(pstmt);

            try (ResultSet rs = pstmt.executeQuery())
            {
                return mapper.mapResult(rs);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface StatementSetter
    {
        void setValues(PreparedStatement pstmt) throws SQLException;
    }

    @FunctionalInterface
    public interface ResultMapper<R>
    {
        R mapResult(ResultSet rs) throws SQLException;
    }
}
