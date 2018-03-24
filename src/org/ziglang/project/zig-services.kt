package org.ziglang.project

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import java.io.File
import java.nio.file.Paths

interface ZigProjectService {
	val settings: ZigSettings
}

interface ZigGlobalSettingsService {
	val knownZigExes: MutableSet<String>
}

class ZigSettings(
		var exePath: String = zigPath,
		var version: String = "",
		var installPath: String = ""
) {
	fun initWithExe() {
		version = versionOf(exePath)
		installPath = Paths.get(exePath).parent.toAbsolutePath().toString()
	}
}

class ZigGlobalSettings(
		var allZigExePath: String = "")

val Project.zigSettings: ZigProjectService
	get() = ServiceManager.getService(this, ZigProjectService::class.java)

val zigGlobalSettings: ZigGlobalSettingsService
	get() = ServiceManager.getService(ZigGlobalSettingsService::class.java)

@State(
		name = "ZigProjectSettings",
		storages = [Storage(file = "zigConfig.xml", scheme = StorageScheme.DIRECTORY_BASED)])
class ZigProjectServiceImpl : ZigProjectService, PersistentStateComponent<ZigSettings> {
	override val settings = ZigSettings()
	override fun getState(): ZigSettings? = XmlSerializerUtil.createCopy(settings)
	override fun loadState(state: ZigSettings?) {
		state?.let { XmlSerializerUtil.copyBean(it, settings) }
	}
}

@State(
		name = "ZigGlobalSettings",
		storages = [Storage(file = "zigGlobalConfig.xml", scheme = StorageScheme.DIRECTORY_BASED)])
class ZigGlobalSettingsServiceImpl :
		ZigGlobalSettingsService, PersistentStateComponent<ZigGlobalSettings> {
	override val knownZigExes: MutableSet<String> = hashSetOf()
	private fun invalidate() = knownZigExes.retainAll { validateZigExe(it) }
	override fun getState(): ZigGlobalSettings {
		invalidate()
		return ZigGlobalSettings(knownZigExes.joinToString(File.pathSeparator))
	}

	override fun loadState(state: ZigGlobalSettings) {
		invalidate()
		knownZigExes += state.allZigExePath.split(File.pathSeparatorChar)
	}
}
