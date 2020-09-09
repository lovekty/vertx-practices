package me.tonyirl.practice.vertx.example.eventbus

import io.vertx.core.AbstractVerticle
import io.vertx.core.Promise
import org.slf4j.LoggerFactory

/**
 * @author tony.zhuby
 * @date 2020/9/5
 */

val log = LoggerFactory.getLogger(EventBusDemoMain::class.java)!!

class EventBusDemoMain : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        vertx.deployVerticle(EventBusDemoConsumer())
        vertx.deployVerticle(EventBusDemoProducer())
        promise?.complete()
    }
}

class EventBusDemoConsumer : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        vertx.eventBus().localConsumer<String>("eb-addr1") { msg ->
            msg.body()?.let { log.info("received msg body:${it}") }
        }
        promise?.complete()
    }
}

class EventBusDemoProducer : AbstractVerticle() {
    override fun start(promise: Promise<Void>?) {
        var count = 0
        vertx.setPeriodic(1000) {
            vertx.eventBus().send("eb-addr1", "hello count:${count++}")
            if (count == 10) {
                vertx.cancelTimer(it)
            }
        }
        promise?.complete()
    }
}