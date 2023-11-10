plugins {
    alias { libs.plugins.kotlin.jvm }
}

dependencies {
    implementation(project(":common"))
    implementation(libs.vertx.lang.kt)
    implementation(libs.slf4j.simple)
    testImplementation(libs.vertx.test.junit5)
}
