#Build JAR FILE
FROM maven:3.6.3-openjdk-15 AS stage1
LABEL maintainer="Swapnil Kharche sdk14e@gmail.com"
WORKDIR /home/app
COPY . /home/app
RUN mvn -f /home/app/pom.xml clean package

#Create an Image
FROM openjdk:8-jdk-alpine
COPY --from=stage1 /home/app/target/springboot-restfulservices-0.0.1-SNAPSHOT.jar springboot-restfulservices.jar
ENTRYPOINT ["java","-jar","/springboot-restfulservices.jar"]

#If you have jar built in /target folder then use this to create image
#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]