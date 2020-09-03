package me.tonyirl.practice.vertx.core

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import io.vertx.core.Vertx

/**
 * @author tony.zhuby
 * @date 2020/9/4
 */

class HelloWorld : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        println("Hello world")
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