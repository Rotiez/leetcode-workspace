import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    val kotlinVersion = "1.9.23"
    kotlin("jvm") version kotlinVersion apply false
    kotlin("kapt") version kotlinVersion apply false
    id("io.freefair.aspectj.post-compile-weaving") version "8.6"
}

allprojects {
    group = "edu.rotiez"

    repositories {
        mavenCentral()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    tasks.withType<KotlinJvmCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}

subprojects {
    apply {
        plugin("io.freefair.aspectj.post-compile-weaving")
    }

    when (project.name) {
        "java-solutions" -> apply {
            plugin("java")
        }
        else -> apply {
            plugin("org.jetbrains.kotlin.jvm")
            plugin("org.jetbrains.kotlin.kapt")
        }
    }
}