FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 5005
# Використовуємо офіційний образ Java з вказаною версією
#FROM adoptopenjdk:17
#
## Встановлюємо робочий каталог у контейнері
#WORKDIR /app
#
## Копіюємо скомпільований JAR-файл вашого додатку в робочий каталог контейнера
#COPY target/rick-and-morty-app-0.0.1-SNAPSHOT.jar app.jar
#
## Виконуємо команду для запуску додатку
#CMD ["java", "-jar", "app.jar"]

