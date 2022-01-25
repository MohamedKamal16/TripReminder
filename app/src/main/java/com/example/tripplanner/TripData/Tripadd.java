package com.example.tripplanner.TripData;

import java.io.Serializable;

public class Tripadd implements Serializable {
    private String tripName;
    private String startPoint;
    private String endPoint;
    private String dateTrip;
    private String timeTrip;
   //note
    public Tripadd(String tripName, String startPoint, String endPoint, String dateTrip, String timeTrip) {
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.dateTrip = dateTrip;
        this.timeTrip = timeTrip;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getDateTrip() {
        return dateTrip;
    }

    public void setDateTrip(String dateTrip) {
        this.dateTrip = dateTrip;
    }

    public String getTimeTrip() {
        return timeTrip;
    }

    public void setTimeTrip(String timeTrip) {
        this.timeTrip = timeTrip;
    }
}
