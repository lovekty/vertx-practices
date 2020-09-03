package me.tonyirl.practice.vertx.web.handler

import io.vertx.ext.web.Router

/**
 * @author tony.zhuby
 */
interface RouterHandler {
    val router: Router
    fun setupRouter()
}
