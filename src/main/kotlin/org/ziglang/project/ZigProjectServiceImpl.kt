package org.ziglang.project

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = "ZigProjectSettings",
    storages = [Storage("zigConfig.xml")]
)
class ZigProjectServiceImpl : ZigProjectService, PersistentStateComponent<ZigSettings> {
    override val settings = ZigSettings()
    override fun getState(): ZigSettings? = XmlSerializerUtil.createCopy(settings)
    override fun loadState(state: ZigSettings) {
        state.let { XmlSerializerUtil.copyBean(it, settings) }
    }
}