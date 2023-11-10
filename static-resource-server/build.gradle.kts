plugins {
    application
    alias { libs.plugins.kotlin.jvm }
}

dependencies {
    implementation(libs.vertx.lang.kt)
    implementation(libs.vertx.web)
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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