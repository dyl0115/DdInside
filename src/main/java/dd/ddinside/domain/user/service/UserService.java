package dd.ddinside.domain.user.service;

import dd.ddinside.domain.user.User;
import dd.ddinside.domain.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public User findById(Long id)
    {
        return userDao.findById(id);
    }
}
