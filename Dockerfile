# https://spring.io/guides/gs/spring-boot-docker
# https://www.geeksforgeeks.org/springboot/how-to-dockerize-a-spring-boot-application-with-maven/
# https://kscodes.com/spring-boot/multi-stage-docker-builds-for-production-ready-spring-boot-images/

# ---------- Stage 1: Build ----------
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy only pom first (better caching)
COPY pom.xml .
RUN mvn -B -q dependency:go-offline

# Copy source
COPY src ./src

# Build the jar
RUN mvn -B clean package -DskipTests


# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:17-jre

RUN groupadd -r spring && useradd -r -g spring spring

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

USER spring:spring

ENTRYPOINT ["java","-jar","/app/app.jar"]


