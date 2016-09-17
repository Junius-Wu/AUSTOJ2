//底部二维码展示
$(function () {
    $('[data-toggle=tooltip]').tooltip({delay:{show:0,hide:500}});
});

//返回顶部
$(".backtop").click(function () {
        $('body,html').animate({scrollTop:0},1000);
        return false;
});

//菜单链接进入active
$(function () {
   var index = window.location.href.split("/").length-1;
    var href = window.location.href.split("/")[index].substr(0,4);
    if(href.length>0){
        //如果匹配开头成功则更改样式
        $(".navbar-nav li a[href^='/"+href+"']").parent().addClass('active')
        //[attribute^=value]：匹配给定的属性是以某些值开始的元素。
    }
});

$(function () {
    //写入侧边栏目录
    $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:"../static/json/catelog.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function(data){
            //使用$.each方法遍历返回的数据date,插入到id为#result中
            $.each(data,function(i,item){
                var content=item.catename;
                $(".widget .catelog").append('<li><i class="fa fa-bookmark-o">&nbsp;&nbsp;<a href="/problem/catepro/'+item.id+'/'+content+'">'+content+'</a></i></li>');
            })
        }
    });
    //写入到标签栏
    $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:"../static/json/tags.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function(data){
            //使用$.each方法遍历返回的数据date,插入到id为#result中
            $.each(data,function(i,item){
                var content=item.tag;
                $(".widget .tags").append('<a href="/articles?search='+content+'" style="font-size: 12px;">'+content+'</a>');
            })
        }
    });
    //写入到文章
    $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:"../static/json/article.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function(data){
            //使用$.each方法遍历返回的数据date,插入到id为#result中
            $.each(data,function(i,item){
                var content=item.title;
                $(".widget .article").append('<li><i class="fa fa-file-text-o"><a href="/articles/'+item.id+'" target="_blank">'+content+'</a></i></li>');
            })
        }
    });
    //写入到通知
    $.ajax({
        //请求方式为get
        type:"GET",
        //json文件位置
        url:"../static/json/notify.json",
        //返回数据格式为json
        dataType: "json",
        //请求成功完成后要执行的方法
        success: function(data){
            //使用$.each方法遍历返回的数据date,插入到id为#result中
            $.each(data,function(i,item){
                var content=item.notify_name;
                $(".tips .notify").append('<li><a href="#">'+content+'</a></li>');
            })
        }
    });
//    首页请求在index页面
});

//通知栏轮播
function jump(){
    $(function(){
        $('.tips-bulls li').eq(0).slideUp(2000,function(){
            $(this).clone().appendTo($(this).parent()).show();
            $(this).remove();
        });
    });
}
setInterval('jump()',2000);


