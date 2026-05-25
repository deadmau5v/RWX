plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    compileOnly(project(":android-stubs"))
    compileOnly(project(":r-compat"))
    compileOnly(files("../libs/android.jar"))
    implementation(files("../libs/android-platform-lib.jar"))
    implementation(libs.httpclient)
    implementation(libs.jackson.databind)
    api(libs.jvm.libp2p)
    api(libs.tomlkt)
}

// Ensure android-stubs classes take precedence over android.jar on the compile classpath.
// This is needed because the stubs contain obfuscated method/field names (e.g., Rect.a, Paint.b)
// that the decompiled game code still references, while android.jar has standard API names.
tasks.withType<JavaCompile>().configureEach {
    val stubsClasses = project(":android-stubs").layout.buildDirectory.dir("classes/java/main")
    classpath = files(stubsClasses) + classpath
}




