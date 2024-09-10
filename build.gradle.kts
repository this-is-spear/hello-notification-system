plugins {
    id("org.springframework.boot") version "3.2.4"
    id("com.google.cloud.tools.jib") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("plugin.jpa") version "1.9.23"
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply {
        plugin("io.spring.dependency-management")
        plugin("org.springframework.boot")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("org.jetbrains.kotlin.plugin.jpa")
        plugin("org.jetbrains.kotlin.plugin.allopen")
        plugin("com.google.cloud.tools.jib")
        plugin("maven-publish")
    }

    group = "hello.tis"
    version = "1.0-SNAPSHOT"

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    extra["springCloudVersion"] = "2023.0.1"

    dependencies {
        implementation(kotlin("reflect"))
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        runtimeOnly("io.micrometer:micrometer-registry-prometheus")

        testImplementation(kotlin("test"))
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    tasks.test {
        useJUnitPlatform()
    }

    allOpen {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.Embeddable")
        annotation("jakarta.persistence.MappedSuperclass")
    }
}
