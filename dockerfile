# Fase 1: Construção da aplicação
FROM eclipse-temurin:21-jdk AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia todos os arquivos do projeto para o diretório de trabalho
COPY . .

# Concede permissão de execução ao script mvnw
RUN chmod +x mvnw

# Realiza o build do projeto, ignorando os testes
RUN ./mvnw clean package -DskipTests

# Fase 2: Criação da imagem final para execução
FROM eclipse-temurin:21-jdk

# Define diretório de trabalho da imagem final
WORKDIR /app

# Transfere o arquivo JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão usada pelo Spring Boot
EXPOSE 8080

# Define o comando padrão para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
