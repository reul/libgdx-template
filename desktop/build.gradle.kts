plugins {
    `java-library`
}

dependencies {
    api(libs.gdx.backend.lwjgl3)
    api("${libs.gdx.platform.get()}:natives-desktop")
    api("${libs.gdx.freetype.get()}:natives-desktop")
    api("${libs.gdx.box2d.get()}:natives-desktop")

    implementation(projects.core)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

sourceSets {
    main {
        resources.srcDir(ProjectConfig.ASSETS_DIR)
    }
}

registerDebugTask()
registerRunTask()
registerDistTask()

eclipse {
    project {
        name = "${ProjectConfig.APP_NAME}-desktop"
    }
}
