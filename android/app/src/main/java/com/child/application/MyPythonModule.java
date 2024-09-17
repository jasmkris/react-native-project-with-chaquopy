package com.child.application;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.chaquo.python.Python;
import com.chaquo.python.PyObject;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;

public class MyPythonModule extends ReactContextBaseJavaModule {
    // private static final String MODULE_NAME = "MyPythonModule";

    // Context context;
    String TAG = "Python Running";
    // Python py = Python.getInstance();
    // PyObject pyobj = py.getModule("main");

    public MyPythonModule(ReactApplicationContext context) {
        super(context);
        this.context = context.getApplicationContext();
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(context));
        }
    }

    @NonNull
    @Override
    public String getName() {
        return "MyPythonModule";
    }

    @ReactMethod
    public void runPythonCode(String arg, String functionName, Callback callback) {
        Log.d(TAG, "returnFromPython: " + arg + functionName);
        Python py = Python.getInstance();
        PyObject pyObject = py.getModule("main"); // Refers to main.py
        PyObject result;

        if (functionName.equals("add_task")) {
            result = pyObject.callAttr("add_task", arg);
        } else if (functionName.equals("get_tasks")) {
            result = pyObject.callAttr("get_tasks");
        } else {
            result = PyObject.None;
        }

        callback.invoke(result.toString());
    }
}
