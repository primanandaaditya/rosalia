package org.apache.cordova.contacts;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

public class PermissionHelper {
    private static final String LOG_TAG = "CordovaPermissionHelper";

    public static void requestPermission(CordovaPlugin plugin, int requestCode, String permission) {
        requestPermissions(plugin, requestCode, new String[]{permission});
    }

    public static void requestPermissions(CordovaPlugin plugin, int requestCode, String[] permissions) {
        try {
            CordovaInterface.class.getDeclaredMethod("requestPermissions", new Class[]{CordovaPlugin.class, Integer.TYPE, String[].class}).invoke(plugin.cordova, new Object[]{plugin, Integer.valueOf(requestCode), permissions});
        } catch (NoSuchMethodException e) {
            LOG.m19d(LOG_TAG, "No need to request permissions " + Arrays.toString(permissions));
            deliverPermissionResult(plugin, requestCode, permissions);
        } catch (IllegalAccessException illegalAccessException) {
            LOG.m23e(LOG_TAG, "IllegalAccessException when requesting permissions " + Arrays.toString(permissions), (Throwable) illegalAccessException);
        } catch (InvocationTargetException invocationTargetException) {
            LOG.m23e(LOG_TAG, "invocationTargetException when requesting permissions " + Arrays.toString(permissions), (Throwable) invocationTargetException);
        }
    }

    public static boolean hasPermission(CordovaPlugin plugin, String permission) {
        try {
            return ((Boolean) CordovaInterface.class.getDeclaredMethod("hasPermission", new Class[]{String.class}).invoke(plugin.cordova, new Object[]{permission})).booleanValue();
        } catch (NoSuchMethodException e) {
            LOG.m19d(LOG_TAG, "No need to check for permission " + permission);
            return true;
        } catch (IllegalAccessException illegalAccessException) {
            LOG.m23e(LOG_TAG, "IllegalAccessException when checking permission " + permission, (Throwable) illegalAccessException);
            return false;
        } catch (InvocationTargetException invocationTargetException) {
            LOG.m23e(LOG_TAG, "invocationTargetException when checking permission " + permission, (Throwable) invocationTargetException);
            return false;
        }
    }

    private static void deliverPermissionResult(CordovaPlugin plugin, int requestCode, String[] permissions) {
        int[] requestResults = new int[permissions.length];
        Arrays.fill(requestResults, 0);
        try {
            CordovaPlugin.class.getDeclaredMethod("onRequestPermissionResult", new Class[]{Integer.TYPE, String[].class, int[].class}).invoke(plugin, new Object[]{Integer.valueOf(requestCode), permissions, requestResults});
        } catch (NoSuchMethodException noSuchMethodException) {
            LOG.m23e(LOG_TAG, "NoSuchMethodException when delivering permissions results", (Throwable) noSuchMethodException);
        } catch (IllegalAccessException illegalAccessException) {
            LOG.m23e(LOG_TAG, "IllegalAccessException when delivering permissions results", (Throwable) illegalAccessException);
        } catch (InvocationTargetException invocationTargetException) {
            LOG.m23e(LOG_TAG, "InvocationTargetException when delivering permissions results", (Throwable) invocationTargetException);
        }
    }
}
