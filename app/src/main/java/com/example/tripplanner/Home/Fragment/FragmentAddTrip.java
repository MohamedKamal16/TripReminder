package com.example.tripplanner.Home.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Trip;
import com.google.android.gms.location.places.Place;

import java.util.ArrayList;
import java.util.Calendar;


public class FragmentAddTrip extends Fragment {


    public FragmentAddTrip(){};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_trip, container, false);
    }


    EditText editTextTripName;
    EditText editTextStartPoint;
    EditText editTextEndPoint;
    TextView textViewTripName;
    TextView textViewDate;
    TextView textViewDate2;
    ImageButton btnAddNotes;
    Button btnSaveTrip;
    ImageButton imageButtonDate;
    ImageButton imageButtonDate2;
    TextView textViewTime;
    TextView textViewTime2;
    ImageButton imageButtonTime;
    ImageButton imageButtonTime2;
    RadioButton radioButtonOneDirection;
    RadioButton radioButtonRoundTrip;
    RadioGroup radioGroup;
    ConstraintLayout constraintLayoutRoundTrip;


    public static final String TAG = "AddTripFragment";
    private static final String apiKey = "AIzaSyAKXUZsOm7RLbPEAQQxp6TZsU9YWLeh5Pg";
    private static final int AUTOCOMPLETE_REQUEST_CODE_STARTPOINT = 1;
    private static final int AUTOCOMPLETE_REQUEST_CODE_ENDPOINT = 2;
    public static final String PREF_NAME="MY_PREF";

    Calendar calender = Calendar.getInstance();
    final int year = calender.get(Calendar.YEAR);
    final int month = calender.get(Calendar.MONTH);
    final int day = calender.get(Calendar.DAY_OF_MONTH);

    SharedPreferences myPref;
    Calendar calenderNormal;
    Calendar calendarRound;
    String min;
    boolean isDateCorrect = false;
    boolean isTimeCorrect = false;
    boolean isDateToday=false;
    Boolean isRound = false;
    boolean isDateCorrectRoundTrip = false;
    boolean isTimeCorrectRoundTrip = false;
    boolean isDateTodayRoundTrip=false;
    boolean isFirstTimeSeleceted=false;
    boolean isFirstAddNotes=true;
    Place placeStartPoint;
    Place placeEndPoint;
    Trip trip;
    Trip selectedTrip;
    ArrayList<String> resultNotes;
}