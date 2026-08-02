FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

# Give execute permission to Maven wrapper
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/crudDemo-0.0.1-SNAPSHOT.jar"]