dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("io.projectreactor:reactor-test")
}

val dockerUsername: String = System.getProperty("DOCKER_USERNAME") ?: ""
val dockerPassword: String = System.getProperty("DOCKER_PASSWORD") ?: ""

jib {
    from {
        image = "openjdk:17.0.2-slim"
        platforms {
            platform {
                architecture = "arm64"
                os = "linux"
            }
        }
    }
    to {
        image = "geonc123/tis-${project.name}"
        auth {
            username = dockerUsername
            password = dockerPassword
        }
        tags = setOf("latest", project.version.toString().lowercase())
    }
    container {
        jvmFlags = listOf(
            "-Xms256m",
            "-Xmx512m",
        )
    }
}
