FROM philoyui/talib-jdk:6.0

ENV LANG=zh_CN.UTF-8 \
LANGUAGE=zh_CN:zh \
LC_ALL=zh_CN.UTF-8

ADD *.jar app.jar

EXPOSE 8080

ADD . /root/python/

COPY product/base.py /root/python/

RUN chmod -R 777 /root/python/

CMD ["java", "-jar", "app.jar","--spring.profiles.active=product"]