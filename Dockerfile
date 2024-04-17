# Usa la imagen oficial de OpenJDK 17 para aprovechar las optimizaciones y características de seguridad
FROM openjdk:17-slim AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos necesarios para construir el proyecto
COPY mvnw pom.xml ./
COPY .mvn .mvn
COPY src ./src

# Construye el proyecto, salta los tests
RUN ./mvnw clean package -DskipTests

# Segunda etapa del build: Usa nuevamente una imagen limpia para reducir tamaño
FROM openjdk:17-slim

# Copia el jar desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto 8080 para acceder a la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "app.jar"]
