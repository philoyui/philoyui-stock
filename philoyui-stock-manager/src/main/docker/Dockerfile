FROM philoyui/talib-jdk:3.0

ENV LANG en_US.UTF-8

ADD *.jar app.jar

EXPOSE 8080

ADD python /root/python/

RUN chmod -R 777 /root/python/

RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

CMD ["java", "-jar", "app.jar"]