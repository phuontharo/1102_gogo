package com.example.testimg;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaittingOpponent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaittingOpponent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int music = R.raw.choose_sound;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WaittingOpponent() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaittingOpponent.
     */
    // TODO: Rename and change types and number of parameters
    public static WaittingOpponent newInstance(String param1, String param2) {
        WaittingOpponent fragment = new WaittingOpponent();
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
        View view = inflater.inflate(R.layout.fragment_waitting_opponent, container, false);
        Button button = (Button) view.findViewById(R.id.buttonCancel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mPlayer = MediaPlayer.create(getActivity(), music);
                mPlayer.start();

                HostSetting hostSetting = new HostSetting();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction()
                        .replace(R.id.host, hostSetting, hostSetting.getTag())
                        .commit();
            }
        });
        return view;
    }
}