#!/bin/bash
# Garante que o script do Gradle tem permissão para rodar
chmod +x ./gradlew

# Compila o projeto gerando o arquivo JAR
./gradlew build -x test

# Executa o JAR gerado (substitua 'nome-do-seu-jar.jar' pelo nome real gerado dentro da pasta build/libs)
java -jar build/libs/047Diversao-0.0.1-SNAPSHOT.jar