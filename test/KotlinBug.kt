import com.intellij.ui.components.labels.LinkLabel
import org.ziglang.project.asLink

@JvmName("main")
fun Array<String>.kotlinBug() {
	LinkLabel<Any>().asLink()
}
