package me.tonyirl.practice.vertx.web.handler

import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.common.template.TemplateEngine
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import me.tonyirl.practice.vertx.common.classpathResourcePath
import me.tonyirl.practice.vertx.common.log

/**
 * @author tony.zhuby
 * @date 2020/8/30
 */

class IndexPageRouterHandler(override val router: Router, private val engine: TemplateEngine) : RouterHandler {

    private fun indexPage(ctx: RoutingContext) {
        engine.render(json {
            obj(
                "name" to "zhubaiyang",
                "age" to 31,
                "type" to "admin"
            )
        }, classpathResourcePath("static/index.html")) {
            if (it.succeeded()) {
                ctx.response().end(it.result())
            } else {
                ctx.fail(it.cause())
                log.error("ex in engine render", it.cause())
            }
        }
    }

    override fun setupRouter() {
        router.get("/").handler { indexPage(it) }
    }
}
