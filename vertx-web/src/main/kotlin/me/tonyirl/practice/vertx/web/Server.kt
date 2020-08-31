package me.tonyirl.practice.vertx.web

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.ext.web.Router
import io.vertx.ext.web.templ.thymeleaf.ThymeleafTemplateEngine
import me.tonyirl.practice.vertx.web.handler.IndexPageRouterHandler

/**
 * @author tony.zhuby
 * @date 2020/8/16
 */
class Server : AbstractVerticle() {

    override fun start(promise: Promise<Void>?) {
        vertx.createHttpServer()
            .requestHandler(router())
            .listen(8080) {
                if (it.succeeded()) {
                    promise?.complete()
                } else {
                    promise?.fail(it.cause())
                }
            }
    }

    private fun router(): Router {
        val router = Router.router(vertx)
        IndexPageRouterHandler(router, ThymeleafTemplateEngine.create(vertx)).setupRouter()
        return router
    }
}