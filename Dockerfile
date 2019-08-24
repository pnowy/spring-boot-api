FROM openjdk:11.0.3-slim

VOLUME /tmp
COPY /build/libs/*.jar app.jar

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:InitialRAMPercentage=50", "-XX:MaxRAMPercentage=80", "-XX:+ExitOnOutOfMemoryError", "-Xss256k","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
