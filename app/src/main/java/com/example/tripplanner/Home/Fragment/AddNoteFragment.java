package com.example.tripplanner.Home.Fragment;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tripplanner.Adapter.NoteAdapter;
import com.example.tripplanner.Home.Activity.AddActivity;
import com.example.tripplanner.Home.Activity.Home_Activity;
import com.example.tripplanner.R;
import com.example.tripplanner.TripData.Trip;


import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddNoteFragment extends Fragment {


    public AddNoteFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        getParentFragmentManager().setFragmentResultListener("datakey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                if(result!=null) {
                    date = result.getString("date");
                    time = result.getString("time");
                    date2 = result.getString("date2");
                    time2 = result.getString("time2");

                }
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_add_note, container, false);
        btnAddNote=view.findViewById(R.id.btn_note);
        btnDeleteNote=view.findViewById(R.id.btn_deleteNote);
        btnSaveNotes=view.findViewById(R.id.btn_saveNotes);
        recyclerView=view.findViewById(R.id.recyclerView);
        result = new Bundle();
        linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        if(AddActivity.key==3) {
            btnSaveNotes.setText("Edit");
            new AddNoteFragment.LoadRoomData().execute();

        }else {
            selectedNotes=new ArrayList<>();
            selectedNotes.add("");
            adapter=new NoteAdapter(selectedNotes,getContext());
            recyclerView.setAdapter(adapter);

        }
        //button add note
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedNotes.size()<=10){
                    selectedNotes.add("");
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(),"you can only add 10 notes",Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedNotes.size()>0){
                    adapter.removeItem();
                    if(selectedNotes.size()==0)
                        btnDeleteNote.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btnSaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notes=new ArrayList<>();
                if(AddActivity.key==1){
                    result = new Bundle();
                    if(!selectedNotes.isEmpty()) {
                        for (int i = 0; i < selectedNotes.size(); i++) {
                            if(!selectedNotes.get(i).isEmpty()&&selectedNotes.get(i)!=null) {
                                notes.add(selectedNotes.get(i));
                                //           result.putStringArrayList("bundleKey", selectedNotes);

                            }
                        }
                        //    Log.i(TAG, "onClick: "+notes);
                        result.putStringArrayList("bundleKey", notes);
                    }


                }else if(AddActivity.key==3){

                    Home_Activity.database.tripDAO().EditNotes(AddActivity.ID,selectedNotes.toString());

                }
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
                fm.popBackStack ("name", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });



        return  view;
    }



    private class LoadRoomData extends AsyncTask<Void, Void, Trip> {


        @Override
        protected Trip doInBackground(Void... voids) {

            return Home_Activity.database.tripDAO().selectById(AddActivity.ID);
        }
        @Override
        protected void onPostExecute(Trip trip) {
            super.onPostExecute(trip);
            selectedTrip = trip;
            if (selectedTrip.getNotes()!=null) {
                selectedNotes = selectedTrip.getNotes();
                adapter = new NoteAdapter(selectedTrip.getNotes(), getContext());
                recyclerView.setAdapter(adapter);

            }else {
                selectedNotes=new ArrayList<>();
                selectedNotes.add("");
                adapter=new NoteAdapter(selectedNotes,getContext());
                recyclerView.setAdapter(adapter);
            }
        }
    }
    RecyclerView recyclerView;
    NoteAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    ImageButton btnAddNote;
    ImageButton   btnDeleteNote;
    Button btnSaveNotes;
    ArrayList<String> notes;
    String date;
    String time;
    String date2;
    String time2;
    Bundle result;

    Trip selectedTrip;
    ArrayList<String> selectedNotes;
    public static final String TAG="Notes";

}