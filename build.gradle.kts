
plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.minidev", "json-smart", "2.5.0")
    implementation("com.jayway.jsonpath:json-path:2.9.0")
    implementation("org.slf4j", "slf4j-nop", "2.0.11")
    implementation("org.json:json:20231013")
}

tasks.test {
    useJUnitPlatform()
}