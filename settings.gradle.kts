plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "hello-notification-system"
include("sender")
include("forwarder-1")
include("forwarder-2")
include("forwarder-5")
include("forwarder-6")
include("forwarder-7")
