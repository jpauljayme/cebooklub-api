#FROM openjdk:17-jdk-oracle
#VOLUME /tmp
#
#ARG EXTRACTED=target/extracted
#COPY ${EXTRACTED}/dependencies/ ./
#COPY ${EXTRACTED}/spring-boot-loader/ ./
#COPY ${EXTRACTED}/snapshot-dependencies/ ./
#COPY ${EXTRACTED}/application/ ./
#ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]

FROM openjdk:17-jdk-oracle as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-jdk-oracle
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","dev.mayhm.cebooklubapi.CebooklubApiApplication"]