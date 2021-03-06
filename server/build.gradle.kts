val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val fakerVersion: String by project

plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
}

group = "com.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenCentral()
}

tasks.create("stage") {
    dependsOn("installDist")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")

    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")

    implementation("io.github.serpro69:kotlin-faker:$fakerVersion")
}