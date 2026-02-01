# ===============================
# ESTÁGIO 1: Build (Compilação)
# ===============================
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copia o arquivo de dependências primeiro (para aproveitar cache)
COPY pom.xml .
# Baixa as dependências (demora só na primeira vez)
RUN mvn dependency:go-offline

# Copia o código fonte e compila
COPY src ./src
RUN mvn clean package -DskipTests

# ===============================
# ESTÁGIO 2: Run (Execução Leve)
# ===============================
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Pega apenas o arquivo .jar gerado no estágio anterior
ARG JAR_FILE=target/*.jar
COPY --from=build /app/${JAR_FILE} app.jar

# Expõe a porta padrão do Spring
EXPOSE 8080

# Comando para iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]
