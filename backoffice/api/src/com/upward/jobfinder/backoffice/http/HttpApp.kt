package com.upward.jobfinder.backoffice.http

import com.upward.jobfinder.backoffice.modules.common.http.StatusController
import io.javalin.Javalin
import io.javalin.http.Context
import org.slf4j.LoggerFactory


class HttpApp(private val config: Config) {
    private val httpServer: Javalin
    private val logger = LoggerFactory.getLogger(javaClass.simpleName)

    init {
        System.setProperty("org.slf4j.simpleLogger.showShortLogName", "true")
        httpServer = Javalin.create { config ->
            config.showJavalinBanner = false
            config.requestLogger.http(::logRequest)
        }
        registerControllers()
        registerExceptionHandlers()
        registerMiddlewares()
    }

    private fun registerControllers() {
        StatusController(httpServer)
    }


    private fun registerExceptionHandlers() {}

    private fun registerMiddlewares() {}

    private fun logRequest(ctx: Context, executionTimeMs: Float) {
        val sb = StringBuilder()
        sb.append(ctx.req().method)
        sb.append(" " + ctx.fullUrl())
        sb.append(" Response: " + ctx.res().status)
        sb.append(" - " + ctx.res().getHeader("content-type"))
        sb.append(" (" + executionTimeMs + "ms)")
        if (ctx.res().status >= 300 || ctx.res().status < 200) {
            sb.appendLine()
            sb.append("Request Body: " + ctx.body())
            sb.appendLine()
            sb.append("Response Body: " + ctx.result())
            logger.error(sb.toString())
            return
        }
        logger.info(sb.toString())
    }

    fun start(): Javalin = httpServer.start()

    fun stop() = httpServer.stop()

    data class Config(
        val port: Int,
    )
}
