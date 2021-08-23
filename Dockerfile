FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/encurtador_url-0.0.1-SNAPSHOT.jar encurtador.jar
ENTRYPOINT ["java","-jar","/encurtador.jar"]
