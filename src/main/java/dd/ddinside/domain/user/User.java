package dd.ddinside.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
