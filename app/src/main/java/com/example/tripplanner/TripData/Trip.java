package com.example.tripplanner.TripData;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;



public class Trip {
    @NonNull
    private String userID;
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String tripName;
    @NonNull
    private String startPoint;
    @NonNull
    private double startPointLatitude;
    @NonNull
    private double startPointLongitude;
    @NonNull
    private String endPoint;
    @NonNull
    private double endPointLatitude;
    @NonNull
    private double endPointLongitude;
    @NonNull
    private String date;
    @NonNull
    private String time;
    @NonNull
    private int tripImg;
    @NonNull
    private String tripStatus;
    /*
    @TypeConverters(DataConverter.class)
    private ArrayList<String> notes;*/
    @NonNull
    private long calendar;

    public Trip(@NonNull String userID, int id, @NonNull String tripName,
                @NonNull String startPoint, double startPointLatitude, double startPointLongitude,
                @NonNull String endPoint, double endPointLatitude, double endPointLongitude,
                @NonNull String date, @NonNull String time, int tripImg, @NonNull String tripStatus,
                long calendar) {
        this.userID = userID;
        this.id = id;
        this.tripName = tripName;
        this.startPoint = startPoint;
        this.startPointLatitude = startPointLatitude;
        this.startPointLongitude = startPointLongitude;
        this.endPoint = endPoint;
        this.endPointLatitude = endPointLatitude;
        this.endPointLongitude = endPointLongitude;
        this.date = date;
        this.time = time;
        this.tripImg = tripImg;
        this.tripStatus = tripStatus;
        this.calendar = calendar;
    }

    public Trip() {
    }

    @NonNull
    public String getUserID() {
        return userID;
    }

    public void setUserID(@NonNull String userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTripName() {
        return tripName;
    }

    public void setTripName(@NonNull String tripName) {
        this.tripName = tripName;
    }

    @NonNull
    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(@NonNull String startPoint) {
        this.startPoint = startPoint;
    }

    public double getStartPointLatitude() {
        return startPointLatitude;
    }

    public void setStartPointLatitude(double startPointLatitude) {
        this.startPointLatitude = startPointLatitude;
    }

    public double getStartPointLongitude() {
        return startPointLongitude;
    }

    public void setStartPointLongitude(double startPointLongitude) {
        this.startPointLongitude = startPointLongitude;
    }

    @NonNull
    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(@NonNull String endPoint) {
        this.endPoint = endPoint;
    }

    public double getEndPointLatitude() {
        return endPointLatitude;
    }

    public void setEndPointLatitude(double endPointLatitude) {
        this.endPointLatitude = endPointLatitude;
    }

    public double getEndPointLongitude() {
        return endPointLongitude;
    }

    public void setEndPointLongitude(double endPointLongitude) {
        this.endPointLongitude = endPointLongitude;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    @NonNull
    public String getTime() {
        return time;
    }

    public void setTime(@NonNull String time) {
        this.time = time;
    }

    public int getTripImg() {
        return tripImg;
    }

    public void setTripImg(int tripImg) {
        this.tripImg = tripImg;
    }

    @NonNull
    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(@NonNull String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public long getCalendar() {
        return calendar;
    }

    public void setCalendar(long calendar) {
        this.calendar = calendar;
    }
}
