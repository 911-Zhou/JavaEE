package zdl.blogsystem.common.pojo.response;

import lombok.Data;

@Data
public class UserInfoResponse {
    private Integer id;
    private String userName;
    private String githubUrl;
    //该账号拥有博客数量
    private Integer BlogNums;
}
