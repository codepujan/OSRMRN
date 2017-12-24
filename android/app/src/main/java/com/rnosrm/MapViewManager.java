package com.rnosrm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.Road;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

import javax.annotation.Nullable;

/**
 * Created by pujanpaudel on 12/19/17.
 */

public class MapViewManager extends SimpleViewManager<MapView> {

    public  static final String REACT_CLASS="OSRMAndroidMapView";
    private Activity mActivity;
    private Context ctx;

    public  MapViewManager(){

   // mActivity=activity;


    }
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected MapView createViewInstance(ThemedReactContext reactContext) {
        ctx=reactContext;
        return new MapView(reactContext);

    }



    @ReactProp(name="zoomLevel")
    public  void setZoomLevel(MapView view,@Nullable int zoomLevel){

        IMapController mapController = view.getController();
        mapController.setZoom(zoomLevel);
        view.invalidate();


    }
    @ReactProp(name = "location")
    public void setLocation(MapView view, @Nullable ReadableMap locationObject){


        view.setTileSource(TileSourceFactory.MAPNIK);
        view.setBuiltInZoomControls(true);
        view.setMultiTouchControls(true);
        //31.3225178,-89.3174118
        GeoPoint startPoint = new GeoPoint(locationObject.getDouble("latitude"),locationObject.getDouble("longitude"));
        IMapController mapController = view.getController();
        //mapController.setZoom(13);
        mapController.setCenter(startPoint);





        Marker startMarker = new Marker(view);
        startMarker.setPosition(startPoint);

        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        view.getOverlays().add(startMarker);
        startMarker.setIcon(ctx.getResources().getDrawable(R.drawable.marker_kml_point));
        startMarker.setTitle("Start point");



        //Covering the region with circle
        float radius=3000; // M I guess
        Polygon circle = new Polygon(ctx);
        circle.setPoints(Polygon.pointsAsCircle(startPoint,radius));


        circle.setFillColor(Color.TRANSPARENT);
        circle.setStrokeColor(Color.RED);
        circle.setStrokeWidth(3);

        view.getOverlays().add(circle);


        view.invalidate();

    }


    @ReactProp(name="route")
    public void setRoute(MapView view, @Nullable ReadableArray locations){


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ReadableMap firstMap=locations.getMap(0);
        GeoPoint startPoint = new GeoPoint(firstMap.getDouble("latitude"),firstMap.getDouble("longitude"));



        ReadableMap secondMap=locations.getMap(1);
       // GeoPoint startgeoPoint=new GeoPoint(locations.get)
        GeoPoint endPoint = new GeoPoint(secondMap.getDouble("latitude"),secondMap.getDouble("longitude"));

        Marker endMarker = new Marker(view);
        endMarker.setPosition(endPoint);

        endMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        endMarker.setIcon(ctx.getResources().getDrawable(R.drawable.marker_kml_point));
        endMarker.setTitle("End point");


        view.getOverlays().add(endMarker);



        //Road Part
        OSRMRoadManager roadManager = new OSRMRoadManager(ctx);


        ArrayList<GeoPoint> waypoints = new ArrayList<GeoPoint>();
        waypoints.add(startPoint);
        waypoints.add(endPoint);

        Road road = roadManager.getRoad(waypoints);
        Polyline roadOverlay = RoadManager.buildRoadOverlay(road);


        view.getOverlays().add(roadOverlay);
        view.invalidate();



    }
}
