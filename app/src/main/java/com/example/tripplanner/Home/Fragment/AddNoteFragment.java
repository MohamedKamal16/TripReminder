package com.example.tripplanner.Home.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tripplanner.Adapter.NoteAdapter;
import com.example.tripplanner.R;

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

                if(bundle!=null) {
                    date = bundle.getString("date");
                    time = bundle.getString("time");
                    date2 = bundle.getString("date2");
                    time2 = bundle.getString("time2");

                }
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_add_note, container, false);
        initComponent();
        //button add note
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
       /* if(AddTripActivity.key==3) {
            btnSaveNotes.setText("Edit");
            new FragmentAddNotes.LoadRoomData().execute();
            Log.i(TAG, "onCreateView: thread");
        }else {*/
        selectedNotes=new ArrayList<>();
        selectedNotes.add("");
        adapter= new NoteAdapter(selectedNotes,getContext());
        recyclerView.setAdapter(adapter);


        //button add note
        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TAG, "onClick:add button "+selectedNotes.toString());
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


        return  view;
    }


    public void initComponent(){

        btnAddNote=view.findViewById(R.id.btn_note);
        btnDeleteNote=view.findViewById(R.id.btn_deleteNote);
        recyclerView=view.findViewById(R.id.recyclerView);

    }
    String date;
    String time;
    String date2;
    String time2;
    Bundle bundle=null;
    ImageButton btnAddNote;
    ImageButton btnDeleteNote;
    View view;
    RecyclerView recyclerView;
    NoteAdapter adapter;
    ArrayList<String> selectedNotes;

}
