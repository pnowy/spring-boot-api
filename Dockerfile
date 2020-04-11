# build project
FROM gradle:6.3.0-jdk14 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon
RUN ls /home/gradle/src/build/libs

LABEL mainteriner="Przemek Nowak"

# run project
FROM adoptopenjdk:14-jre-hotspot
VOLUME /tmp
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENV JAVA_TOOL_OPTIONS="-Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -XX:InitialRAMPercentage=50 -XX:MaxRAMPercentage=75 -XX:+ExitOnOutOfMemoryError"
ENTRYPOINT ["java", "-jar", "/app/app.jar"]