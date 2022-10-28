package com.upward.jobfinder.site.http

import com.nbottarini.asimov.environment.Env
import com.upward.jobfinder.site.http.config.httpApp

fun main() {
    Env.addSearchPath("./api")

    val app = httpApp {
        portFromEnv()
    }
    app.start()
}
