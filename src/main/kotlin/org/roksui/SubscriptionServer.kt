package org.roksui

import graphql.GraphQL
import graphql.execution.SubscriptionExecutionStrategy
import graphql.schema.DataFetchingEnvironment
import graphql.schema.idl.RuntimeWiring.newRuntimeWiring
import graphql.schema.idl.SchemaGenerator
import graphql.schema.idl.SchemaParser
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import io.vertx.ext.web.handler.graphql.ApolloWSHandler
import io.vertx.ext.web.handler.graphql.GraphQLHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.launch
import org.reactivestreams.Publisher
import org.roksui.exception.GraphQLExceptionHandler
import org.roksui.exception.SomeException
import java.util.concurrent.CompletableFuture

class SubscriptionServer : CoroutineVerticle() {
    private lateinit var detectionsPublisher: DetectionsPublisher
    private val observableList: ObservableList = ObservableList()

    override suspend fun start() {
        val router = Router.router(vertx)

        router.route("/graphql")
            .handler(ApolloWSHandler
                .create(setupGraphQL())
                .connectionHandler { ws ->
                    launch {
                        val remoteAddress = ws.remoteAddress()
                        val socketAddress = "${remoteAddress.host()}:${remoteAddress.port()}"
                        println("A websocket has connected. SocketAddress: $socketAddress")
                    }
                }
                .messageHandler { msg ->
                    launch {
                        val remoteAddress = msg.serverWebSocket().remoteAddress()
                        val socketAddress = "${remoteAddress.host()}:${remoteAddress.port()}"
                        println("WebSocket message received - SocketAddress: $socketAddress")
                        
                    }
                }
                .endHandler { ws ->
                    launch {
                        val remoteAddress = ws.remoteAddress()
                        val socketAddress = "${remoteAddress.host()}:${remoteAddress.port()}"
                        println("WebSocket connection has ended. SocketAddress: $socketAddress")
                    }
                }
            )
            .handler(BodyHandler.create())
            .handler(GraphQLHandler.create(setupGraphQL()))

        val opts: HttpServerOptions = HttpServerOptions()
            .addWebSocketSubProtocol("graphql-ws")
            .addWebSocketSubProtocol("graphql-transport-ws")
    
        vertx.createHttpServer(opts)
            .requestHandler(router)
            .listen(8080)
            .await()

        println("SubscriptionServer has started.")
    }
    
    /**
     * Sets up GraphQL schema and dataFetchers.
     */
    private fun setupGraphQL(): GraphQL {
        val schema = vertx.fileSystem().readFileBlocking("schema.graphqls").toString()
        val schemaParser = SchemaParser()
        val typeDefinitionRegistry = schemaParser.parse(schema)
        val runtimeWiring = newRuntimeWiring()
            .type("Query") {
                it.dataFetcher("projects", this::projectsFetcher)
            }
            .type("Mutation") {
                it.dataFetcher("addProject", this::addProjectFetcher)
            }
            .type("Subscription") {
                it.dataFetcher("newProject", this::newProjectFetcher)
            }
            .build()

        val schemaGenerator = SchemaGenerator()
        val graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring)

        val execStrategy = SubscriptionExecutionStrategy(GraphQLExceptionHandler())
        
        return GraphQL
            .newGraphQL(graphQLSchema)
            .subscriptionExecutionStrategy(execStrategy)
            .build()
    }
    
    /**
     * Gets all the projects as a list of Strings.
     */
    private fun projectsFetcher(dfe: DataFetchingEnvironment): CompletableFuture<List<String>> {
        val completableFuture: CompletableFuture<List<String>> = CompletableFuture()

        launch(vertx.dispatcher()) {
            completableFuture.complete(observableList.projects)
        }
        return completableFuture
    }
    
    /**
     * Adds a new project as a String.
     */
    private fun addProjectFetcher(dfe: DataFetchingEnvironment): CompletableFuture<Boolean> {
        val completableFuture: CompletableFuture<Boolean> = CompletableFuture()

        val name = dfe.getArgument<String>("name")
        launch(vertx.dispatcher()) {
            observableList.add(name)
            completableFuture.complete(true)
        }
        return completableFuture
    }
    
    /**
     * Returns a publisher for streaming new projects when they are added.
     * This is used for the GraphQL Subscription DataFetcher.
     */
    private fun newProjectFetcher(dfe: DataFetchingEnvironment): Publisher<String> {
        // If some condition fails, I want this method to throw a custom error.
        // And close the websocket connection.
        // But currently, the client only receives an ERROR and a COMPLETE websocket message, and the connection lives.
        if (true)  {
            throw SomeException("Some exception occurred!")
        }
        detectionsPublisher = DetectionsPublisher(observableList)
        
        return detectionsPublisher.getPublisher()
    }
}