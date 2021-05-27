package com.koisoftware.misc;

import java.util.HashMap;
import java.util.Map;

public class DesignUndergroundSubway {
    //Time complexity : O(1) for all methods
    //Space complexity : O(P + S^2), where S is the number of stations on the network,
    // and P is the number of passengers making a journey concurrently during peak time.
    //checkIn + checkOut = O(P)
    //journeyData = O(S), but eventually all stations will be in the map so O(S^2)

    Map<String, RouteInfo> routeMap;
    Map<Integer, TravelInfo> travelMap;

    public DesignUndergroundSubway() {
        routeMap = new HashMap();
        travelMap = new HashMap();
    }

    public void checkIn(int id, String stationName, int t) {
        travelMap.put(id, new TravelInfo(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        TravelInfo sourceInfo = travelMap.get(id);
        travelMap.remove(id);
        String routeKey = getRouteKey(sourceInfo.startStation, stationName);
        RouteInfo info = routeMap.get(routeKey);
        if (info == null) {
            routeMap.put(routeKey, new RouteInfo((t - sourceInfo.startTime), 1));
        } else {
            info.totalTravelTime += (t - sourceInfo.startTime);
            info.travelCount++;
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeKey = getRouteKey(startStation, endStation);
        RouteInfo info = routeMap.get(routeKey);
        return ((double) (info.totalTravelTime) / (info.travelCount));
    }

    class TravelInfo {
        String startStation;
        int startTime;

        public TravelInfo(String startStation, int startTime) {
            this.startStation = startStation;
            this.startTime = startTime;
        }
    }

    class RouteInfo {
        int totalTravelTime;
        int travelCount;

        public RouteInfo(int time, int count) {
            totalTravelTime = time;
            travelCount = count;
        }
    }

    private String getRouteKey(String startStation, String endStation) {
        return startStation + "->" + endStation;
    }
}
