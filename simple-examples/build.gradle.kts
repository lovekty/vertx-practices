plugins {
    alias { libs.plugins.kotlin.jvm }
}

dependencies {
    implementation(libs.vertx.lang.kt)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.slf4j.simple)
    testImplementation(libs.vertx.test.junit5)
}
