val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.20"
    kotlin("plugin.serialization").version("1.6.21")
}

group = "com.peacemaker"
version = "0.0.1"

application {
    mainClass.set("com.peacemaker.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

tasks.create("stage"){
    dependsOn("installDist")
}//adding task to heroku

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {

    //kto dependencies
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-auth-jwt:$ktor_version")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")

    //serialization and deserialization
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")//media type
    implementation("io.ktor:ktor-serialization-jackson:$ktor_version")//library to parse json content
    implementation ("io.ktor:ktor-serialization:$ktor_version")

    //others
    implementation("ch.qos.logback:logback-classic:1.2.7")
    implementation("ch.qos.logback:logback-access:0.7")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    //database connection and postgres as db
    implementation("org.jetbrains.exposed:exposed-core:0.38.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.38.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.38.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.38.1")
    implementation("org.postgresql:postgresql:42.3.3")
    implementation("com.zaxxer:HikariCP:5.0.1")//configuration
}