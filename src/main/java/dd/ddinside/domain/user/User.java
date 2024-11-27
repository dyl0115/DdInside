package dd.ddinside.domain.user;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class User
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
