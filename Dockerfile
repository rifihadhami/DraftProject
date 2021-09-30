
#FROM maven:3.6.3 AS MAVEN_BUILD


#COPY pom.xml /build/
#COPY src /build/src/

#WORKDIR /build/
#RUN mvn package -Dmaven.test.skip=true

#FROM openjdk:11

#WORKDIR /app

#COPY --from=MAVEN_BUILD /build/target/user-mysql.jar /app/

#ENTRYPOINT ["java", "-jar", "user-mysql.jar"]

FROM openjdk:11
ADD target/user-mysql.jar user-mysql.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "user-mysql.jar"] 
