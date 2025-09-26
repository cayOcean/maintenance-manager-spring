# Etapa 1: Build do projeto
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Copia todos os arquivos do projeto
COPY . .

# Garante permissões para o Maven Wrapper
RUN chmod +x ./mvnw

# Build do projeto ignorando os testes
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final enxuta usando JRE
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o jar do build anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
