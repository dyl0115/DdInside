package dd.ddinside.domain.user.dao;

import dd.ddinside.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao
{
    private final DataSource dataSource;

    @Autowired
    public UserDao(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    public void save(User user)
    {

    }

    public User findById(Long id)
    {
        JdbcTemplate<User> template = new JdbcTemplate<>(dataSource)
        {
            @Override
            protected User mapResult(ResultSet rs) throws SQLException
            {
                if (rs.next())
                {
                    return User.builder()
                            .id(rs.getLong("id"))
                            .username(rs.getString("username"))
                            .password(rs.getString("password"))
                            .nickname(rs.getString("nickname"))
                            .name(rs.getString("name"))
                            .phone(rs.getString("phone"))
                            .email(rs.getString("email"))
                            .profileImageUrl(rs.getString("profile_image_url"))
                            .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                            .modifiedAt(rs.getTimestamp("modified_at").toLocalDateTime())
                            .status(rs.getString("status"))
                            .build();
                }
                return null;
            }
        };

        String query = "SELECT * FROM USERS WHERE ID = ?";
        return template.execute(query, stmt ->
        {
            try
            {
                stmt.setLong(1, 1);
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        });
    }


    public User findByEmail(String email)
    {
        return null;
    }


    public List<User> findByStatus(String status)
    {
        return List.of();
    }


    public void updatePassword(Long id, String newPassword)
    {

    }


    public void updateNickname(Long id, String newNickname)
    {

    }


    public void updateProfileImage(Long id, String newProfileImageUrl)
    {

    }


    public void updateUser(User user)
    {

    }


    public void deleteById(Long id)
    {

    }


    public void softDelete(Long id)
    {

    }


    public boolean existByUsername(String username)
    {
        return false;
    }


    public boolean existByEmail(String email)
    {
        return false;
    }


    public User findByUsernameAndPassword(String username, String password)
    {
        return null;
    }
}
