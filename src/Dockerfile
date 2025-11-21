
#1 Etapa de construcción

FROM gradle:8.7.0-jdk21 AS build
WORKDIR /app

# Copia todos los archivos del proyecto (incluye build.gradle, settings.gradle y src/)
COPY . .

# Da permisos al gradlew (por si no los tiene)
RUN chmod +x gradlew

# Compila el proyecto
RUN ./gradlew clean build -x test

#2) Etapa final (runtime)
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copia solo el JAR construido
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto donde corre el microservicio
EXPOSE 9090

# Comando para ejecutar el servicio
ENTRYPOINT ["java", "-jar", "app.jar"]
