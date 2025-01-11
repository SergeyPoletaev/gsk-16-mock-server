plugins {
    java
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "ru.gsk-16"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.wiremock:wiremock-standalone:3.9.2")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-data-mongodb")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}
