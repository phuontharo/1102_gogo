package com.example.testimg;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.testimg.Start.musicBackgroundService;

public class Setting extends AppCompatActivity {
    Spinner spinner;
    int buttonEffect = R.raw.choose_sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);
        spinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.board_size_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (musicBackgroundService != null) {
            musicBackgroundService.resumeMusic();
        }
    }

    public void saveOnClick(View view) {
        MediaPlayer mPlayer = MediaPlayer.create(this, buttonEffect);
        mPlayer.start();

        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

    public void backOnClick(View view) {
        MediaPlayer mPlayer = MediaPlayer.create(this, buttonEffect);
        mPlayer.start();

        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }
}