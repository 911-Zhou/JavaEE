package zdl.blogsystem.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import zdl.blogsystem.common.pojo.response.UserInfoResponse;

import java.time.LocalDateTime;

@Data
public class UserInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String password;
    private String githubUrl;
    private Byte deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public UserInfoResponse trans(){
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        BeanUtils.copyProperties(this,userInfoResponse);
        return userInfoResponse;
    }
}