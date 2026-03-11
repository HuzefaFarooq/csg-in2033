
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

group = "ac.csg.in2033"
var baseName = "ipos-pu"

plugins {
    id("java-platform")
    id("com.gradleup.shadow") version "9.3.2" apply false
}


subprojects {
    
    
    apply(plugin = "java")
    apply(plugin = "com.gradleup.shadow")

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        "testImplementation"("org.junit.jupiter:junit-jupiter:6.0.3")
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    //tasks.withType<ShadowJar>().configureEach {
    //
    //    relocate("", "${rootProject.group}.libs.") {
    //        exclude("ac/csg/in2033/**")
    //        exclude("META-INF/**")
    //        exclude("junit/**")
    //    }
    //}

    tasks.named<Test>("test") {
        useJUnitPlatform()
    }

    tasks.withType<Javadoc>().configureEach {
        title = "IN2033 Team Project"

        (options as CoreJavadocOptions).addBooleanOption("-enable-preview", true)
        (options as CoreJavadocOptions).addStringOption("-snippet-path", "snippets")
        (options as CoreJavadocOptions).addStringOption("-release", "21")

        options.memberLevel = JavadocMemberLevel.PRIVATE

        (options as StandardJavadocDocletOptions).tags(
            "apiNote:a:API Note:",
            "implSpec:a:Implementation Requirements:",
            "implNote:a:Implementation Note:"
        )
    }
}
