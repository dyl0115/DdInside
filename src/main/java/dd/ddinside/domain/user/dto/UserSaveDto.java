package dd.ddinside.domain.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserSaveDto
{
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String name;
    private String phone;
    private String email;
    private String profileImageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String status;
}