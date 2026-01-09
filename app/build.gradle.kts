plugins {
    id("org.jetbrains.kotlin.jvm") version "2.2.20"
    application
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {

    implementation(platform("org.http4k:http4k-bom:6.20.2.1"))

    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-undertow")
    implementation("org.http4k:http4k-client-apache")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
}

application {
    mainClass.set("hello.world.kotlin.AppKt")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
