package com.example.tripplanner.TripData;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import java.util.List;

//use this class only to deal with room database //to solve rotate problem
public class TripViewModel extends AndroidViewModel {
    TripDataRepo tripDataRepo;
    public TripViewModel(@NonNull Application application){
        super(application);
        tripDataRepo=new TripDataRepo(application);
    }

    public void insert(Trip trip ){
        tripDataRepo.insert(trip);
        }
    public void delete(Trip trip){
        tripDataRepo.delete(trip);
    }
    public void clear(){
        tripDataRepo.clear();
    }
    public void deleteById(String userId, int id){
        tripDataRepo.deleteById(userId,id);
    }
    public void deleteUpcomingById(String userId,String status) {
        tripDataRepo.deleteUpcomingById(userId,status);
    }

    public void deleteHistoryById(String userId,String status,String status1,String status2){
      tripDataRepo.deleteHistoryById(userId,status,status1,status2);
    }

    public List<Trip> selectAll(String userId){
        return tripDataRepo.selectAll(userId);

    }
    public List<Trip> selectRoom(String userId){
        return tripDataRepo.selectRoom(userId);
    }
    public Trip selectById(int id){
        return tripDataRepo.selectById(id);
    }
    public List<Trip> selectHistoryTrip(String userId, String cancleStatus,
                                        String finishedStatus, String missedStatus){
        return tripDataRepo.selectHistoryTrip(userId,cancleStatus,
                finishedStatus,missedStatus);
    }
    public List<Trip> selectUpcomingTrip(String userId, String status){
        return tripDataRepo.selectUpcomingTrip(userId,status);
    }

    public void updateTripStatus(String userId, int id,
                                 String tripStatus) {
       tripDataRepo.updateTripStatus(userId,id,tripStatus);

    }
    public void EditTrip(int id, String tripName,String startPoint,String endPoint,
                         double endPointLat,double endPointLong,
                         String date,String time,double calendar) {
       tripDataRepo.EditTrip(id,tripName,startPoint,endPoint,
                        endPointLat,endPointLong,date,time,calendar);
    }

    public void getCountTripType(String userId ,String status){
        tripDataRepo.getCountTripType(userId,status);

    }

    public void EditNotes(int id, String notes) {
        tripDataRepo.EditNotes(id,notes);

    }
}
