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
