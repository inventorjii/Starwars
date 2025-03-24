# Use AdoptOpenJDK 17 as base image
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged JAR file into the container at the specified working directory
COPY target/starwars-0.0.1-SNAPSHOT.jar /app/starwars-0.0.1-SNAPSHOT.jar

#Download and extract Consul
# RUN apt-get update && apt-get install -y wget unzip && rm -rf /var/lib/apt/lists/*
# RUN wget https://releases.hashicorp.com/consul/1.11.0/consul_1.11.0_linux_amd64.zip && \
#     unzip consul_1.11.0_linux_amd64.zip && \
#     mv consul /usr/local/bin/ && \
#     rm consul_1.11.0_linux_amd64.zip

# Expose the port that your Spring Boot application uses
EXPOSE 8080

# Start Consul agent and then your Spring Boot application
CMD java -jar starwars-0.0.1-SNAPSHOT.jar