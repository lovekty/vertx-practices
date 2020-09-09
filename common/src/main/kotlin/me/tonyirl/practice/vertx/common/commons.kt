package me.tonyirl.practice.vertx.common

import org.slf4j.LoggerFactory
import java.net.URL

/**
 * @author tony.zhuby
 */

val log = LoggerFactory.getLogger("common-out")!!

fun classpathResource(file: String): URL? {
    val cl = Thread.currentThread().contextClassLoader ?: LoggerFactory::class.java.classLoader
    return cl?.getResource(file)
}

fun classpathResourcePath(file: String): String? {
    return classpathResource(file)?.path
}