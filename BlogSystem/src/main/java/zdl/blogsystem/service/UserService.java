package zdl.blogsystem.service;

import zdl.blogsystem.common.pojo.UserInfo;
import zdl.blogsystem.common.pojo.request.LoginRequest;
import zdl.blogsystem.common.pojo.response.LoginResponse;
import zdl.blogsystem.common.pojo.response.UserInfoResponse;

public interface UserService {
    public LoginResponse login(LoginRequest loginRequest);

    public UserInfo SelectUserByUsername(String userName);

    public UserInfoResponse getUserInfoById(Integer id);

    public UserInfoResponse getAuthorInfo(Integer id);
}
