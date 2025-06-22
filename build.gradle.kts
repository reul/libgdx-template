buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven ( url="https://oss.sonatype.org/content/repositories/snapshots/")
        google()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.10.1")
    }
}


allprojects {
    apply (plugin= "eclipse")

    version = ProjectConfig.APP_VERSION

    repositories {
        mavenLocal()
        mavenCentral()
        google()
        gradlePluginPortal()
        maven (url="https://oss.sonatype.org/content/repositories/snapshots/")
        maven ( url="https://oss.sonatype.org/content/repositories/releases/")
        maven ( url="https://jitpack.io")
    }
}

project(":android") {
    apply (plugin= "com.android.application")

    configurations { create("natives") }

    val api by configurations
    val implementation by configurations
    val natives by configurations

    dependencies {
        implementation(project(":core"))
        api("com.badlogicgames.gdx:gdx-backend-android:${Versions.gdxVersion}")
        natives("com.badlogicgames.gdx:gdx-platform:${Versions.gdxVersion}:natives-armeabi-v7a")
        natives("com.badlogicgames.gdx:gdx-platform:${Versions.gdxVersion}:natives-arm64-v8a")
        natives("com.badlogicgames.gdx:gdx-platform:${Versions.gdxVersion}:natives-x86")
        natives("com.badlogicgames.gdx:gdx-platform:${Versions.gdxVersion}:natives-x86_64")
        api("com.badlogicgames.gdx:gdx-freetype:${Versions.gdxVersion}")
        natives("com.badlogicgames.gdx:gdx-freetype-platform:${Versions.gdxVersion}:natives-armeabi-v7a")
        natives("com.badlogicgames.gdx:gdx-freetype-platform:${Versions.gdxVersion}:natives-arm64-v8a")
        natives("com.badlogicgames.gdx:gdx-freetype-platform:${Versions.gdxVersion}:natives-x86")
        natives("com.badlogicgames.gdx:gdx-freetype-platform:${Versions.gdxVersion}:natives-x86_64")
        api("com.badlogicgames.gdx:gdx-box2d:${Versions.gdxVersion}")
        natives("com.badlogicgames.gdx:gdx-box2d-platform:${Versions.gdxVersion}:natives-armeabi-v7a")
        natives("com.badlogicgames.gdx:gdx-box2d-platform:${Versions.gdxVersion}:natives-arm64-v8a")
        natives("com.badlogicgames.gdx:gdx-box2d-platform:${Versions.gdxVersion}:natives-x86")
        natives("com.badlogicgames.gdx:gdx-box2d-platform:${Versions.gdxVersion}:natives-x86_64")
        api("com.badlogicgames.gdx:gdx-ai:${Versions.aiVersion}")
        api("com.badlogicgames.ashley:ashley:${Versions.ashleyVersion}")
        api("com.badlogicgames.box2dlights:box2dlights:${Versions.box2DLightsVersion}")
    }
}
