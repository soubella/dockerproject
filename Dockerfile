From openjdk:8
copy ./target/dockerproject-0.0.1-SNAPSHOT.jar dockerproject-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","dockerproject-0.0.1-SNAPSHOT.jar"]