import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
}

group = "com.newrelic.entities"
version = "1.0-SNAPSHOT"
val ktor_version = "1.3.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    /*
DB
 */
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-jackson:$ktor_version")

    implementation("com.zaxxer:HikariCP:3.4.2")
    implementation("org.postgresql:postgresql:42.2.11")
    implementation("mysql:mysql-connector-java:8.0.31")

    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.41.1")

    implementation("com.newrelic.agent.java:newrelic-api:7.7.0")


    /*
    AWS libraries
     */
    implementation("aws.sdk.kotlin:s3:0.17.5-beta")
    implementation("aws.sdk.kotlin:dynamodb:0.17.5-beta")
    implementation("aws.sdk.kotlin:iam:0.17.5-beta")
    implementation("aws.sdk.kotlin:cloudwatch:0.17.5-beta")
    implementation("aws.sdk.kotlin:cognitoidentityprovider:0.17.5-beta")
    implementation("aws.sdk.kotlin:sns:0.17.5-beta")
    implementation("aws.sdk.kotlin:pinpoint:0.17.5-beta")
    implementation("aws.sdk.kotlin:rds:0.17.7-beta")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")


    testImplementation("io.ktor:ktor-server-tests:$ktor_version")


}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
