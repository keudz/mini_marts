# Bước 1: Build dự án
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
# Thêm cờ ép dùng UTF-8 để Maven không bắt lỗi ký tự lạ
RUN mvn clean package -DskipTests -Dproject.build.sourceEncoding=UTF-8 -Dproject.reporting.outputEncoding=UTF-8

# Bước 2: Chạy ứng dụng
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]