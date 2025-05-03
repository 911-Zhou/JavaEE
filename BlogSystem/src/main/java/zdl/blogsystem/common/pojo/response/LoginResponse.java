package zdl.blogsystem.common.pojo.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private Integer userId;
}
