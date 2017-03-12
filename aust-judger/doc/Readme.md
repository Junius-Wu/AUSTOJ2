##AUST-Judger项目说明

具体判题核心来自: https://github.com/hzxie/voj,感谢作者的开源

针对改进:
1. 输入输出都与数据库解耦
2. 替换MQ消息为gRPC,可以实现双向流式通信(第一版未这样写)

如何使用:

**linux系统:**

需要环境 
> Maven 3+ and GCC 4.8+ with POSIX thread model is required.
Make sure add the Maven and GCC to the PATH.

JNI环境

> cd $JAVA_HOME/include/linux
cp jawt_md.h jni_md.h ..


对aust-parent项目执行mvn install
对aust-common项目执行mvn install
对aust-judger项目执行mvn clean package

**mac or win系统:**

需要借助docker容器来构建编译,容器所需环境为项目目录下的Dockerfile文件,构建环境后
执行`docker run -ti -p -v 项目绝对路径:/AUSTOJ2 镜像名`,会挂在项目到容器中,在容器中编译打包.


启动命令:
`java -jar 打包后的jar`

然后根据docker分配的端口映射本地再访问测试


第一版比较简单,后续版本会增加更多功能,比如virtual OJ