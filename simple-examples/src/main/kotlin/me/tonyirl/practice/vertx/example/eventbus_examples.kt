package me.tonyirl.practice.vertx.example.eventbus

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import me.tonyirl.practice.vertx.common.log

/**
 * @author tony.zhuby
 * @date 2020/9/5
 */


class ExampleMain : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        vertx.deployVerticle(Consumer())
        vertx.deployVerticle(Consumer2())
//        vertx.deployVerticle(Producer())
//        vertx.deployVerticle(Producer2())
        promise?.complete()
    }

    override fun stop(promise: Promise<Void>?) {

    }
}

class Consumer : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        vertx.eventBus().localConsumer<String>("eb-addr1") { msg ->
            msg.body()?.let { log.info("consumer received msg body:${it}") }
        }
        promise?.complete()
    }
}

class Producer : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        var count = 0
        vertx.setPeriodic(1000) {
            vertx.eventBus().send("eb-addr1", "producer hello count:${count++}")
            if (count == 10) {
                vertx.cancelTimer(it)
            }
        }
        promise?.complete()
    }
}

class Consumer2 : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        vertx.eventBus().localConsumer<String>("eb-addr1") { msg ->
            msg.body()?.let { log.info("consumer2 received msg body:${it}") }
        }
        promise?.complete()
    }
}

class Producer2 : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        var count = 0
        vertx.setPeriodic(1600) {
            vertx.eventBus().send("eb-addr1", "producer2 hello count:${count++}")
            if (count == 20) {
                vertx.cancelTimer(it)
            }
        }
        promise?.complete()
    }
}