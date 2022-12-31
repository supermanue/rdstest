package com.newrelic.rdstest.model

import org.jetbrains.exposed.sql.Table

object CustomerTable : Table("customer") {
    val id = varchar("id", 32)
    val name = varchar("name", 2048)
    override val primaryKey: PrimaryKey = PrimaryKey(id)
}