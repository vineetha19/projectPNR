package com.vmax.railwayinfo;

import java.util.ArrayList;

/**
 * Created by welcome on 3/29/2018.
 */

public class RouteBean {

    private ArrayList<String> routeData;
    private ArrayList<String> lat;
    private ArrayList<String> lon;
    private String trainNum;
    private String trainName;
    private String schdep;
    private String name;
    private String latitude;
    private String longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }




    public ArrayList<String> getLat() {
        return lat;
    }

    public void setLat(ArrayList<String> lat) {
        this.lat = lat;
    }

    public ArrayList<String> getLon() {
        return lon;
    }

    public void setLon(ArrayList<String> lon) {
        this.lon = lon;
    }

    public ArrayList<String> getRouteData() {
        return routeData;
    }

    public void setRouteData(ArrayList<String> routeData) {
        this.routeData = routeData;
    }

    public String getSchdep() {
        return schdep;
    }

    public void setSchdep(String schdep) {
        this.schdep = schdep;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }
}
