package com.newrelic.rdstest.rds

import com.zaxxer.hikari.HikariConfig

object ExampleConfiguration {
    val config = HikariConfig().apply {
        jdbcUrl =
            "jdbc:mysql://database-1.ccsudaab15y0.us-west-2.rds.amazonaws.com:3306/testdb1"
        username = "admin"
        password = "NewRelic2022"
        driverClassName = "com.mysql.cj.jdbc.Driver"
    }
}