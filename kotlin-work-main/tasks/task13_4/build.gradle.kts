plugins {
    kotlin("jvm") version "2.3.21"
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.framework)
    testRuntimeOnly(libs.kotest.runner)
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "failed", "skipped")
    }
}
