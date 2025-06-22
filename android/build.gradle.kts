import java.util.*

android {
    namespace = ProjectConfig.ROOT_PACKAGE
    buildToolsVersion = "36.0.0"
    compileSdk = 36

    defaultConfig {
        applicationId = ProjectConfig.ROOT_PACKAGE
        minSdk = 14
        targetSdk = 36
        versionCode = 1
        versionName = ProjectConfig.APP_VERSION
    }

    buildFeatures {
        renderScript = true
        aidl = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets {
        getByName("main") {
            jniLibs.srcDirs("libs")
        }
    }
}

dependencies {
    implementation(projects.core)
    api(libs.gdx.backend.android)
    NativePlatforms.values().forEach { platform ->
        natives("${libs.gdx.platform.get()}:$platform")
    }
}


tasks.register("copyAndroidNatives") {
    val libsDir = File(ProjectConfig.LIBS_DIR)

    doFirst {
        NativePlatforms.values().forEach {
            File(libsDir, it.platformName).mkdirs()
        }

        configurations.getByName("natives").copy().files.forEach { jar ->
            val outputDir: File? =
                NativePlatforms.values().firstOrNull { jar.name == "natives-${it.platformName}.jar" }?.let {
                    File(libsDir, it.platformName)
                }

            outputDir?.let { dir ->
                copy {
                    from(zipTree(jar))
                    into(dir)
                    include("*.so")
                }
            }
        }
    }
}

tasks.configureEach {
    if (name.contains("merge") && name.contains("JniLibFolders")) {
        dependsOn("copyAndroidNatives")
    }
}

tasks.register<Exec>("run") {
    val localProperties = project.file("../local.properties")
    val androidHomeEnvVar = System.getenv("ANDROID_HOME")

    val path = if (localProperties.exists()) {
        val properties = Properties()
        localProperties.inputStream().use { properties.load(it) }
        properties.getProperty("sdk.dir") ?: androidHomeEnvVar
    } else {
        androidHomeEnvVar
    }

    val rootPackage =
        with(project.android.defaultConfig) { applicationId + (applicationIdSuffix?.let { "-$it" } ?: "") }

    val adb = "$path/platform-tools/adb"
    commandLine(adb, "shell", "am", "start", "-n", "${rootPackage}/${rootPackage}.AndroidLauncher")
}

tasks.named("preBuild") {
    dependsOn("copyAndroidNatives")
}

eclipse.project.name = ProjectConfig.APP_NAME + "-android"
