import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.7.21"
}

application {
    mainClass.set("de.consuli.aoc.Runner")
}

group = "de.consuli"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.reflections", "reflections", "0.9.12")
    testImplementation(kotlin("test"))
}

dependencies {
    implementation("org.reflections", "reflections", "0.9.12")
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.5.4")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}
