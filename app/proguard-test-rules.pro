# The androidTest APK is not processed by R8 but calls into the minified app
# APK, so the app entry points referenced from instrumented tests must keep
# their original names. Only applied to the "minified" test build type.
-keep class com.raks.pvcreator.util.StringResNameKt { *; }

# The androidTest APK resolves these from the app classpath under their
# original names, but R8 would otherwise strip or rename them in the app APK.
-keep class androidx.tracing.** { *; }
-keep class androidx.concurrent.futures.** { *; }
-keep class kotlin.** { *; }
-keep class kotlinx.coroutines.** { *; }

# Truth/Guava annotations reference compiler-only types that do not exist on
# Android; safe to ignore when R8 processes the androidTest APK.
-dontwarn javax.lang.model.element.Modifier
