FROM gradle:6.3.0-jdk8
RUN apt-get update && apt-get install
RUN mkdir /app
WORKDIR /app
COPY . .
RUN gradle build
ENTRYPOINT ["java", "-jar", "/app/build/libs/doclink_fn-1.0-SNAPSHOT.jar"]
