FROM openjdk:15
WORKDIR /app/
COPY src/ ./
COPY judge.toml ./
RUN javac *.java