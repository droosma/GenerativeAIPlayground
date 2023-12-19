plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.azure:azure-ai-openai:1.0.0-beta.2")

    implementation("com.microsoft.semantic-kernel:semantickernel-plugin-core:0.2.13-alpha")
    implementation("com.microsoft.semantic-kernel:semantickernel-core:0.2.13-alpha")
    implementation("com.microsoft.semantic-kernel:semantickernel-api:0.2.13-alpha")
    implementation("com.microsoft.semantic-kernel:semantickernel-connectors-ai-openai:0.2.13-alpha")
    implementation("com.microsoft.semantic-kernel:semantickernel-connectors-memory-azurecognitivesearch:0.2.13-alpha")
    implementation("com.microsoft.semantic-kernel:semantickernel-connectors-memory-postgresql:0.2.13-alpha")

    implementation("org.slf4j:slf4j-nop:2.0.9")
}