import org.gradle.api.file.DuplicatesStrategy
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register

fun org.gradle.api.Project.registerDistTask() {
    tasks.register<Jar>("dist") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE

        manifest {
            attributes["Main-Class"] = ProjectConfig.MAIN_CLASS
        }

        dependsOn("classes")
        dependsOn(configurations["runtimeClasspath"])
        from(configurations["runtimeClasspath"].map { if (it.isDirectory) it else zipTree(it) })
        with(tasks["jar"] as Jar)
    }
}
