package zdl.blogsystem.controller;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zdl.blogsystem.common.pojo.request.BlogAddRequest;
import zdl.blogsystem.common.pojo.request.BlogUpdateRequest;
import zdl.blogsystem.common.pojo.response.BlogInfoResponse;
import zdl.blogsystem.service.BlogService;
import zdl.blogsystem.service.impl.BlogServiceImpl;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/blog")
public class BlogController {
    @Resource(name = "blogServiceImpl")
    private BlogServiceImpl blogServiceImpl;

    @RequestMapping("/getList")
    //查询列表所有数据
    public List<BlogInfoResponse> getList(){
        return blogServiceImpl.getList();
    }

    @RequestMapping("/getBlogDetail")
    public BlogInfoResponse getBlogDetail(@NotNull(message = "id不能为空") int blogId){
        return blogServiceImpl.getBlogDetail(blogId);
    }

    @RequestMapping("/add")
    public boolean addBlog(@Validated @RequestBody BlogAddRequest blogAddRequest){
        return blogServiceImpl.addBlog(blogAddRequest);
    }

    //逻辑删除
    @RequestMapping("/delete")
    public boolean deleteBlog(@NotNull Integer blogId){
        return  blogServiceImpl.deleteBlog(blogId);
    }


    @RequestMapping("/update")
    public boolean updateBlog(@Validated @RequestBody BlogUpdateRequest request){
        log.info("更新博客："+request.toString());
        return blogServiceImpl.updateBlog(request);
    }
}
