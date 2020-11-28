import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.4.20" apply false
    id("com.github.johnrengelman.shadow") version "6.1.0" apply false
}

group = "me.tonyirl.practice.vertx"
version = "1.0.0"

val vertxVersion = "4.0.0.CR2"
val log4jVersion = "2.13.3"

subprojects {
    apply<JavaLibraryPlugin>()

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    dependencies {
        implementation(platform("io.vertx:vertx-dependencies:${vertxVersion}"))
        implementation(platform("org.apache.logging.log4j:log4j:${log4jVersion}"))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "11"
    }
}

