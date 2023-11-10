import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    java
    alias { libs.plugins.kotlin.jvm } apply false
}

group = "me.tonyirl.practice.vertx"
version = "1.0.0"

subprojects {
    apply<JavaPlugin>()

    dependencies {
        implementation(platform(rootProject.libs.vertx.bom))
    }

    extensions.findByType(KotlinJvmProjectExtension::class.java)?.apply {
        jvmToolchain(11)
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

