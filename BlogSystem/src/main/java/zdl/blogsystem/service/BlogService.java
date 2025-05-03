package zdl.blogsystem.service;

import zdl.blogsystem.common.pojo.BlogInfo;
import zdl.blogsystem.common.pojo.request.BlogAddRequest;
import zdl.blogsystem.common.pojo.request.BlogUpdateRequest;
import zdl.blogsystem.common.pojo.response.BlogInfoResponse;

import java.util.List;

public interface BlogService {
    public List<BlogInfoResponse> getList();

    public BlogInfoResponse getBlogDetail(int blogId);

    public BlogInfo getBlogInfo(int blogId);

    public boolean addBlog(BlogAddRequest blogAddRequest);

    //根据用户查询blog数量
    public Integer getBlogNumsById(Integer userId);

    //逻辑删除博客
    public boolean deleteBlog(Integer blogId);

    public boolean updateBlog(BlogUpdateRequest request);
}
