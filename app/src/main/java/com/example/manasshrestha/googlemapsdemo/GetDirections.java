package com.example.manasshrestha.googlemapsdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by ManasShrestha on 8/2/15.
 */
class GetDirections extends AsyncTask<String, String, String> {
    ProgressDialog pDialog;
    Context context;
    List<LatLng> polyz;

    public GetDirections(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        pDialog = new ProgressDialog(context);
//        pDialog.setMessage("Loading route. Please wait...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);
//        pDialog.show();
    }

    protected String doInBackground(String... args) {

        String stringUrl = "https://maps.googleapis.com/maps/api/directions/json?origin=Toronto&destination=Montreal&key=AIzaSyCvdojukTDTU2mn9PHdIVPhjxQkzfIO4hg" ;
                StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(stringUrl);
            HttpURLConnection httpconn = (HttpURLConnection) url
                    .openConnection();
            if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(httpconn.getInputStream()),
                        8192);
                String strLine = null;

                while ((strLine = input.readLine()) != null) {
                    response.append(strLine);
                }
                input.close();
            }

            String jsonOutput = response.toString();

//            JSONObject jsonObject = new JSONObject(jsonOutput);
//
//            // routesArray contains ALL routes
//            JSONArray routesArray = jsonObject.getJSONArray("routes");
//            // Grab the first route
//            JSONObject route = routesArray.getJSONObject(0);
//
//            JSONObject poly = route.getJSONObject("overview_polyline");
//            String polyline = poly.getString("points");
            android.util.Log.e("error tag", jsonOutput.toString());

        } catch (Exception e) {
//            pDialog.dismiss();
            android.util.Log.e("error tag", e.toString());
        }

        return null;

    }

    protected void onPostExecute(String file_url) {

//        for (int i = 0; i < polyz.size() - 1; i++) {
//            LatLng src = polyz.get(i);
//            LatLng dest = polyz.get(i + 1);
//            Polyline line = map.addPolyline(new PolylineOptions()
//                    .add(new LatLng(src.latitude, src.longitude),
//                            new LatLng(dest.latitude, dest.longitude))
//                    .width(2).color(Color.RED).geodesic(true));
//
//        }
//        pDialog.dismiss();

    }
}
