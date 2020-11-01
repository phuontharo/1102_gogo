package com.example.testimg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;

public class information extends AppCompatActivity {
    RadioGroup group;
    ImageView avatar;
    private int possIMG = 0;
    ArrayList<Integer> listImg;
    RadioButton player1, player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        getSupportActionBar().hide();
        // change player



        setUp();
    }

   void setUp(){
       avatar = findViewById(R.id.avatar);
       group = findViewById(R.id.groupRadio);
       player1 = findViewById(R.id.radio1);
       player2 = findViewById(R.id.radio2);
       group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {

           }
       });


       listImg = new ArrayList<>();
       String[] imgAvt = getResources().getStringArray(R.array.list_name);
       for (int i = 0; i < imgAvt.length; i++) {
           int idImg = getResources().getIdentifier(imgAvt[i], "drawable", getPackageName());
           listImg.add(idImg);
       }
       avatar.setImageResource(listImg.get(possIMG));
   }

    public void onClickPre(View view) {
        possIMG = possIMG==0?possIMG:possIMG-1;
        avatar.setImageResource(listImg.get(possIMG));
    }

    public void onClickNext(View view) {
       possIMG = possIMG == listImg.size()-1?possIMG:possIMG+1;
       avatar.setImageResource(listImg.get(possIMG));
    }
    

}