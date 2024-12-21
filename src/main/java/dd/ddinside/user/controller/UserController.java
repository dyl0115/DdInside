package dd.ddinside.user.controller;

import dd.ddinside.user.domain.User;
import dd.ddinside.user.dto.UserBaseDto;
import dd.ddinside.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;

    @PostMapping()
    public UserBaseDto save(@RequestBody UserBaseDto userDto)
    {
        return userService.save(userDto);
    }
}
