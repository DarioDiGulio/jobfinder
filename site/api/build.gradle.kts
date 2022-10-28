import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.nbottarini.asimov.environment.Env

Env.addSearchPath(rootProject.projectDir.absolutePath)

plugins {
    kotlin("jvm") version "1.7.20"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

buildscript {
    dependencies { classpath("com.nbottarini:asimov-environment:2.0.0") }
}
group = "com.upward.jobfinder.site"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.nbottarini:asimov-kotlin-extensions:1.0.2")
    implementation("com.nbottarini:asimov-environment:2.0.0")
    implementation("org.slf4j:slf4j-simple:2.0.3")
    implementation("io.javalin:javalin:5.1.2")
    implementation("com.nbottarini:asimov-json:0.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("net.bytebuddy:byte-buddy:1.12.18") // Added for mockk compatibility with JDK16
    testImplementation("io.mockk:mockk:1.13.2")
    testImplementation("io.rest-assured:rest-assured:5.2.0")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDirs("src", "generated")
        resources.srcDirs("resources")
    }
    sourceSets["test"].apply {
        kotlin.srcDir("test")
        resources.srcDir("test_resources")
    }
}

java {
    sourceSets["main"].apply {
        java.srcDirs("src", "generated")
        resources.srcDirs("resources")
    }
    sourceSets["test"].apply {
        java.srcDir("test")
        resources.srcDir("test_resources")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.getByName<ShadowJar>("shadowJar") {
    archiveFileName.set("liquidator_api.jar")
    manifest {
        attributes(mapOf(
            "Main-Class" to "com.upward.jobfinder.site.http.HttpMainKt",
            "VERSION" to project.version
        ))
    }
}