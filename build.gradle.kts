import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.3.72" apply false
}

val vertxVersion = "3.9.2"
val log4jVersion = "2.13.3"

allprojects {
    apply<JavaLibraryPlugin>()

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    group = "me.tonyirl.practice.vertx"
    version = "1.0.0"

    dependencies {
        implementation(platform("io.vertx:vertx-dependencies:${vertxVersion}"))
        implementation(platform("org.apache.logging.log4j:log4j:${log4jVersion}"))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

