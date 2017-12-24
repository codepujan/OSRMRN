package com.rnosrm;

/**
 * Created by pujanpaudel on 12/19/17.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Polyline;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import org.osmdroid.api.IMapController;

import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Marker.OnMarkerDragListener;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;
import org.osmdroid.views.overlay.infowindow.InfoWindow;
import org.osmdroid.views.overlay.infowindow.MarkerInfoWindow;


import java.util.ArrayList;

/**
 * This is the implementation of OSMBonusPack tutorials.
 * Sections of code can be commented/uncommented depending on the progress in the tutorials.
 *
 * @author M.Kergall
 * @see <a href="https://github.com/MKergall/osmbonuspack">OSMBonusPack on GitHub</a>
 */
public class MapActivity extends Activity {

    MapView map;

    @Override protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //Introduction
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = (MapView) findViewById(R.id.map);


        /**
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        GeoPoint startPoint = new GeoPoint(31.33, -89.33);
        IMapController mapController = map.getController();
        mapController.setZoom(20);
        mapController.setCenter(startPoint);
        map.setMapOrientation(20.0f);

        //0. Using the Marker overlay
        Marker startMarker = new Marker(map);
        startMarker.setPosition(startPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setTitle("Start point");
        //startMarker.setIcon(getResources().getDrawable(R.drawable.marker_kml_point).mutate());
        //startMarker.setImage(getResources().getDrawable(R.drawable.ic_launcher));
        //startMarker.setInfoWindow(new MarkerInfoWindow(R.layout.bonuspack_bubble_black, map));
        startMarker.setDraggable(true);
        startMarker.setOnMarkerDragListener(new OnMarkerDragListenerDrawer());
        map.getOverlays().add(startMarker);
         **/




    }




    //0. Using the Marker and Polyline overlays - advanced options
    class OnMarkerDragListenerDrawer implements Marker.OnMarkerDragListener {
        ArrayList<GeoPoint> mTrace;
        Polyline mPolyline;

        OnMarkerDragListenerDrawer() {
            mTrace = new ArrayList<GeoPoint>(100);
            mPolyline = new Polyline();
            mPolyline.setColor(0xAA0000FF);
            mPolyline.setWidth(2.0f);
            mPolyline.setGeodesic(true);
            map.getOverlays().add(mPolyline);
        }

        @Override public void onMarkerDrag(Marker marker) {
            //mTrace.add(marker.getPosition());
        }

        @Override public void onMarkerDragEnd(Marker marker) {
            mTrace.add(marker.getPosition());
            mPolyline.setPoints(mTrace);
            map.invalidate();
        }

        @Override public void onMarkerDragStart(Marker marker) {
            //mTrace.add(marker.getPosition());
        }
    }
}