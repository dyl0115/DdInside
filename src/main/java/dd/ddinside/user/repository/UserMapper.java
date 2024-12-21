package dd.ddinside.user.repository;

import dd.ddinside.user.domain.User;
import dd.ddinside.user.dto.UserBaseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper
{
    public void save(UserBaseDto userDto);

    public UserBaseDto findById(Long id);

    public User findByEmail(String email);

    public List<User> findByStatus(String status);

    public void updatePassword(Long id, String newPassword);

    public void updateNickname(Long id, String newNickname);

    public void updateProfileImage(Long id, String newProfileImageUrl);

    public void updateUser(User user);

    public void deleteById(Long id);

    public void softDelete(Long id);

    public boolean existByUsername(String username);

    public boolean existByEmail(String email);

    public User findByUsernameAndPassword(String username, String password);
}
