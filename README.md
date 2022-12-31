# rdstest

testing RDS entities

How does it work:
main:

- deletes existing tables (if any) and recreates them
- generates load on that tables until the app is manually stopped:
    - add rows
    - select latest
    - count elements

Things to configure:

- Main / dbInstanceIdentifier: this is the name of your DB. It will be used by RDS
- JdbcExample / initDatabase(): configuration for your DB
    - note that the jdbcUrl MUST include your RDS DB and the created one. This is a bit confusing, sorry.
      Example: `jdbc:mysql://myRdsDB.crozmfulsgta.us-west-2.rds.amazonaws.com:3306/testdb`
- dbManager / rdsClient(): configuration for your RDS access.
- newrelic / newrelic.yml: your New Relic license key

This could all go into config files but for now is hardcoded.

# Configuration

## Prerequisites

Account in Staging. It must be configured for US zone, NOT europe

## Creating and configuring a Database in RDS

Here we'll create a MySQL database and configure it, so it can be accessed from this application

1. Go to RDS homepage. In my case is [us-west-2](https://us-west-2.console.aws.amazon.com/rds/home?region=us-west-2).
2. Click on "Create database"
3. Create the database
    - Choose "Standard create" (NO easy create) -> Amazon Aurora -> Amazon Aurora MySQL-compatible edition
    - Templates -> Dev/test
    - DB cluster identifier: `test-db-cluster`
    - master username & password: whatever
    - instance configuration -> burstable classes -> db.t3.small (the cheapest)
    - virtual private cloud -> "Create new VPC"
    - public access -> Yes
    - VPC security group -> "Create new" -> "My DB VPC Security Group"
    - Additional configuration -> Database port -> 3306
    - Additiona configuration
        - initial database name -> "mytestdb"
4. Configure the VPC
    - open the VPC config in RDS -> databases -> test-db-cluster -> VPC
    - open the ACL config in "Main network ACL"
    - make sure than both "inbound rules" and "outbound rules" contain "0.0.0.0/0"
5. Configure the security group so the DB can be accessed by everybody
    - Open the security group config in RDS -> databases -> test-db-cluster -> Security groups -> "My DB VPC Security
      Group"
    - inbound rules -> edit inbound rules ->  source -> edit so it has `Anywhere IPv4 "0.0.0/0"`
6. Take the DB config and add it here. It should look like

```
   val testBDConfig = HikariConfig().apply {
   jdbcUrl =
   "jdbc:mysql://ENDPOINT_PROVIDED_BY_AMAZON.us-west-2.rds.amazonaws.com:3306/mytestdb"
   username = "your DB user"
   password = "your DB pass"
   driverClassName = "com.mysql.cj.jdbc.Driver"
   }
```