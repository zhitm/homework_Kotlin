import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.compose.compose

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.openjfx.javafxplugin") version "0.0.8"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("org.jetbrains.compose") version "1.1.0"
}

group = "me.maria"
version = "1.0-SNAPSHOT"


repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

val jfxVersion = "18.0.1"

fun getJavaFXPlatform(): String {
    val currentOS = org.gradle.nativeplatform.platform.internal.DefaultNativePlatform.getCurrentOperatingSystem()
    if (currentOS.isWindows) {
        return "win"
    } else if (currentOS.isLinux) {
        return "linux"
    } else if (currentOS.isMacOsX) {
        return "mac"
    }
    throw IllegalStateException("Unexpected OS: ${currentOS.name}")
}

dependencies {

    implementation("org.openjfx:javafx-base:$jfxVersion:${getJavaFXPlatform()}")
    implementation("org.openjfx:javafx-swing:$jfxVersion:${getJavaFXPlatform()}")
    implementation("org.openjfx:javafx-graphics:$jfxVersion:${getJavaFXPlatform()}")

    implementation("org.jetbrains.lets-plot:lets-plot-jfx:2.3.0")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.2.0")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.3.0")

    implementation("org.slf4j:slf4j-simple:1.7.36")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.20.0")

    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))

    implementation(compose.desktop.currentOs)
    implementation("org.jsoup:jsoup:1.13.1")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

