plugins {
    java
    checkstyle
    id("java")
}

group = "com.mk"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    val junitVersion = "4.12"

    implementation("junit:junit:$junitVersion")
    testImplementation("junit:junit:$junitVersion")
}

checkstyle {
    toolVersion = "8.24"
    configFile = file("config/checkstyle/checkstyle-rules-patched-linewrap.xml")
    configProperties = mapOf("suppressionFile" to file("config/checkstyle/checkstyle-suppressions.xml"))
    maxWarnings = 0
}
