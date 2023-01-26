## VR Mini Authorizer
#### How to get the project source
- Git
  https://github.com/igorlimeira/mini-autorizador

## Prerequisites
- JDK 17+
- Maven 3.6.3+
- Docker
- Spring Boot 3.0.2


## Running the Services (on root directory)
- For unitary testing:
```bash
mvn test
```
- Docker Build Image
```bash
docker build -t vr-mini-autorizador .
```
- Docker Compose
```bash
docker compose up
```