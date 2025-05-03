package zdl.blogsystem.common.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginRequest {
    @NotBlank(message = "用户名不能为空")
    @Length(max = 20,message = "用户名长度最大为20")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Length(max = 20,message = "密码长度最大为20")
    private String password;
}
