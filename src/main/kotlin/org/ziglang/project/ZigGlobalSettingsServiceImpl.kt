package org.ziglang.project

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import java.io.File

@State(
    name = "ZigGlobalSettings",
    storages = [Storage("zigGlobalConfig.xml")]
)
class ZigGlobalSettingsServiceImpl :
    ZigGlobalSettingsService, PersistentStateComponent<ZigGlobalSettings> {
    override val knownZigExes: MutableSet<String> = hashSetOf()
    private fun invalidate() = knownZigExes.retainAll(::validateZigExe)
    override fun getState(): ZigGlobalSettings {
        invalidate()
        return ZigGlobalSettings(knownZigExes.joinToString(File.pathSeparator))
    }

    override fun loadState(state: ZigGlobalSettings) {
        invalidate()
        knownZigExes += state.allZigExePath.split(File.pathSeparatorChar)
    }
}