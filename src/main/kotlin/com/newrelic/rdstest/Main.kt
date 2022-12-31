package com.newrelic.rdstest

import com.newrelic.rdstest.rds.DbLoader
import com.newrelic.rdstest.rds.ExampleConfiguration


suspend fun main(args: Array<String>) {
    println("hello world")

    val dbLoader = DbLoader(ExampleConfiguration.config)

    dbLoader.initDatabase()

    var i = 0
    while (true) {
        dbLoader.generateLoad()

        println(i)
        i += 5
        Thread.sleep(5000)
    }
    println("goodbye world")
}
