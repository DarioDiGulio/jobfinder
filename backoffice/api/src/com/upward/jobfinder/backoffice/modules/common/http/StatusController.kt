package com.upward.jobfinder.backoffice.modules.common.http

import com.upward.jobfinder.backoffice.modules.common.base.http.jsonObj
import io.javalin.Javalin
import io.javalin.http.Context


class StatusController(httpServer: Javalin) {
    init {
        httpServer.get("/status", ::status)
    }

    private fun status(ctx: Context) {
        ctx.jsonObj(
            "name" to "jobfinder-backoffice api",
            "status" to "OK",
        )
    }
}
