plugins {
    kotlin("jvm") version "2.3.21"  // 第 2 行安装了 Kotlin 插件
    application                     // 第 3 行安装了应用程序插件
}
// 第 6-8 行配置 Kotlin 插件，以表明我们要使用 Java 编译器和虚拟机的 25 版本
kotlin {
    jvmToolchain(21)
}
// 第 10-12 行配置应用程序插件，以指示 main()应用程序函数的位置
application {
    mainClass = "MainKt"
    applicationDefaultJvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
}
// 第 14-16 行指定了应用程序依赖项的来源
repositories {
    mavenCentral()
}
// 第 18-21 行指定了依赖项本身
dependencies {
    implementation(libs.datetime.jvm)
    implementation(libs.mordant)
}
