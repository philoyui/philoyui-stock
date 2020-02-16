FROM philoyui/talib-jdk:2.0

ENV LANG en_US.UTF-8

ADD *.jar app.jar

EXPOSE 8080

ADD python /root/python/

RUN chmod -R 777 /root/python/

CMD ["java", "-jar", "app.jar"]