package com.example.tripplanner.Home.Fragment;


import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Trip;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


public class FragmentAddTrip extends Fragment {


    public FragmentAddTrip() {
    }

    ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Places.initialize(getContext(), API_KEY);
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_trip, container, false);
        //Method have declared variable
        initComponent();
        //on click listner methods
        editTextStartPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placesAutocompletes(StartPointFlag);
            }
        });
        editTextEndPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placesAutocompletes(ENDPOINTFlag);
            }
        });


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // check place request code
        if (requestCode == StartPointFlag) {
            if (resultCode == RESULT_OK) {
                placeStartPoint = Autocomplete.getPlaceFromIntent(data);

                if (placeStartPoint.getLatLng() != null) {
                    editTextStartPoint.setText(placeStartPoint.getName());
                }
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;

        } else if (requestCode == ENDPOINTFlag) {
            if (resultCode == RESULT_OK) {
                placeEndPoint = Autocomplete.getPlaceFromIntent(data);
                Log.i(TAG, "Place: " + placeEndPoint.getName() + ", " + placeEndPoint.getId());
                editTextEndPoint.setText(placeEndPoint.getName());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i(TAG, status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


    //Method to handle place
    private void placesAutocompletes(int flag) {
        List<Place.Field> fieldList = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                , fieldList).build(getContext());

        startActivityForResult(intent, flag);
    }

    //declare variable
    private void initComponent() {
        editTextTripName = view.findViewById(R.id.ediTxt_tripName);
        textViewTripName = view.findViewById(R.id.txtView_tripName);
        textViewDate = view.findViewById(R.id.txtView_date);
        imageButtonDate = view.findViewById(R.id.btn_date);
        textViewTime = view.findViewById(R.id.txtview_time);
        imageButtonTime = view.findViewById(R.id.btn_time);
        editTextStartPoint = view.findViewById(R.id.editTxt_startPoint);
        editTextStartPoint.setFocusable(false);
        editTextEndPoint = view.findViewById(R.id.editTxt_endPoint);
        editTextEndPoint.setFocusable(false);
        textViewTime2 = view.findViewById(R.id.txtView_Time2);
        textViewDate2 = view.findViewById(R.id.txtView_Date2);
        imageButtonDate2 = view.findViewById(R.id.btn_date2);
        imageButtonDate2.setFocusable(false);
        imageButtonTime2 = view.findViewById(R.id.btn_time2);
        imageButtonTime2.setFocusable(false);
        radioGroup = view.findViewById(R.id.radioGroup_typeTrip);
        radioButtonOneDirection = view.findViewById(R.id.radioBtn_oneDirection);
        radioButtonRoundTrip = view.findViewById(R.id.radioBtn_roundTrip);
        btnSaveTrip = view.findViewById(R.id.btn_saveTrip);
        btnAddNotes = view.findViewById(R.id.btn_addNotes);
        constraintLayoutRoundTrip = view.findViewById(R.id.constraintLayoutAddRound);
    }

   /* public void checkData(){
        Log.i(TAG, "checkData: ");
        if(!TextUtils.isEmpty(editTextTripName.getText())){
            editTextTripName.setError(null);
            if(!TextUtils.isEmpty(editTextStartPoint.getText())){
                editTextStartPoint.setError(null);
                if(!TextUtils.isEmpty(editTextEndPoint.getText())){
                    editTextEndPoint.setError(null);
                    if(!TextUtils.isEmpty(textViewDate.getText())){
                        textViewDate.setError(null);
                        if(!TextUtils.isEmpty(textViewTime.getText())){
                            textViewTime.setError(null);

                            //when edit trip
                            if(AddTripActivity.key==2){
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (placeEndPoint==null) {
                                            HomeActivity.database.tripDAO().EditTrip(AddTripActivity.ID, editTextTripName.getText().toString(), editTextStartPoint.getText().toString(),
                                                    editTextEndPoint.getText().toString(), selectedTrip.getEndPointLat(),
                                                    selectedTrip.getEndPointLong(), textViewDate.getText().toString(), textViewTime.getText().toString(),calenderNormal.getTimeInMillis());
                                            getActivity().finish(); //added by amr
                                            Log.i(TAG, "run: place end null");
                                        }else {
                                            HomeActivity.database.tripDAO().EditTrip(AddTripActivity.ID, editTextTripName.getText().toString(), editTextStartPoint.getText().toString(),
                                                    editTextEndPoint.getText().toString(), placeEndPoint.getLatLng().latitude
                                                    , placeEndPoint.getLatLng().longitude, textViewDate.getText().toString(), textViewTime.getText().toString(),calenderNormal.getTimeInMillis());
                                            getActivity().finish(); //added by amr
                                            Log.i(TAG, "run: place end  not null");
                                        }
                                    }
                                }).start();
                                return;
                            }
                            // add trip object
                            if (resultNotes != null) {

                                trip = new Trip(HomeActivity.fireBaseUseerId, editTextTripName.getText().toString(), placeStartPoint.getName(), placeStartPoint.getLatLng().latitude,
                                        placeStartPoint.getLatLng().longitude, placeEndPoint.getName(), placeEndPoint.getLatLng().latitude, placeEndPoint.getLatLng().longitude,
                                        textViewDate.getText().toString(), textViewTime.getText().toString(), R.drawable.preview,
                                        "upcoming", myPref.getLong("CalendarNormal", 0), resultNotes);
                                if (isRound) {
                                    if (!TextUtils.isEmpty(textViewDate2.getText())) {
                                        textViewDate2.setError(null);
                                        if (!TextUtils.isEmpty(textViewTime2.getText())) {
                                            textViewTime2.setError(null);
                                            //create two obj
                                            Trip tripRound = new Trip(HomeActivity.fireBaseUseerId, editTextTripName.getText().toString() + " Round", placeEndPoint.getName(), placeEndPoint.getLatLng().latitude, placeEndPoint.getLatLng().longitude,
                                                    placeStartPoint.getName(), placeStartPoint.getLatLng().latitude, placeStartPoint.getLatLng().longitude,
                                                    textViewDate2.getText().toString(), textViewTime2.getText().toString(), R.drawable.preview,
                                                    "upcoming", myPref.getLong("CalendarRound", 0), resultNotes);
                                            insertRoom(trip);
                                            insertRoom(tripRound);
                                            getActivity().finish();
                                        } else {
                                            textViewTime2.setError("Valid Time");
                                            Toast.makeText(getContext(), "Please, Enter Valid Time for round trip", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        textViewDate2.setError("Valid Date");
                                        Toast.makeText(getContext(), "Please, Enter Valid Date for round trip", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    insertRoom(trip);
                                    getActivity().finish();
                                }
                            }else{
                                trip = new Trip(HomeActivity.fireBaseUseerId, editTextTripName.getText().toString(), placeStartPoint.getName(), placeStartPoint.getLatLng().latitude,
                                        placeStartPoint.getLatLng().longitude, placeEndPoint.getName(), placeEndPoint.getLatLng().latitude, placeEndPoint.getLatLng().longitude,
                                        textViewDate.getText().toString(), textViewTime.getText().toString(), R.drawable.preview,
                                        "upcoming", calenderNormal.getTimeInMillis(), null);

                                if (isRound) {
                                    if (!TextUtils.isEmpty(textViewDate2.getText())) {
                                        textViewDate2.setError(null);
                                        if (!TextUtils.isEmpty(textViewTime2.getText())) {
                                            textViewTime2.setError(null);
                                            //create two obj
                                            Trip tripRound = new Trip(HomeActivity.fireBaseUseerId, editTextTripName.getText().toString() + " Round", placeEndPoint.getName(), placeEndPoint.getLatLng().latitude, placeEndPoint.getLatLng().longitude,
                                                    placeStartPoint.getName(), placeStartPoint.getLatLng().latitude, placeStartPoint.getLatLng().longitude,
                                                    textViewDate2.getText().toString(), textViewTime2.getText().toString(), R.drawable.preview,
                                                    "upcoming", calendarRound.getTimeInMillis(), null);
                                            insertRoom(trip);
                                            insertRoom(tripRound);
                                            getActivity().finish();

                                        } else {
                                            textViewTime2.setError("Invalid Time");
                                            Toast.makeText(getContext(), "Please, Enter Valid Time for round trip", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        textViewDate2.setError("Invalid Date");
                                        Toast.makeText(getContext(), "Please, Enter Valid Date for round trip", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    insertRoom(trip);
                                    getActivity().finish();
                                }
                            }

                        }else{
                            textViewTime.setError("Valid Time");
                            Toast.makeText(getContext(),"Please, Enter Valid Time",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        textViewDate.setError("Please Enter Valid Date");
                        Toast.makeText(getContext(),"Please, Enter Valid Date",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    editTextStartPoint.setError(" Required End Point");
                    Toast.makeText(getContext(),"Please, Required End Point",Toast.LENGTH_SHORT).show();
                }

            }else{
                editTextEndPoint.setError("Please Enter Valid Start Point");
                Toast.makeText(getContext(),"Please, Required Start Point",Toast.LENGTH_SHORT).show();
            }
        }else{
            editTextTripName.setError("Required");
            Toast.makeText(getContext(),"Please, Required Trip Name",Toast.LENGTH_SHORT).show();

        }
    }*/


    //declare Variables
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
    View view;
    //for plac Api (link-startActivity flag for start and end)
    private static final String API_KEY = "AIzaSyBpK-AM55wLemXfm-ffY9IpHA3MkF5vd0M";
    private static final int StartPointFlag = 1;
    private static final int ENDPOINTFlag = 2;


    public static final String TAG = "AddTripFragment";
    public static final String PREF_NAME = "MY_PREF";
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
    boolean isDateToday = false;
    Boolean isRound = false;
    boolean isDateCorrectRoundTrip = false;
    boolean isTimeCorrectRoundTrip = false;
    boolean isDateTodayRoundTrip = false;
    boolean isFirstTimeSeleceted = false;
    boolean isFirstAddNotes = true;
    Place placeStartPoint;
    Place placeEndPoint;
    Trip trip;
    Trip selectedTrip;
    ArrayList<String> resultNotes;
}