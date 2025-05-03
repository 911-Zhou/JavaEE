$(document).ajaxError(function(event,xhr,options,exc){
    if(xhr.status==400){
        alert("参数校验失败");
    }else if(xhr.status==401){
        alert("⽤⼾未登录, 即将跳转到登录⻚!");
        //已经被拦截器拦截了, 未登录
        location.href ="blog_login.html";
    }
    else if(xhr.status==500){
        alert("服务器错误");
    }
});
$(document).ajaxSend(function (e, xhr, opt) {
    var Authorization = localStorage.getItem("Authorization");
    //console.log("Authorization:"+Authorization);  // 查看获取的 Authorization 值
    xhr.setRequestHeader("Authorization", Authorization);
});

function getUserInfo(userUrl){
    $.ajax({
        type:"get",
        url: userUrl,
        success:function(result){
            if(result.code==200 && result.data!=null){
                $(".left .card h3").text(result.data.userName);
                $(".left .card a").attr("href",result.data.githubUrl);
                $(".left .card .row2 span:first-child").text(result.data.blogNums);
            }
        }
    });
}

function logout() {
    //删除Cookie, 设置Cookie为空即可
    localStorage.removeItem("Authorization");
    localStorage.removeItem("loginUserId");
    location.href = "/blog_login.html";
}