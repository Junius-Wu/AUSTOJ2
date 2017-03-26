#构建judger端需要的环境,方便本地测试
#基于java8环境
FROM java:8

#维护人信息
MAINTAINER quding niudear@foxmail.com
#修改源信息
RUN echo "deb http://mirrors.163.com/ubuntu/ wily main restricted universe multiverse" > /etc/apt/sources.list
RUN echo "deb http://mirrors.163.com/ubuntu/ wily-security main restricted universe multiverse" >> /etc/apt/sources.list
RUN echo "deb http://mirrors.163.com/ubuntu/ wily-updates main restricted universe multiverse" >> /etc/apt/sources.list
RUN echo "deb http://mirrors.163.com/ubuntu/ wily-proposed main restricted universe multiverse">> /etc/apt/sources.list
RUN echo "deb http://mirrors.163.com/ubuntu/ wily-backports main restricted universe multiverse">> /etc/apt/sources.list
RUN echo "deb-src http://mirrors.163.com/ubuntu/ wily main restricted universe multiverse" >> /etc/apt/sources.list
RUN echo "deb-src http://mirrors.163.com/ubuntu/ wily-security main restricted universe multiverse" >> /etc/apt/sources.list
RUN echo "deb-src http://mirrors.163.com/ubuntu/ wily-updates main restricted universe multiverse" >> /etc/apt/sources.list
RUN echo "deb-src http://mirrors.163.com/ubuntu/ wily-proposed main restricted universe multiverse" >> /etc/apt/sources.list
RUN echo "deb-src http://mirrors.163.com/ubuntu/ wily-backports main restricted universe multiverse" >> /etc/apt/sources.list
RUN echo "APT::Get::AllowUnauthenticated 1 ;" >> /etc/apt/apt.conf
#echo "deb http://mirrors.163.com/ubuntu/ wily main restricted universe multiverse" > /etc/apt/sources.list
#echo "deb http://mirrors.163.com/ubuntu/ wily-security main restricted universe multiverse" >> /etc/apt/sources.list
#echo "deb http://mirrors.163.com/ubuntu/ wily-updates main restricted universe multiverse" >> /etc/apt/sources.list
#echo "deb http://mirrors.163.com/ubuntu/ wily-proposed main restricted universe multiverse">> /etc/apt/sources.list
#echo "deb http://mirrors.163.com/ubuntu/ wily-backports main restricted universe multiverse">> /etc/apt/sources.list
#echo "deb-src http://mirrors.163.com/ubuntu/ wily main restricted universe multiverse" >> /etc/apt/sources.list
#echo "deb-src http://mirrors.163.com/ubuntu/ wily-security main restricted universe multiverse" >> /etc/apt/sources.list
#echo "deb-src http://mirrors.163.com/ubuntu/ wily-updates main restricted universe multiverse" >> /etc/apt/sources.list
#echo "deb-src http://mirrors.163.com/ubuntu/ wily-proposed main restricted universe multiverse" >> /etc/apt/sources.list
#echo "deb-src http://mirrors.163.com/ubuntu/ wily-backports main restricted universe multiverse" >> /etc/apt/sources.list
#echo "APT::Get::AllowUnauthenticated 1 ;" >> /etc/apt/apt.conf

#更新源
RUN apt-get update
#gcc g++ make安装
RUN apt-get install -y gcc-4.9
RUN apt-get install -y g++-4.9
RUN apt-get install -y build-essential

#配置mvn环境
ADD apache-maven-3.3.9.tar.gz /usr/local
ENV M2_HOME /usr/local/apache-maven-3.3.9
ENV PATH $PATH:$JAVA_HOME/bin:$M2_HOME/bin

#jni环境
RUN cp $JAVA_HOME/include/linux/jawt_md.h $JAVA_HOME/include/
RUN cp $JAVA_HOME/include/linux/jni_md.h $JAVA_HOME/include/
