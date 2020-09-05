import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    application
    kotlin("jvm") version "1.3.72"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ratpack:ratpack-core:1.8.0")
    implementation("io.ratpack:ratpack-guice:1.8.0")
    implementation("io.ratpack:ratpack-rx2:1.8.0")

}

application {
    mainClassName = "com.example.Main"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}