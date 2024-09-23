plugins {
    kotlin("jvm") version "2.0.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("maven-publish")
}

group = "de.butterfly"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.21")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}
sourceSets {
    main {
        kotlin {
            exclude("de/butterfly/butterflylibrary/menumanager/**")
        }
    }
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

// Task for creating source JAR
val sourceJar by tasks.registering(Jar::class) {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            // Include the shaded JAR
            artifact(tasks.shadowJar.get()) {
                classifier = "shaded"
            }

            // Add artifact sources JAR
            artifact(sourceJar.get()) {
                classifier = "sources"
            }

            // Artifact details
            groupId = group as String
            artifactId = "butterfly-library"
            version = version.toString()
        }
    }

    repositories {
        maven {
            if (version.toString().endsWith("-SNAPSHOT")) {
                // Upload to snapshot repository
                name = "snapshot"
                url = uri("http://85.214.27.173:8081/repository/butterflyApi/")
            } else {
                // Upload to release repository
                name = "release"
                url = uri("http://85.214.27.173:8081/repository/butterflyApiBuilt/")
            }

            credentials {
                username = project.findProperty("nexusUsername") as String? ?: "eritis"
                password = project.findProperty("nexusPassword") as String? ?: "Developer45"
            }
            isAllowInsecureProtocol = true
        }
    }
}