package dd.ddinside.user.service;

import dd.ddinside.user.domain.User;
import dd.ddinside.user.dto.UserBaseDto;
import dd.ddinside.user.repository.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService
{
    private final UserMapper userMapper;

    public UserBaseDto save(UserBaseDto userDto)
    {
        userMapper.save(userDto);
        return findById(userDto.getId());
    }

    public UserBaseDto findById(Long id)
    {
        return userMapper.findById(id);
    }

    
}
