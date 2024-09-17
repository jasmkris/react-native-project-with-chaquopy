package com.child.application;

import android.app.Application;
import android.content.Context;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.soloader.SoLoader;

import java.lang.reflect.InvocationTargetException;

import java.util.Arrays;
import java.util.List;

// Import your custom package
import com.child.application.MyPythonPackage;
import com.child.application.MyPythonModule;
import com.facebook.react.shell.MainReactPackage;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            @SuppressWarnings("UnnecessaryLocalVariable")
            List<ReactPackage> packages = new PackageList(this).getPackages();
            // packages.add(new ButtonAnimationPackage());
            packages.add(new MyPythonPackage());
            return packages;
        }

        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    private final ReactNativeHost mNewArchitectureNativeHost = new
        MyPythonModule(this);

        @Override
        public ReactNativeHost getReactNativeHost() {
        if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
        return mNewArchitectureNativeHost;
        } else {
        return mReactNativeHost;
        }
    }

    // @Override
    // public void onCreate() {
    // super.onCreate();
    // SoLoader.init(this, false);
    // }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeFlipper(this, getReactNativeHost().getReactInstanceManager());

        // Add this line to register the Python module
        SoLoader.init(this, false);
        new MyPythonModule(getReactNativeHost().getReactInstanceManager().getCurrentReactContext());
    }

    // private static void initializeFlipper(
    //   Context context, ReactInstanceManager reactInstanceManager) {
    //     if (BuildConfig.DEBUG) {
    //     try {
    //         /*
    //         * We use reflection here to pick up the class that initializes Flipper,
    //         * since Flipper library is not available in release mode
    //         */
    //         Class<?> aClass = Class.forName("com.research.ReactNativeFlipper");
    //         aClass
    //             .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
    //             .invoke(null, context, reactInstanceManager);
    //     } catch (ClassNotFoundException e) {
    //         e.printStackTrace();
    //     } catch (NoSuchMethodException e) {
    //         e.printStackTrace();
    //     } catch (IllegalAccessException e) {
    //         e.printStackTrace();
    //     } catch (InvocationTargetException e) {
    //         e.printStackTrace();
    //     }
    //     }
    // }
}
