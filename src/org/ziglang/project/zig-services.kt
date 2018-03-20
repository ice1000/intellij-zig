package org.ziglang.project

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

interface ZigProjectService {
	val settings: ZigSettings
}

class ZigSettings(
		var exePath: String = zigPath
)

val Project.zigSettings: ZigProjectService
	get() = ServiceManager.getService(this, ZigProjectService::class.java)

class ZigProjectServiceImpl : ZigProjectService, PersistentStateComponent<ZigSettings> {
	override val settings = ZigSettings()
	override fun getState(): ZigSettings? = XmlSerializerUtil.createCopy(settings)
	override fun loadState(state: ZigSettings?) {
		state?.let { XmlSerializerUtil.copyBean(it, settings) }
	}
}

