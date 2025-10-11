# Usar una imagen base con Java 11
FROM openjdk:11-jre-slim

# Opcional: establecer un directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el jar generado al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# Exponer el puerto que usa tu aplicación (por ejemplo 8080)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]
