import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    alias { libs.plugins.kotlin.jvm }
    alias { libs.plugins.shadow.jar }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.vertx.lang.kt)
    implementation(libs.vertx.web)
    implementation(libs.vertx.webtempl.thymeleaf)
    implementation(libs.slf4j.simple)
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
