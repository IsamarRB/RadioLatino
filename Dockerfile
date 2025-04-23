# Fase 1: Construcción del proyecto con Maven
FROM maven:3.8.5-openjdk-17 AS builder

# Establece el directorio de trabajo para la construcción
WORKDIR /app

# Copia los archivos de dependencias y resuélvelos primero (mejora el caching de Docker)
COPY pom.xml .
RUN mvn dependency:resolve

# Copia el resto del proyecto
COPY . .

# Compila y construye el archivo WAR
RUN mvn clean package -DskipTests
# Resultado esperado: target/RadioLatino-1.0-SNAPSHOTgit.war

# Fase 2: Imagen final con Tomcat
FROM tomcat:9.0-jdk17

# Establece el directorio de trabajo en Tomcat
WORKDIR /usr/local/tomcat

# Elimina las apps de ejemplo por defecto (ahora sí, estamos dentro de la imagen Tomcat)
RUN rm -rf webapps/*

# Copia el .war generado desde la fase de build
COPY --from=builder /app/target/radiolatino.war webapps/ROOT.war

# Exponer el puerto para acceder a la aplicación
EXPOSE 8080

# Comando de arranque
CMD ["catalina.sh", "run"]
