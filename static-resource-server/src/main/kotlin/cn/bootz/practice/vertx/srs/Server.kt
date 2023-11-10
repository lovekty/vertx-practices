package cn.bootz.practice.vertx.srs

import io.vertx.core.AbstractVerticle
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.StaticHandler

class Server : AbstractVerticle() {
    override fun start() {
        val router = Router.router(vertx)
        router.route("/*").handler(StaticHandler.create("public"))
        vertx.createHttpServer().requestHandler(router).listen(8080)
    }
}