package me.tonyirl.practice.vertx.example.test

import io.vertx.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import me.tonyirl.practice.vertx.common.classpathResourcePath
import me.tonyirl.practice.vertx.common.log
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.CountDownLatch

/**
 * @author tony.zhuby
 */
@ExtendWith(VertxExtension::class)
class TestExamples {

    @Test
    fun testTimer(vertx: Vertx, context: VertxTestContext) {
        val cdl = CountDownLatch(2)
        val start = System.currentTimeMillis()
        vertx.setTimer(1000) {
            log.info("timer id:${it} duration:${System.currentTimeMillis() - start}")
            cdl.countDown()
        }
        vertx.setTimer(2000) {
            log.info("timer id:${it} duration:${System.currentTimeMillis() - start}")
            cdl.countDown()
        }
        cdl.await()
        log.info("count down to 0")
        context.completeNow()
    }

    @Test
    fun testPeriodic(vertx: Vertx, context: VertxTestContext) {
        val cdl = CountDownLatch(10)
        val start = System.currentTimeMillis()
        vertx.setPeriodic(500) {
            log.info("timer id:${it} count down:${cdl.count} duration:${System.currentTimeMillis() - start}")
            cdl.countDown()
            if (cdl.count == 0L) {
                vertx.cancelTimer(it)
            }
        }
        cdl.await()
        log.info("count down to 0")
        context.completeNow()
    }

    @Test
    fun testFilesystem(vertx: Vertx, context: VertxTestContext) {
        val cdl = CountDownLatch(1)
        val fs = vertx.fileSystem()
        fs.readFile(classpathResourcePath("fsdemo.txt")) { ar ->
            if (ar.succeeded()) {
                val buffer = ar.result()
                log.info(buffer.getString(0, buffer.length()))
                cdl.countDown()
            } else {
                log.error("read error", ar.cause())
                cdl.countDown()
            }
        }
        cdl.await()
        context.completeNow()
    }


}