plugins {
    id("java")
}

group = "energy.lux"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://ci.hesi.energy/artifactory/libs-release-local")
    }
}

dependencies {
    implementation("nl.tno.esdl:esdl:2.25.5.1")
    implementation("org.eclipse.emf:org.eclipse.emf.ecore:2.25.0")
    implementation("org.eclipse.emf:org.eclipse.emf.common:2.23.0")
    implementation("org.eclipse.emf:org.eclipse.emf.ecore.xmi:2.16.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")


}

tasks.test {
    useJUnitPlatform()
}