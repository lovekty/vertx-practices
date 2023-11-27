plugins {
    application
    alias { libs.plugins.kotlin.jvm }
}

dependencies {
    implementation(libs.vertx.lang.kt)
    implementation(libs.vertx.web)
    implementation(libs.jackson.module.kotlin)
    testImplementation(kotlin("test-junit5"))
    testImplementation(libs.slf4j.simple)
    testImplementation(libs.vertx.test.junit5)
}

val launcherClassName = "io.vertx.core.Launcher"

application {
    mainClass.set(launcherClassName)
}

tasks.withType<JavaExec> {
    args = listOf(
        "run",
        "cn.bootz.practice.vertx.srs.Server",
        "--launcher-class=$launcherClassName"
    )
}

tasks.test {
    useJUnitPlatform()
}