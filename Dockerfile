FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/blog-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
RUN echo $(ls -1 /usr/src/app)

ENTRYPOINT ["java","-jar","app.jar"]