# Fase 1: Construcción del WAR
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar el pom.xml y descargar las dependencias primero (cache)
COPY pom.xml .
RUN mvn dependency:resolve

# Copiar el resto del proyecto
COPY . .

# Construir el archivo WAR
RUN mvn clean package -DskipTests

# Fase 2: Imagen final con Tomcat
FROM tomcat:9.0-jdk17

WORKDIR /usr/local/tomcat

# Eliminar las aplicaciones de ejemplo de Tomcat
RUN rm -rf webapps/*

# Copiar el WAR compilado
COPY --from=builder /app/target/RadioLatino.war webapps/RadioLatino.war

# Exponer el puerto 8080
EXPOSE 8080

# Iniciar Tomcat
CMD ["catalina.sh", "run"]
