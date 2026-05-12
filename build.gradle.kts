plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.spring") version "2.2.21"
    id("org.sonarqube") version "7.3.0.8198"
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
    jacoco
}

group = "com.neuroconvert"
version = "0.0.1-SNAPSHOT"
description = "task-service"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.13"
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true   // SonarQube reads XML
        html.required = true  // human-readable
    }
}

sonar {
    properties {
        property("sonar.projectKey", "neuroconvert_task-service")
        property("sonar.projectName", "task-service")
        property("sonar.coverage.jacoco.xmlReportPaths",
            "${layout.buildDirectory.get()}/reports/jacoco/test/jacocoTestReport.xml")
    }
}