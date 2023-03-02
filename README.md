
## This repository consists of a GraphQL client and server.

- Client is located under src/main/java
- Server is located under src/main/kotlin





~~## This repository is for testing the vertx gRPC API with Kotlin Coroutines

This project has two parts.
- gRPC server (Kotlin)
- gRPC client (Java)

**Current Situation**: There are five unary calls (gRPC) of sending messages of serialized size around 10MB. However, there is a significant amount of latency in handling the request.

The client sends the five requests asynchronously, and on the server-side, new coroutine scopes are launched by the `vertx.dispatcher` to handle each request.

## Getting started
The server uses Vert.x and a fat-jar can be built with
```bash
./gradlew clean build
```

The client can be run in IDE.~~