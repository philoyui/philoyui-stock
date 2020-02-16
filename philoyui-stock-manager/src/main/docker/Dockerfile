FROM philoyui/talib-jdk:1.0

ENV LANG en_US.UTF-8

ADD *.jar app.jar

EXPOSE 8080

ADD python /root/

CMD ["java", "-jar", "app.jar"]