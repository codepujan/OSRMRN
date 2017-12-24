package com.rnosrm;

import android.app.Activity;
import android.os.StrictMode;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by pujanpaudel on 12/19/17.
 */

public class MapViewPackage implements ReactPackage
{

    private Activity mActivity;


    MapViewPackage(){
       // mActivity=activity;
    }



    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }


    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList(
                new MapViewManager()
        );
    }
}
