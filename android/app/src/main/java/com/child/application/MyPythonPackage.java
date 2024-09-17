package com.child.application;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.child.application.MyPythonModule;

public class MyPythonPackage implements ReactPackage {

    @Override
    public List<ViewManager> screateViewManager(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modules = new ArrayList<>();
        // modules.add(new InvokePython(reactContext)); // Register the module
        modules.add(new MyPythonModule(reactContext)); // Register the module
        return modules;
    }

}
