package com.example.manasshrestha.googlemapsdemo;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
    ArrayList<LatLng> directionPoint = new ArrayList<>();
    Context context;
    MainActivity mainActivity;
    public JSONAsyncTask(Context context){
        mainActivity = (MainActivity) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Boolean doInBackground(String... urls) {
        try {

            //------------------>>
            HttpGet httppost = new HttpGet("http://maps.googleapis.com/maps/api/directions/json?origin=27.712066,85.309331&destination=27.716490,%2085.315275&sensor=false");
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httppost);

            // StatusLine stat = response.getStatusLine();
            int status = response.getStatusLine().getStatusCode();

            if (status == 200) {
                HttpEntity entity = response.getEntity();
                String data = EntityUtils.toString(entity);


                JSONObject jsono = new JSONObject(data);
                JSONArray arrayList = jsono.getJSONArray("routes");


                for (int i =0; i<arrayList.length();i++){
                    JSONObject routes = (JSONObject) arrayList.get(i);

                    JSONArray legsJSONArray = routes.getJSONArray("legs");
//                    android.util.Log.e("data", legsJSONArray.toString());
                    for (int j = 0; j<legsJSONArray.length();j++){
                        JSONObject legs = (JSONObject) legsJSONArray.get(j);

                        JSONArray stepsJSONArray = legs.getJSONArray("steps");

                        for (int k = 0; k < stepsJSONArray.length();k++){
                            JSONObject steps = (JSONObject) stepsJSONArray.get(k);

                            JSONObject endLocations = steps.getJSONObject("end_location");
                            String endLat = endLocations.getString("lat");
                            String endLong = endLocations.getString("lng");

                            JSONObject startLocation = steps.getJSONObject("start_location");
                            String startLat = startLocation.getString("lat");
                            String startLong = startLocation.getString("lng");

                            LatLng start = new LatLng(Double.parseDouble(endLat),Double.parseDouble(endLong));
                            directionPoint.add(start);

                            android.util.Log.e("data steps","end lat"+ endLong + " start long " + startLong);
                        }



                    }

                }

                return true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {

            e.printStackTrace();
        }
        return false;
    }

    protected void onPostExecute(Boolean result) {
        PolylineOptions rectLine = new PolylineOptions().width(10).color(Color.RED);

        for(int i = 0 ; i < directionPoint.size() ; i++) {
            rectLine.add(directionPoint.get(i));
        }

        mainActivity.googleMap.addPolyline(rectLine);
    }
}