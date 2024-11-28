package dd.ddinside.domain.user.controller;

import dd.ddinside.domain.user.User;
import dd.ddinside.domain.user.dto.UserSaveDto;
import dd.ddinside.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id)
    {
        return userService.findById(id);
    }

    @PostMapping()
    public User save(@RequestBody UserSaveDto userDto)
    {
        return userService.save(userDto);
    }
}
