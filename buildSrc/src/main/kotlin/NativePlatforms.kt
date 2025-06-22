public enum class NativePlatforms(val platformName: String) {
    ARM7("armeabi-v7a"),
    ARM8("arm64-v8a"),
    X86_64("x86_64"),
    X86("x86");

    override fun toString(): String = platformName
}
