FROM openjdk:8
ADD target/exchangerate-1.0.0-SNAPSHOT.jar exchangerate-1.0.0-SNAPSHOT.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","exchangerate-1.0.0-SNAPSHOT.jar"]