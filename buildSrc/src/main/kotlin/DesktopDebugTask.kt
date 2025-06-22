import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.JavaExec
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register
import java.io.File

fun org.gradle.api.Project.registerDebugTask() {
    tasks.register<JavaExec>("debug") {
        dependsOn("classes")

        extensions.getByType<ExtraPropertiesExtension>().apply {
            mainClass.set(ProjectConfig.MAIN_CLASS)
        }

        val sourceSets = project.extensions.getByType<JavaPluginExtension>().sourceSets
        classpath = sourceSets["main"].runtimeClasspath

        standardInput = System.`in`
        workingDir = File(ProjectConfig.ASSETS_DIR)
        debug = true
    }
}
