package com.child.application;

import android.app.*;
// import com.child.utils.*;
import android.os.Bundle;
import android.util.Log;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;

public class MainActivity extends ReactActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        Log.d("onCreate", "onCreate: " + "python started");
        // Initialize Python if it's not already started
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py = Python.getInstance();
        PyObject pyObject = py.getModule("main"); // Python file name without .py
        try {
            PyObject result = pyObject.callAttr("run_task", "parameter");
            String resultString = result.toString();
            Log.d("PythonResult", resultString); // Log or use the result as needed
        } catch (Exception e) {
            Log.e("PythonError", "Error executing Python code", e);
        }
    }

    @Override
    protected String getMainComponentName() {
        return "startProject"; // Replace with your app name from app.json
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new MainActivityDelegate(this, getMainComponentName());
    }

    public static class MainActivityDelegate extends ReactActivityDelegate {
        public MainActivityDelegate(ReactActivity activity, String mainComponentName) {
            super(activity, mainComponentName);
        }

        @Override
        protected ReactRootView createRootView() {
            ReactRootView reactRootView = new ReactRootView(getContext());
            reactRootView.setIsFabric(BuildConfig.IS_NEW_ARCHITECTURE_ENABLED);
            return reactRootView;
        }

        @Override
        protected boolean isConcurrentRootEnabled() {
            return BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
        }
    }
}
