package com.newrelic.rdstest.rds

import com.newrelic.api.agent.Trace
import com.newrelic.rdstest.model.CustomerTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class DbLoader(val config: HikariConfig) {
    var count = 0
    lateinit var db: Database

    @Trace
    fun initDatabase(): Database {
        val ds = HikariDataSource(config)
        db = Database.connect(ds)
        println("I connected with this jdbcUrl: ${config.jdbcUrl}, user ${config.username} pass ${config.password}")
        return db
    }


    @Trace(dispatcher = true)
    fun generateLoad() {
        transaction(db) {
            // drop tables
            SchemaUtils.drop(CustomerTable)
            // create tables if they do not exist
            SchemaUtils.createMissingTablesAndColumns(CustomerTable)
        }
        count += 1
        transaction(db) {
            CustomerTable.insert {
                it[id] = "ID-$count"
                it[name] = "name-$count"
            }
            CustomerTable.selectAll().limit(1).orderBy(CustomerTable.id, SortOrder.DESC).map { println("inserted $it") }
            println("SIZE: ${CustomerTable.selectAll().count()}")
        }
    }

}