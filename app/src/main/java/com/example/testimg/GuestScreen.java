package com.example.testimg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GuestScreen#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GuestScreen extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int buttonEffect = R.raw.choose_sound;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GuestScreen() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GuestScreen.
     */
    // TODO: Rename and change types and number of parameters
    public static GuestScreen newInstance(String param1, String param2) {
        GuestScreen fragment = new GuestScreen();
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
        View view = inflater.inflate(R.layout.fragment_guest_screen, container, false);
        Button button = view.findViewById(R.id.buttonEnter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mPlayer = MediaPlayer.create(getActivity(), buttonEffect);
                mPlayer.start();

                Intent intent = new Intent(getActivity(), Information.class);
                startActivity(intent);
            }
        });
        return view;
    }
}