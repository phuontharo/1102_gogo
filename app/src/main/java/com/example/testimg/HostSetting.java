package com.example.testimg;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HostSetting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HostSetting extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int buttonEffect = R.raw.choose_sound;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner spinnerTime, spinnerOvertime, spinnerSizeOfBooard;

    public HostSetting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HostSetting.
     */
    // TODO: Rename and change types and number of parameters
    public static HostSetting newInstance(String param1, String param2) {
        HostSetting fragment = new HostSetting();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_host_setting, container, false);
        Button buttonCreate = view.findViewById(R.id.buttonCreate);
        setSpinners(view);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mPlayer = MediaPlayer.create(getActivity(), buttonEffect);
                mPlayer.start();

                WaittingOpponent waitOpponent = new WaittingOpponent();
                FragmentManager manager = getFragmentManager();

                //  FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                manager.beginTransaction()
                        .replace(R.id.host, waitOpponent).addToBackStack(null)
                        .commit();
//                transaction                        .replace(R.id.layoutHost, waitOpponent);
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });
//        Button buttonPlay = view.findViewById(R.id.buttonPlay);
//        buttonPlay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Information.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    private void setSpinners(View view) {
        spinnerTime = view.findViewById(R.id.spinnerTime);
        spinnerOvertime = view.findViewById(R.id.spinnerSubTime);
        spinnerSizeOfBooard = view.findViewById(R.id.spinnerSizeOfBoard);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.board_size_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSizeOfBooard.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.sub_time, android.R.layout.simple_spinner_item);
        spinnerOvertime.setAdapter(adapter);
        adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.main_time, android.R.layout.simple_spinner_item);
        spinnerTime.setAdapter(adapter);
    }
}