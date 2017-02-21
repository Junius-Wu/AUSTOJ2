##AUST-Judger项目说明

具体判题核心来自: https://github.com/hzxie/voj,感谢作者的开源

针对改进:
1. 输入输出都与数据库解耦
2. 替换MQ消息为gRPC,可以实现双向流式通信(第一版未这样写)

第一版比较简单,后续版本会增加更多功能,比如virtual OJ