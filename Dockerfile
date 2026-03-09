# Bước 1: Build dự án bằng Maven (Giữ nguyên hoặc đổi sang image maven mới hơn)
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Bước 2: Chạy ứng dụng (SỬA ĐOẠN NÀY)
# Thay vì openjdk:17-jdk-slim, hãy dùng eclipse-temurin hoặc amazoncorretto
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]