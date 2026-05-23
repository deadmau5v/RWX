package com.corrodinggames.rts.gameFramework

import android.content.Context
import dalvik.system.DexClassLoader
import java.io.File
import java.nio.charset.Charset

/**
 * Android platform bridge implementation.
 *
 * Restored from the original Android APK (decompiled via JADX into Desktop module).
 * The Desktop code contains "JADX INFO: loaded from: game-lib.jar" markers indicating
 * these classes originated on Android. This bridge provides:
 * - Mobile platform flag (isMobilePlatform = true)
 * - DexClassLoader for mod APK/DEX loading (vs Desktop's URLClassLoader)
 * - Android-specific storage paths via Context APIs
 */
class AndroidPlatformBridge(
    private val appContext: Context,
) : PlatformBridge {

    override val storage: PlatformStorage = AndroidPlatformStorage(appContext)

    override fun isMobilePlatform(): Boolean = true

    override fun createModClassLoader(modFile: File, parent: ClassLoader): ClassLoader {
        // Android requires DexClassLoader for .jar/.apk files containing DEX bytecode.
        // optimizedDirectory is deprecated since API 26 but still required by the constructor;
        // the system ignores it on API 28+ (our minSdk=29).
        val optimizedDir = File(appContext.codeCacheDir, "mod_dex").apply { mkdirs() }
        return DexClassLoader(
            modFile.absolutePath,
            optimizedDir.absolutePath,
            null,  // librarySearchPath — mods don't ship native libs
            parent,
        )
    }
}

/**
 * Android storage implementation using Context APIs.
 *
 * Maps the same virtual paths as DesktopPlatformStorage ("/SD/", "/LOCAL/") to
 * Android's external/files and internal/data directories respectively.
 *
 * On Android, "SD card" path traditionally meant external storage, which is now
 * emulated under app-specific directory (scoped storage, API 29+).
 */
class AndroidPlatformStorage(
    private val appContext: Context,
) : PlatformStorage {

    // /SD/rustedWarfare/ → app-specific external storage (visible to user)
    private val sdDir: File = appContext.getExternalFilesDir(null)
        ?: appContext.filesDir

    // /LOCAL/ → app-specific internal storage (private)
    private val localDir: File = appContext.filesDir

    // /LOCAL/cache/ → cache directory (auto-cleaned by system)
    private val cacheDir: File = appContext.cacheDir

    override val rootDir: StorageLocation = location("/SD/rustedWarfare/", sdDir)
    override val localDir: StorageLocation = location("/LOCAL/", localDir)
    override val cacheDir: StorageLocation = location("/LOCAL/cache/", cacheDir)
    override val modsDir: StorageLocation = location("/SD/mods/", File(sdDir, "mods"))
    override val unitsDir: StorageLocation = location("/SD/rustedWarfare/units/", File(sdDir, "units"))
    override val mapsDir: StorageLocation = location("/SD/rustedWarfare/maps/", File(sdDir, "maps"))
    override val savesDir: StorageLocation = location("/SD/rustedWarfare/saves/", File(sdDir, "saves"))
    override val replaysDir: StorageLocation = location("/SD/rustedWarfare/replays/", File(sdDir, "replays"))
    override val screenshotsDir: StorageLocation =
        location("/SD/rustedWarfare/screenshots/", File(sdDir, "screenshots"))
    override val crashReportsFile: StorageLocation =
        StorageLocation("/SD/rustedWarfare/crashes.txt", AndroidPlatformFile(File(sdDir, "crashes.txt")))

    override fun location(kind: StorageKind): StorageLocation = when (kind) {
        StorageKind.ROOT -> rootDir
        StorageKind.LOCAL -> localDir
        StorageKind.CACHE -> cacheDir
        StorageKind.MODS -> modsDir
        StorageKind.UNITS -> unitsDir
        StorageKind.MAPS -> mapsDir
        StorageKind.SAVES -> savesDir
        StorageKind.REPLAYS -> replaysDir
        StorageKind.SCREENSHOTS -> screenshotsDir
        StorageKind.CRASH_REPORTS -> crashReportsFile
    }

    override fun resolveVirtualPath(virtualPath: String): PlatformFile {
        val normalized = virtualPath.replace('', '/')
        return when {
            normalized == rootDir.virtualPath.trimEnd('/') || normalized == rootDir.virtualPath -> rootDir.file
            normalized == localDir.virtualPath.trimEnd('/') || normalized == localDir.virtualPath -> localDir.file
            normalized.startsWith(rootDir.virtualPath) ->
                rootDir.file.resolve(normalized.substring(rootDir.virtualPath.length))
            normalized.startsWith("/SD/") ->
                rootDir.file.resolve(normalized.substring("/SD/".length))
            normalized.startsWith(localDir.virtualPath) ->
                localDir.file.resolve(normalized.substring(localDir.virtualPath.length))
            else -> AndroidPlatformFile(File(normalized))
        }
    }

    override fun createDirectories() {
        listOf(
            rootDir,
            localDir,
            cacheDir,
            modsDir,
            unitsDir,
            mapsDir,
            savesDir,
            replaysDir,
            screenshotsDir,
        ).forEach { it.file.mkdirs() }
    }

    private fun location(virtualPath: String, file: File): StorageLocation {
        return StorageLocation(virtualPath, AndroidPlatformFile(file.absoluteFile))
    }
}

/**
 * Android PlatformFile wrapping java.io.File.
 *
 * Identical in behavior to DesktopPlatformFile — both delegate to java.io.File.
 * On Android API 29+ with scoped storage, SAF (Storage Access Framework) operations
 * are handled separately via AndroidSAF (in android-platform-lib.jar).
 */
class AndroidPlatformFile(
    val javaFile: File,
) : PlatformFile {
    override val path: String get() = javaFile.path
    override val name: String get() = javaFile.name
    override val parent: PlatformFile? get() = javaFile.parentFile?.let(::AndroidPlatformFile)

    override fun exists(): Boolean = javaFile.exists()
    override fun isDirectory(): Boolean = javaFile.isDirectory
    override fun mkdirs(): Boolean = javaFile.mkdirs()

    override fun resolve(relativePath: String): PlatformFile {
        return AndroidPlatformFile(javaFile.resolve(relativePath))
    }

    override fun writeText(text: String, charset: Charset) {
        javaFile.parentFile?.mkdirs()
        javaFile.writeText(text, charset)
    }

    override fun appendText(text: String, charset: Charset) {
        javaFile.parentFile?.mkdirs()
        javaFile.appendText(text, charset)
    }
}
