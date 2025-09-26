# Etapa 1: Build do projeto usando JDK
FROM eclipse-temurin:21-jdk AS build

# Define diretório de trabalho
WORKDIR /app

# Copia todos os arquivos do projeto
COPY . .

# Garante que o Maven Wrapper tem permissão de execução
RUN chmod +x ./mvnw

# Confirma versão do Java
RUN ./mvnw -v

# Build do projeto ignorando testes
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final enxuta usando JRE
FROM eclipse-temurin:21-jre

# Diretório de trabalho na imagem final
WORKDIR /app

# Copia o jar gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Comando para rodar o jar
ENTRYPOINT ["java","-jar","app.jar"]
