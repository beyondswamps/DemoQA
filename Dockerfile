FROM eclipse-temurin:17-jdk-jammy

WORKDIR /tests-app

COPY . .

ADD

CMD ["./gradlew", "clean", "test"]