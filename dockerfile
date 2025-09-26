# Etapa 1: Build do projeto
FROM eclipse-temurin:17-jdk AS build

# Define diretório de trabalho
WORKDIR /app

# Copia os arquivos do Maven Wrapper e do projeto
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src ./src

# Dá permissão para o mvnw
RUN chmod +x mvnw

# Configura Maven Compiler Plugin para JDK compatível
# (opcional se estiver no pom.xml, mas reforça)
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagem final para execução
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia o jar gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta da aplicação (ajuste se necessário)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
