package dd.ddinside.domain.user.dao;

import dd.ddinside.domain.user.User;
import dd.ddinside.domain.user.dto.UserSaveDto;
import dd.ddinside.config.database.JdbcTransactionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserDao
{
    private final JdbcTransactionManager transactionManager;

    public Long save(UserSaveDto userDto)
    {
        String sql = "INSERT INTO USERS(USERNAME,PASSWORD,NICKNAME,NAME,PHONE,EMAIL,PROFILE_IMAGE_URL,CREATED_AT,MODIFIED_AT,STATUS) VALUES(?,?,?,?,?,?,?,?,?,?)";
        Connection conn = transactionManager.begin();
        Long id = transactionManager.updateAndReturnKey(conn, sql, pstmt ->
        {
            pstmt.setString(1, userDto.getUsername());
            pstmt.setString(2, userDto.getPassword());
            pstmt.setString(3, userDto.getNickname());
            pstmt.setString(4, userDto.getName());
            pstmt.setString(5, userDto.getPhone());
            pstmt.setString(6, userDto.getEmail());
            pstmt.setString(7, userDto.getProfileImageUrl());
            pstmt.setTimestamp(8, Timestamp.valueOf(userDto.getCreatedAt()));
            pstmt.setTimestamp(9, Timestamp.valueOf(userDto.getModifiedAt()));
            pstmt.setString(10, userDto.getStatus());
        });
        transactionManager.commit(conn);
        return id;
    }

    public User findById(Long id)
    {
        return null;
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
