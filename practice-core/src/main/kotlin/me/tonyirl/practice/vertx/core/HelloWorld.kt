package me.tonyirl.practice.vertx.core

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx
import org.slf4j.LoggerFactory

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
        val log = LoggerFactory.getLogger(HelloWorld::class.java)!!

        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            vertx.deployVerticle(HelloWorld())
        }
    }
}