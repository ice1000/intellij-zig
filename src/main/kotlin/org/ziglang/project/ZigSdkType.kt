package org.ziglang.project

import com.intellij.openapi.projectRoots.*
import org.ziglang.icons.ZigIcons
import org.jdom.Element
import org.ziglang.ZIG_WEBSITE
import org.ziglang.i18n.ZigBundle
import org.ziglang.util.OsSpecific
import java.nio.file.Paths

class ZigSdkType : SdkType(ZigBundle.message("zig.name")) {
    override fun getPresentableName() = ZigBundle.message("zig.sdk.name")
    override fun getIcon() = ZigIcons.ZIG_BIG_ICON
    override fun getIconForAddAction() = ZigIcons.ZIG_ADD_SDK_ICON
    override fun isValidSdkHome(path: String): Boolean = OsSpecific.isValidSdkPath(path)
    override fun suggestSdkName(currentSdkName: String?, sdkHome: String): String = ZigBundle.message("zig.sdk.name")
    override fun suggestHomePath() = Paths.get(zigPath).parent?.parent?.toString()
    override fun getDownloadSdkUrl() = ZIG_WEBSITE
    override fun createAdditionalDataConfigurable(md: SdkModel, m: SdkModificator): AdditionalDataConfigurable? = null
    override fun getVersionString(sdkHome: String?) = versionOf(sdkHome.orEmpty())
    override fun saveAdditionalData(additionalData: SdkAdditionalData, element: Element) = Unit // leave blank
    override fun setupSdkPaths(sdk: Sdk, sdkModel: SdkModel): Boolean {
        val modificator = sdk.sdkModificator
        modificator.versionString = getVersionString(sdk) ?: ZigBundle.message("zig.version.unknown")
        modificator.commitChanges()
        return true
    }

    companion object InstanceHolder {
        val instance get() = findInstance(ZigSdkType::class.java)
    }
}
