FROM centos-jdk8:1.1

ENV LANG en_US.UTF-8

ADD python /root/python/

RUN chmod -R 777 /root/python/

ADD *.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]