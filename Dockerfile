
# Imagen base
FROM eclipse-temurin:17-jdk-alpine

# Crear directorio de la app
WORKDIR /app

# Copiar el jar generado por Maven
COPY target/*.jar app.jar

# Exponer el puerto 8080
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
