FROM philoyui/talib-jdk:3.0

ENV LANG en_US.UTF-8

#定义时区参数
ENV TZ=Asia/Shanghai

#设置时区
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo '$TZ' > /etc/timezone

#安装必要应用
RUN yum -y install kde-l10n-Chinese glibc-common

#设置编码
RUN localedef -c -f UTF-8 -i zh_CN zh_CN.utf8

#设置环境变量
ENV LC_ALL zh_CN.utf8

ADD *.jar app.jar

EXPOSE 8080

ADD python /root/python/

RUN chmod -R 777 /root/python/

RUN echo "Asia/shanghai" > /etc/timezone;

CMD ["java", "-jar", "app.jar"]