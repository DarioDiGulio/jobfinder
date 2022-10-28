package com.upward.jobfinder.backoffice.http.config

import com.nbottarini.asimov.environment.Env
import com.nbottarini.asimov.lang.DetailsExt
import com.upward.jobfinder.backoffice.http.HttpApp

class HttpAppBuilder {
    private var port = 8080

    fun port(port: Int) = apply { this.port = port }

    fun portFromEnv(name: String = "PORT") = port(Env[name]?.toInt() ?: port)


    fun build(): HttpApp {
        return HttpApp(HttpApp.Config(port))
    }
}

fun httpApp(addDetails: DetailsExt<HttpAppBuilder>) = HttpAppBuilder().also(addDetails).build()
