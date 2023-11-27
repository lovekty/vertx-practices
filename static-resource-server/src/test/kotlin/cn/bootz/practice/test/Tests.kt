package cn.bootz.practice.test

import io.vertx.kotlin.core.json.get
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.jsonArrayOf
import io.vertx.kotlin.core.json.obj
import org.slf4j.LoggerFactory
import kotlin.test.Test

class JsonTests {

    val log = LoggerFactory.getLogger(JsonTests::class.java)

    @Test
    fun testJson() {
        val json = json {
            obj(
                "foo" to 1,
                "bar" to "hello"
            )
        }
        val arr = jsonArrayOf("1", 2, json)

        val foo: Int = json["foo"]

        log.info("json obj: {}", json.encodePrettily())

        log.info("json arr: {}", arr.encodePrettily())

    }
}