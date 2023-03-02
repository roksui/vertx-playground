package org.roksui

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.await
import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val vertx = Vertx.vertx()

    vertx.deployVerticle(SubscriptionServer::class.java,
        DeploymentOptions()
            .setInstances(1)
    ).await()
    
}
