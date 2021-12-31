import java.io.File

val path: String = args.firstOrNull()
		?: throw IllegalArgumentException("Please input icons path")

File(path).listFiles { file: File? ->
	file?.let {
		file.isDirectory.not() && file.extension == ".svg"
	} == true
}.orEmpty().filterNotNull().forEach { it: File ->
	//LaJi Java
	println("Zipping ${it.name}")
	Runtime.getRuntime().exec("svgo $it")
}