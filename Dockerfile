# Start with a base image containing Java runtime
FROM java:8

# Add Author info
LABEL maintainer="tries1@naver.com"

# Add a volume to /tmp
VOLUME /tml

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=build/libs/spring-reactive-jpa-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} spring-reactive-jpa.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/spring-reactive-jpa.jar"]