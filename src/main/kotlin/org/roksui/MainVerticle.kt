package org.roksui

import com.google.protobuf.Empty
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.grpc.server.GrpcServer
import io.vertx.grpc.server.GrpcServiceBridge
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.await
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.roksui.grpc.Profiles
import org.roksui.grpc.TraceGrpcKt
import kotlin.system.measureTimeMillis

/**
 * Verticle that runs on an event loop to handle HTTP requests
 * gRPC server is mounted on the Router
 */
class MainVerticle : CoroutineVerticle() {
    override suspend fun start() {
        println("Started gRPC Server")
        val grpcServer: GrpcServer = GrpcServer.server(vertx)
        GrpcServiceBridge.bridge(TraceService(vertx)).bind(grpcServer)
        val router = setupRouter(grpcServer)

        vertx.createHttpServer()
            .requestHandler(router)
            .listen(8080)
            .await()
    }

    private fun setupRouter(grpcServer: GrpcServer): Router {
        return Router.router(vertx).apply {
            this.route()
                .consumes("application/grpc")
                .handler { rc ->
                    grpcServer.handle(rc.request())
                }
        }
    }
}

/**
 * gRPC service
 */
class TraceService(vertx: Vertx) : TraceGrpcKt.TraceCoroutineImplBase(vertx.dispatcher()) {

    override suspend fun uploadTraces(request: Profiles): Empty {
        coroutineScope {
            println("[${Thread.currentThread()}] Received request. Latency: ${System.currentTimeMillis() - request.timestamp}ms")

            println("Saved in ${measureTimeMillis {
                val profiles = request.toProfilesModel()
            }}")
        }
        return Empty.newBuilder().build()
    }
}

data class Profiles(
    val items: List<Profile>,
    val timestamp: Long
)

fun Profiles.toProfilesModel() = Profiles(
    items = itemsList.map { profile -> profile.toProfileModel() },
    timestamp = timestamp
)

fun org.roksui.grpc.Profile.toProfileModel() = Profile(
    text = text
)

data class Profile(
    val text: String
)