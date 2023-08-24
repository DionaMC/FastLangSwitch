package dev.diona.fastlangswitch;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class OptifineCompatible {
    private static Class<?> OPTIFINE_LANG_CLASS;
    private static Method OPTIFINE_LANG_RELOADED_METHOD;

    static {
        try {
            OPTIFINE_LANG_CLASS = Class.forName("net.optifine.Lang");
            try {
                OPTIFINE_LANG_RELOADED_METHOD = OPTIFINE_LANG_CLASS.getDeclaredMethod("resourcesReloaded");
                OPTIFINE_LANG_RELOADED_METHOD.setAccessible(true);
            } catch (NoSuchMethodException e) {
                OPTIFINE_LANG_RELOADED_METHOD = null;
            }
        } catch (ClassNotFoundException e) {
            OPTIFINE_LANG_CLASS = null;
        }
    }

    public static void callOptifineReload() {
        if (OPTIFINE_LANG_CLASS != null && OPTIFINE_LANG_RELOADED_METHOD != null) {
            try {
                OPTIFINE_LANG_RELOADED_METHOD.invoke(null);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
