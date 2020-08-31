package me.tonyirl.practice.vertx.web.handler

import io.vertx.ext.web.Router

/**
 * @author tony.zhuby
 */
abstract class RouterHandler(protected val router: Router) {
    abstract fun setupRouter()
}