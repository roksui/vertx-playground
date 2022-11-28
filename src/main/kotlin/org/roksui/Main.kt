package org.roksui

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.kotlin.coroutines.await

/**
 * Server Launcher
 */
suspend fun main() {
    with(Vertx.vertx()) {
        deployVerticle(MainVerticle::class.qualifiedName, DeploymentOptions().setInstances(4)).await()
    }
}