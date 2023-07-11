import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.util.*

plugins {
    kotlin("jvm") version "1.9.0"
    `java-library`
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

publishing {
    repositories {
        val p = Properties().apply {
            val ins = FileInputStream("./local.gradle.properties")
            load(ins)
            ins.close()
        }
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/TelephoneTan/KotlinHTTPRequest")
            credentials {
                username = p.getProperty("gpr_github_user") as String
                password = p.getProperty("gpr_github_key") as String
            }
        }
    }
    publications {
        create<MavenPublication>("khr") {
            groupId = "pub.telephone"
            artifactId = "kotlin-http-request"
            version = "0.1.0"
            from(components["java"])
        }
    }
}

repositories {
    val p = Properties().apply {
        val ins = FileInputStream("./local.gradle.properties")
        load(ins)
        ins.close()
    }
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/TelephoneTan/JavaHTTPRequest")
        credentials {
            username = p.getProperty("gpr_github_user") as String
            password = p.getProperty("gpr_github_key") as String
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("pub.telephone:java-http-request:6.1.0")
    implementation("pub.telephone:kotlin-promise:0.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
}