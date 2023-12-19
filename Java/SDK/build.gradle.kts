plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.azure:azure-ai-openai:1.0.0-beta.6")

    implementation("org.slf4j:slf4j-nop:2.0.9")
}