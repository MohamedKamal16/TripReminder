package com.example.tripplanner.TripData;
//create database

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Trip.class}, version = 1, exportSchema = false)
    public abstract class TripDatabase extends RoomDatabase {
        public abstract TripDAO tripDAO();

      /*  //to make one object from database
        private static volatile TripDatabase INSTANCE;


        static TripDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (TripDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                TripDatabase.class, "Trip_database")

                                .fallbackToDestructiveMigration()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }*/
    }

