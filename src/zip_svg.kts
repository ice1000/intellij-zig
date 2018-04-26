import java.io.File

val path : String = args.firstOrNull() ?: throw IllegalArgumentException("Please input icons path")

File(path).listFiles { file : File? ->
	file?.let {
		file.isDirectory.not() and file.name.endsWith(".svg")
	} == true
}.forEach { it : File? ->
	//LaJi Java
	println("Zipping ${it?.name}")
	Runtime.getRuntime().exec("svgo $it")
}