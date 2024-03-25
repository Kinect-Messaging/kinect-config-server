FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /workspace/app

COPY mvnw .
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

# Build app
RUN mkdir -p target/extracted
RUN java -Djarmode=layertools -jar target/*.jar extract --destination target/extracted


FROM eclipse-temurin:17-jre-alpine
VOLUME /tmp

RUN addgroup -S dgroup && adduser -S duser -G dgroup
USER duser

ARG EXTRACTED=/workspace/app/target/extracted
ARG DESCRIPTION="Container for Kinect Messaging"
LABEL org.opencontainers.image.description = ${DESCRIPTION}
COPY --from=build ${EXTRACTED}/dependencies/ ./
COPY --from=build ${EXTRACTED}/spring-boot-loader/ ./
COPY --from=build ${EXTRACTED}/snapshot-dependencies/ ./
COPY --from=build ${EXTRACTED}/application/ ./

ENTRYPOINT ["java","org.springframework.boot.loader.launch.JarLauncher"]