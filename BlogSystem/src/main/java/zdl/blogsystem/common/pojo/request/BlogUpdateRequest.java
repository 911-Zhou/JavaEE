package zdl.blogsystem.common.pojo.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import zdl.blogsystem.common.pojo.BlogInfo;

@Data
public class BlogUpdateRequest {
    @NotNull(message = "博客id不能为空")
    private Integer id;
    @NotBlank(message = "标题不能为空")
    @Length(message = "标题长度不符合规定",max = 20)
    private String title;
    @NotBlank(message = "内容不能为空")
    private String content;

    public BlogInfo trans(){
        BlogInfo blogInfo = new BlogInfo();
        BeanUtils.copyProperties(this,blogInfo);

        return blogInfo;
    }
}
