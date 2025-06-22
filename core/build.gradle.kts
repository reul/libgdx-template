plugins {
    `java-library`
}

dependencies {
    api("com.badlogicgames.gdx:gdx:${Versions.gdxVersion}")
    api("com.badlogicgames.gdx:gdx-freetype:${Versions.gdxVersion}")
    api("com.badlogicgames.gdx:gdx-box2d:${Versions.gdxVersion}")
    api("com.badlogicgames.gdx:gdx-ai:${Versions.aiVersion}")
    api("com.badlogicgames.ashley:ashley:${Versions.ashleyVersion}")
    api("com.badlogicgames.box2dlights:box2dlights:${Versions.box2DLightsVersion}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

eclipse {
    project {
        name = "${ProjectConfig.APP_NAME}-core"
    }
}
