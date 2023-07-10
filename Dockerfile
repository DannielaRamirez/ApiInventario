# Imagen base con soporte para Java y Maven
FROM maven:3.8.7-eclipse-temurin-19

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar los archivos de configuración del proyecto
COPY pom.xml .
COPY src ./src

# Empaquetar la aplicación usando Maven
RUN ./mvnw package -DskipTests

# Copiar el archivo JAR generado durante la fase de construcción
COPY ./target/SistemaInventario-0.0.1-SNAPSHOT.jar ./SistemaInventario-0.0.1-SNAPSHOT.jar

# Exponer el puerto en el contenedor
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "SistemaInventario-0.0.1-SNAPSHOT.jar"]

