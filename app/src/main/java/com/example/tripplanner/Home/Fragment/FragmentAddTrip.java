package com.example.tripplanner.Home.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

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

import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Trip;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.location.places.Place;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class FragmentAddTrip extends Fragment {


    private LayoutInflater inflater;
    private ViewGroup container;
    private Bundle savedInstanceState;

    public FragmentAddTrip() {
    }

    ;

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
        View view = inflater.inflate(R.layout.fragment_add_trip, container, false);
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

        Log.i(TAG, "onCreateView: ");
       /* if (AddTripActivity.key == 2) {
            btnSaveTrip.setText("Edit");
            radioGroup.setVisibility(View.GONE);
            constraintLayoutRoundTrip.setVisibility(View.GONE);
            btnAddNotes.setVisibility(View.GONE);
            new LoadRoomData().execute();*/
        getParentFragmentManager().setFragmentResultListener("requestkey", this, new FragmentResultListener() {


            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                //add date and time in text view
                resultNotes=new ArrayList();
                resultNotes = result.getStringArrayList("bundleKey");
                String date=result.getString("date");
                String time=result.getString("time");
                String date2=result.getString("date2");
                String time2=result.getString("time2");
                textViewDate.setText(date);
                textViewTime.setText(time);
                textViewDate2.setText(date2);
                textViewTime2.setText(time2);
                Log.i(TAG, "onFragmentResult: "+resultNotes+".."+date+".."+time+date2+".."+time2);

            }
        });
        imageButtonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calenderDate(textViewDate, 1,calenderNormal);
                textViewTime.setText("");
            }
        });
        imageButtonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDateCorrect)
                    calenderTime(textViewTime, 1,calenderNormal);
                else
                    Toast.makeText(getContext(), "Please choose date first", Toast.LENGTH_SHORT).show();

                isFirstTimeSeleceted = true;
            }
        });
        imageButtonDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDateCorrect) {
                    calenderDate(textViewDate2, 2,calendarRound);
                    textViewTime2.setText("");
                }else
                    Toast.makeText(getContext(), "Please choose date of the first trip", Toast.LENGTH_SHORT).show();

            }
        });
        imageButtonTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isDateCorrectRoundTrip && isFirstTimeSeleceted)
                    calenderTime(textViewTime2, 2,calendarRound);
                else
                    Toast.makeText(getContext(), "Please choose date of the round trip", Toast.LENGTH_SHORT).show();
            }
        });


        return  view;
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
        outState.putString("Date", textViewDate.getText().toString());
        outState.putString("Time", textViewTime.getText().toString());
        outState.putString("DateRound", textViewDate2.getText().toString());
        outState.putString("TimeRound", textViewTime2.getText().toString());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            textViewDate.setText(savedInstanceState.getString("Date"));
            textViewTime.setText(savedInstanceState.getString("Time"));
            textViewDate2.setText(savedInstanceState.getString("DateRound"));
            textViewTime2.setText(savedInstanceState.getString("TimeRound"));
        }
        Log.i(TAG, "onActivityCreated: ");
    }




    private void calenderTime(TextView textViewTime1, int check, Calendar incomingCal) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHours, int selectedMinute) {
                int nowHour;
                int nowMin;
                Log.i(TAG, "hours: " + selectedHours + " minutes: " + selectedMinute);
                Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2:00"));
                Date currentLocalTime = cal.getTime();
                DateFormat date = new SimpleDateFormat("HH:mm");
                String localTime = date.format(currentLocalTime);
                if(check==1){
                    String[] localTimeSplit = localTime.split(":");
                    nowHour = Integer.valueOf(localTimeSplit[0]);
                    nowMin =  Integer.valueOf(localTimeSplit[1]);
                }
                else{
                    String[] localTimeFirstTrip = textViewTime.getText().toString().split(":");
                    nowHour = Integer.valueOf(localTimeFirstTrip[0]);
                    nowMin = Integer.valueOf(localTimeFirstTrip[1]);
                }
                if (isDateToday || isDateTodayRoundTrip) {
                    if (selectedHours > nowHour) {
                        if (check == 1) {
                            isTimeCorrect = true;
                        } else {
                            isTimeCorrectRoundTrip = true;
                        }

                        incomingCal.set(Calendar.HOUR_OF_DAY,selectedHours);
                        incomingCal.set(Calendar.MINUTE,selectedMinute);
                        incomingCal.set(Calendar.SECOND,0);
                        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                        textViewTime1.setText(format.format(incomingCal.getTime()));
                    } else {
                        if (selectedHours == nowHour) {
                            if (selectedMinute > nowMin) {
                                if (check == 1)
                                    isTimeCorrect = true;
                                else
                                    isTimeCorrectRoundTrip = true;

                                incomingCal.set(Calendar.HOUR_OF_DAY,selectedHours);
                                incomingCal.set(Calendar.MINUTE,selectedMinute);
                                incomingCal.set(Calendar.SECOND,0);
                                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                                textViewTime1.setText(format.format(incomingCal.getTime()));

                            } else {
                                Toast.makeText(getContext(), "Time is not correct", Toast.LENGTH_SHORT).show();
                                if (check == 1)
                                    isTimeCorrect = false;
                                else
                                    isTimeCorrectRoundTrip = false;
                            }
                        } else {
                            Toast.makeText(getContext(), "Time is not correct", Toast.LENGTH_SHORT).show();
                            if (check == 1)
                                isTimeCorrect = false;
                            else
                                isTimeCorrectRoundTrip = false;
                        }
                    }
                }else{
                   // incomingCal.set(Calendar.HOUR_OF_DAY,selectedHours);
                  //  incomingCal.set(Calendar.MINUTE,selectedMinute);
                    //incomingCal.set(Calendar.SECOND,0);
                   // SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    //textViewTime1.setText(format.format(incomingCal.getTime()));
                   // writeSp();
                }
            }
        }, 12, 0, false);
        timePickerDialog.show();
    }
    public void writeSp(){
        SharedPreferences.Editor editor=myPref.edit();
        editor.putLong("CalendarNormal",calenderNormal.getTimeInMillis());
        editor.putLong("CalendarRound",calendarRound.getTimeInMillis());
        editor.commit();

    }


    public void calenderDate(TextView textViewDate1, int check, Calendar incomingCal) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                int nowYear;
                int nowMonth;
                int nowDay;
                month = month + 1;
                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                Log.i(TAG,date);
                if(check == 1) {
                    String[] dateTotal = date.split("-");
                    nowYear = Integer.valueOf(dateTotal[2]);
                    nowMonth = Integer.valueOf(dateTotal[1]);
                    nowDay = Integer.valueOf(dateTotal[0]);
                }
                else{
                    String oneTripDate = textViewDate.getText().toString();
                    String[] oneTripDateSplits = oneTripDate.split("-");
                    nowYear = Integer.valueOf(oneTripDateSplits[2]);
                    nowMonth = Integer.valueOf(oneTripDateSplits[1]);
                    nowDay = Integer.valueOf(oneTripDateSplits[0]);
                }
                if(year == nowYear && month == nowMonth && day== nowDay){
                    if(check==1)
                        isDateToday=true;
                    else
                        isDateTodayRoundTrip=true;
                }else{
                    if(check==1)
                        isDateToday=false;
                    else
                        isDateTodayRoundTrip=false;
                }
                if (year > nowYear) {
                    if (check == 1) {
                        isDateCorrect = true;
                    } else {
                        isDateCorrectRoundTrip = true;
                    }
                    //calnder
                    incomingCal.set(Calendar.DAY_OF_MONTH,day);
                    incomingCal.set(Calendar.MONTH,month-1);
                    incomingCal.set(Calendar.YEAR,year);
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    textViewDate1.setText(format.format(incomingCal.getTime()));
                }else if(year == nowYear){
                    if (month > nowMonth) {
                        if (check == 1) {
                            isDateCorrect = true;
                        } else {
                            isDateCorrectRoundTrip = true;
                        }
                        //calnder
                        incomingCal.set(Calendar.DAY_OF_MONTH, day);
                        incomingCal.set(Calendar.MONTH, month - 1);
                        incomingCal.set(Calendar.YEAR, year);
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        textViewDate1.setText(format.format(incomingCal.getTime()));
                    } else if(month == nowMonth){
                        if (day > nowDay) {
                            if (check == 1) {
                                isDateCorrect = true;
                            } else {
                                isDateCorrectRoundTrip = true;
                            }
                            //calnder
                            //incomingCal.set(Calendar.DAY_OF_MONTH,day);
                            //incomingCal.set(Calendar.MONTH,month-1);
                           // incomingCal.set(Calendar.YEAR,year);
                           // SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                           // textViewDate1.setText(format.format(incomingCal.getTime()));
                        } else {
                            Toast.makeText(getContext(), "Date is wrong", Toast.LENGTH_SHORT).show();
                            if (check == 1) {
                                isDateCorrect = false;
                            } else {
                                isDateCorrectRoundTrip = false;
                            }
                        }
                    } else {
                        Toast.makeText(getContext(), "Date is wrong", Toast.LENGTH_SHORT).show();
                        if (check == 1) {
                            isDateCorrect = false;
                        } else {
                            isDateCorrectRoundTrip = false;
                        }
                    }
                } else {
                    Toast.makeText(getContext(), "Date is wrong", Toast.LENGTH_SHORT).show();
                    if (check == 1) {
                        isDateCorrect = false;
                    } else {
                        isDateCorrectRoundTrip = false;
                    }
                }
            }
        }, year, month, day);
        datePickerDialog.show();
    }

            }




