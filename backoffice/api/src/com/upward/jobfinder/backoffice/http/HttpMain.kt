package com.upward.jobfinder.backoffice.http

import com.nbottarini.asimov.environment.Env
import com.upward.jobfinder.backoffice.http.config.httpApp

fun main() {
    Env.addSearchPath("./api")

    val app = httpApp {
        portFromEnv()
    }
    app.start()
}
