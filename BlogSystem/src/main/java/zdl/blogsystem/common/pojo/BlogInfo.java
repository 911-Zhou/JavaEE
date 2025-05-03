package zdl.blogsystem.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import zdl.blogsystem.common.pojo.response.BlogInfoResponse;

import java.time.LocalDateTime;

@Data
public class BlogInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Integer deleteFlag;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public BlogInfoResponse trans(){
        BlogInfoResponse ret = new BlogInfoResponse();
        BeanUtils.copyProperties(this,ret);
        return ret;
    }
}