# AUSTOJ2说明文档

标签（空格分隔）： austoj

---

###起源:
最初学习SSM框架的时候,做了AUSTOJ这个项目(**地址**:[AUSTOJ-LINK][1]),因为是边学习边做的,所以代码规范,一些逻辑处理等做的都不是很好,比较适合入门的同学参考下.实习后打算把项目重构下,添加一些其他功能,把自己学习到的都逐步运用到项目中,所以就有了该项目AUSTOJ2.


###项目概要:
依然是SSM框架,不过JSP被替换为thymeleaf模板(**地址**:[Thymeleaf][2]),JDK使用1.8版本,目前该项目处于实现功能阶段,所以一些关乎性能的东西都没加上,比如缓存,另外已作为个人的毕业设计

目前欠缺功能:
1. 文章列表,文章展示<br>
2. 判题系统(会采用python,实现参考(**地址**:[QingdaoU][3]))<br>
3. 后台系统(会采用springboot+aujular2)<br>
4. 权限管理(会采用shiro)<br>
.....不写了,越写越感觉要实现的东西太多了<br>

###使用说明

    暂留坑,等写完再放出


###项目地址:
> 地址: http://mrdear.cn:8080/austoj  (个人的渣渣服务器)


![这里写图片描述](http://img.blog.csdn.net/20161125092128864)



###更新日志

####2016.11.26更新:
* 竞赛模块完成,只是主逻辑,因为没有详细的测试,所以难免有bug,如有问题,请Issue

####2016.11.25更新:

* 留言板完成,仿照[Segmentfault][4]回答问题模式,当然仿的不够好,目前不支持markdown留言,因为没找到java上的比较好用的markdown解析器.如果有哪位同学可以自己实现一个的话,最好不过


  [1]: https://github.com/nl101531/AUSTOJ
  [2]: https://github.com/thymeleaf/thymeleaf
  [3]: https://github.com/QingdaoU/JudgeServer
  [4]: http://segmentfault.com/
