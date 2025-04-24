# Fase 1: Build de la app
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar archivos necesarios primero (para aprovechar la cache de dependencias)
COPY pom.xml .
RUN mvn dependency:resolve

# Luego el resto del código
COPY . .

# Compilar y empacar el WAR
RUN mvn clean package -DskipTests

# Fase 2: Imagen final con Tomcat
FROM tomcat:9.0-jdk17

WORKDIR /usr/local/tomcat

# Limpia apps por defecto
RUN rm -rf webapps/*

# Copia el WAR desde el builder
COPY --from=builder /app/target/*.war webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
