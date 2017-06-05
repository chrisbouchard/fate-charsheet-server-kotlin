import org.springframework.boot.gradle.dsl.SpringBootExtension

buildscript {
    repositories {
        gradleScriptKotlin()
        mavenCentral()
        maven { setUrl("https://repo.spring.io/libs-milestone") }
    }

    dependencies {
        classpath(kotlinModule("allopen"))
        classpath(kotlinModule("gradle-plugin"))
        classpath(kotlinModule("noarg"))

        classpath("org.springframework.boot:spring-boot-gradle-plugin:2.0.0.M1")
    }
}

apply {
    plugin("idea")

    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("kotlin-jpa")

    plugin("io.spring.dependency-management")
    plugin("org.springframework.boot")
}

repositories {
    gradleScriptKotlin()
    mavenCentral()
    maven { setUrl("https://repo.spring.io/libs-milestone") }
}

configure<SpringBootExtension> {
    buildInfo()
}

dependencies {
    compile(kotlinModule("stdlib-jre8"))
    compile(kotlinModule("reflect"))

    compile("com.google.guava:guava:21.0")

    compile("com.fasterxml.jackson.datatype:jackson-datatype-guava")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jdk8")
    compile("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin")

    compile("org.springframework.boot:spring-boot-devtools")
    compile("org.springframework.boot:spring-boot-starter-actuator")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("org.springframework.boot:spring-boot-starter-data-rest")
    compile("org.springframework.data:spring-data-rest-hal-browser")

    runtime("com.h2database:h2")

    testCompile("org.springframework.boot:spring-boot-starter-test")
}
