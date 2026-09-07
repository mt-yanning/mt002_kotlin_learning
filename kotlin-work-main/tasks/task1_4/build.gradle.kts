plugins {
    kotlin("jvm") version "2.3.21"
    application
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass = "MainKt"
    applicationDefaultJvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.datetime.jvm)
    implementation(libs.mordant)
}
