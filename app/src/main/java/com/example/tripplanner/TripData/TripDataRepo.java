package com.example.tripplanner.TripData;

import android.app.Application;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;




public class TripDataRepo {
    TripDAO tripDAO;

    public TripDataRepo(Application application){
        TripDatabase db=TripDatabase.getDatabase(application);
        tripDAO= db.tripDAO();
    }

    //Trip Data Method thread here
    public void insert(Trip trip){
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
         tripDAO.insert(trip);

            }
        });

    }
    public void delete(Trip trip){
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                tripDAO.delete(trip);
            }
        });

    }
    public void clear(){
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                tripDAO.clear();
            }
        });


    }
    public void deleteById(String userId, int id){
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                tripDAO.deleteById(userId,id);
            }
        });
    }
    public void deleteUpcomingById(String userId,String status) {
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                tripDAO.deleteUpcomingById(userId,status);
            }
        });

    }
    public void deleteHistoryById(String userId,String status,String status1,String status2){
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                tripDAO.deleteHistoryById(userId,status,status1,status2);
            }
        });
    }

    public List<Trip> selectAll(String userId){
        return tripDAO.selectAll(userId);

  }
    public List<Trip> selectRoom(String userId){
        return tripDAO.selectRoom(userId);
    }
     public Trip selectById(int id){
        return tripDAO.selectById(id);
    }
     public List<Trip> selectHistoryTrip(String userId, String cancleStatus,
                                        String finishedStatus, String missedStatus){
        return tripDAO.selectHistoryTrip(userId,cancleStatus,
                                    finishedStatus,missedStatus);
    }
    public List<Trip> selectUpcomingTrip(String userId, String status){
        return tripDAO.selectUpcomingTrip(userId,status);
    }

    public void updateTripStatus(String userId, int id,
                                 String tripStatus) {
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
               tripDAO.updateTripStatus(userId,id,tripStatus);

            }
        });
    }
    public void EditTrip(int id, String tripName,String startPoint,String endPoint,
                        double endPointLat,double endPointLong,
                         String date,String time,double calendar) {
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
              tripDAO.EditTrip(id,tripName,startPoint,endPoint,
                        endPointLat,endPointLong,date,time,calendar);

            }
        });
    }
    public void getCountTripType(String userId ,String status){
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
               tripDAO.getCountTripType(userId,status);

            }
        });
    }

    public void EditNotes(int id, String notes) {
        TripDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
             tripDAO.EditNotes(id,notes);

            }
        });
    }
}
