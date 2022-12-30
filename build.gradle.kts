plugins {
    java
    antlr
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
group = "org.tao"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-collections4:4.4")
    implementation("org.apache.logging.log4j:log4j-core:2.19.0")
    implementation("org.apache.logging.log4j:log4j-api:2.19.0")
    implementation("org.jgrapht:jgrapht-core:1.5.1")
    implementation("org.jgrapht:jgrapht-io:1.5.1")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.eclipse.collections:eclipse-collections-api:11.1.0")
    implementation("org.eclipse.collections:eclipse-collections:11.1.0")
    testImplementation("org.mockito:mockito-core:4.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    implementation("org.antlr:antlr4:4.11.1")
    antlr("org.antlr:antlr4:4.11.1")
}

tasks.generateGrammarSource {
    arguments = arguments + listOf("-package", "demo.antlr")
    outputDirectory = file("$outputDirectory/demo/antlr")
    inputs.files(source.files)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}