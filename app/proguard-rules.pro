# Label keys live in assets/database/pvcreator.db and are resolved by name at
# runtime (see util/StringResName.kt), so R8 cannot detect these fields being
# read and would otherwise strip R$string entirely from the release APK.
-keep class com.raks.pvcreator.R$string {
    public static int *;
}
