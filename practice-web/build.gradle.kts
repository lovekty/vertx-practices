import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    java
    kotlin("jvm")
    id("com.github.johnrengelman.shadow")
}


dependencies {
    implementation(project(":common"))
    implementation("io.vertx:vertx-lang-kotlin")
    implementation("io.vertx:vertx-web")
    implementation("io.vertx:vertx-web-templ-thymeleaf")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl")
}

val mainVerticleName = "me.tonyirl.practice.vertx.web.AppMain"
val watchForChange = "src/**/*"
val doOnChange = "./gradlew classes"
val launcherClassName = "io.vertx.core.Launcher"

application {
    mainClass.set(launcherClassName)
}

tasks.withType<ShadowJar> {
    archiveClassifier.set("fat")
    manifest {
        attributes(mapOf("Main-Verticle" to mainVerticleName))
    }
    mergeServiceFiles {
        include("META-INF/services/io.vertx.core.spi.VerticleFactory")
    }
}

tasks.withType<JavaExec> {
    args = listOf(
        "run",
        mainVerticleName,
        "--redeploy=$watchForChange",
        "--launcher-class=$launcherClassName",
        "--on-redeploy=$doOnChange"
    )
}
