import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.41"
    application
}

group = "com.codingfun"
version = "1.0"


buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("io.ratpack:ratpack-gradle:1.4.5")
    }
}

apply(plugin = "io.ratpack.ratpack-java")

application {
    mainClassName = "com.codingfun.Main.kt"
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit:junit:4.11")
    runtime("org.slf4j:slf4j-simple:1.7.21")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


