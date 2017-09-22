FROM maven:3-jdk-8 as build
RUN mkdir /code
COPY pom.xml /code/
COPY src/ /code/src/
WORKDIR /code
RUN mvn package

FROM openjdk:8-jre-alpine
RUN mkdir -p /code
COPY --from=build /code/target/java-doesnt-suck-*.jar /code/java-doesnt-suck.jar
CMD ["java", "-jar", "/code/java-doesnt-suck.jar"]
