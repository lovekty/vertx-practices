package me.tonyirl.practice.vertx.web

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise

/**
 * @author tony.zhuby
 * @date 2020/8/29
 */
class AppMain : AbstractVerticle() {

    override fun start(promise: Promise<Void>?) {
        vertx.deployVerticle(Server()) {
            if (it.succeeded()) {
                promise?.complete()
            } else {
                promise?.fail(it.cause())
            }
        }
    }
}