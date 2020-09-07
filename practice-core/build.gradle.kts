plugins {
    kotlin("jvm")
}

dependencies {
    implementation("io.vertx:vertx-lang-kotlin")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl")
    testImplementation("io.vertx:vertx-junit5")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
