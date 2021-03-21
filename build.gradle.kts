import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val kotlin_serialization: String by project
val exposedVersion: String by project


plugins {
    application
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"

}

group = "ga.example"
version = "0.0.1"
application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

repositories {
    mavenLocal()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation ("io.ktor:ktor-serialization:$ktor_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-core:$kotlin_serialization")

    //Auth
    implementation ("io.ktor:ktor-auth:$ktor_version")
    implementation ("io.ktor:ktor-auth-jwt:$ktor_version")
//    implementation ("io.ktor:ktor-jackson:$ktor_version")

    implementation("io.ktor:ktor-metrics:$ktor_version")
//exposed db
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("mysql:mysql-connector-java:5.1.48")


    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}