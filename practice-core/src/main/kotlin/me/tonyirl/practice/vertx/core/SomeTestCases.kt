package me.tonyirl.practice.vertx.core

import io.vertx.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.slf4j.LoggerFactory
import java.util.concurrent.CountDownLatch

/**
 * @author tony.zhuby
 */
@ExtendWith(VertxExtension::class)
class SomeTestCases {

    companion object {
        val log = LoggerFactory.getLogger(SomeTestCases::class.java)!!
    }

    @Test
    fun testTimer(vertx: Vertx, context: VertxTestContext) {
        val cdl = CountDownLatch(2)
        vertx.setTimer(1000) {
            log.info("timer id:${it} and count down ${cdl.count} now")
            cdl.countDown()
        }
        vertx.setTimer(2000) {
            log.info("timer id:${it} and count down ${cdl.count} now")
            cdl.countDown()
        }
        cdl.await()
        log.info("count down to 0")
        context.completeNow()
    }

    @Test
    fun testPeriodic(vertx: Vertx, context: VertxTestContext) {
        val cdl = CountDownLatch(10)
        vertx.setPeriodic(500) {
            log.info("timer id:${it} and count down ${cdl.count} now")
            cdl.countDown()
        }
        cdl.await()
        log.info("count down to 0")
        context.completeNow()

    }


}