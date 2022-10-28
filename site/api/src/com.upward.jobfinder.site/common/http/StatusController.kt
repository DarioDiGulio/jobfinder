package com.upward.jobfinder.site.common.http

import com.upward.jobfinder.site.common.base.http.jsonObj
import io.javalin.Javalin
import io.javalin.http.Context


class StatusController(httpServer: Javalin) {
    init {
        httpServer.get("/status", ::status)
    }

    private fun status(ctx: Context) {
        ctx.jsonObj(
            "name" to "jobfinder-site api",
            "status" to "OK",
        )
    }
}
