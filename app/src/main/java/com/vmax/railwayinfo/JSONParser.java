package com.vmax.railwayinfo;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {

    private ArrayList<PNRBean> PNRBeanList = new ArrayList();
    private ArrayList<RouteBean> routeList = new ArrayList();
    private String data;
    Context context;

    public JSONParser(Context context,String data) {
        this.data = data;
        this.context = context;
    }
    

    ArrayList<PNRBean> getPNRBeanList() throws Exception {
        if (this.data == null) {
            return null;
        }
        try{
            JSONObject rootObject = new JSONObject(data);

            PNRBean pb = new PNRBean();
            if (rootObject.has("from_station")) {
                JSONObject from_station = rootObject.getJSONObject("from_station");
                pb.setFromName(from_station.getString("name"));
            }if (rootObject.has("boarding_point")) {
                JSONObject boarding_point = rootObject.getJSONObject("boarding_point");
                pb.setBoardingPoint(boarding_point.getString("name"));
            }if (rootObject.has("to_station")) {
                JSONObject to_station = rootObject.getJSONObject("to_station");
                pb.setToName(to_station.getString("name"));
            }if (rootObject.has("train")) {
                JSONObject trNum = rootObject.getJSONObject("train");
                pb.setTrainNum(trNum.getString("number"));
            }if (rootObject.has("total_passengers")) {
                pb.setTotalPassengers(rootObject.getString("total_passengers"));
            }if (rootObject.has("doj")) {
                pb.setDoj(rootObject.getString("doj"));
            }if (rootObject.has("pnr")) {
                pb.setPnr(rootObject.getString("pnr"));
            }
                this.PNRBeanList.add(pb);
            return this.PNRBeanList;
        } catch (JSONException ex) {
            Log.e("JSON Parsing", "Not able to parse", ex);
            throw new Exception("Something went wrong on server.");
        }
    }

    public JSONArray getJSONArray(String array) {
        JSONArray jsonArray = null;
        if (array != null) {
            try {
                jsonArray = new JSONObject(array).getJSONArray("route");
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error in parsing", e);
            }
        }
        return jsonArray;
    }

    ArrayList<RouteBean> getRouteInfo() throws Exception {

        if (this.data == null) {
            return null;
        }
        try{
            JSONObject rootObject = new JSONObject(data);


            JSONArray jsonArray = getJSONArray(this.data);

            for (int i = 0; i < jsonArray.length(); i++) {

                RouteBean pb = new RouteBean();
                if (rootObject.has("train")) {
                    JSONObject trNum = rootObject.getJSONObject("train");
                    pb.setTrainNum(trNum.getString("number"));
                    pb.setTrainName(trNum.getString("name"));
                }

                JSONObject routeObject = jsonArray.getJSONObject(i);

                if (routeObject.has("schdep"))
                    pb.setSchdep(routeObject.getString("schdep"));

                if (routeObject.has("station")){
                    JSONObject station = routeObject.getJSONObject("station");
                    pb.setName(station.getString("name"));
                    pb.setLatitude(station.getString("lat"));
                    pb.setLongitude(station.getString("lng"));

                }
                routeList.add(pb);
            }

            return routeList;
        } catch (JSONException ex) {
            Log.e("JSON Parsing", "Not able to parse", ex);
            throw new Exception("Something went wrong on server.");
        }
    }
}


