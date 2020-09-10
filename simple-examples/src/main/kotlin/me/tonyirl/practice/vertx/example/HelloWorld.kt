package me.tonyirl.practice.vertx.example

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx
import me.tonyirl.practice.vertx.common.log

/**
 * @author tony.zhuby
 * @date 2020/9/4
 */

class HelloWorld : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        log.info("Hello world")
        promise?.complete()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            vertx.deployVerticle(HelloWorld())
        }
    }
}