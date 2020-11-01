package com.example.testimg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Information extends AppCompatActivity {
    RadioGroup group;
    ImageView avatar;
    EditText inputName;
    private static int possIMG = 0;
    ArrayList<Integer> listImg;
    RadioButton player1, player2;
    Player[] playerInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_information);
        setUp();
    }


    void preRegisterInfor() {
        playerInfor = new Player[2];
        Player p1 = new Player("Joe", listImg.get(0), 0);
        Player p2 = new Player("Akaly", listImg.get(1), 1);
        playerInfor[0] = p1;
        playerInfor[1] = p2;
    }

    private void settingScreenInformation(Player player) {
        inputName.setText(player.getName());
        avatar.setImageResource(player.getImgId());
        possIMG = player.getPositionImgInList();
        System.out.println("Possss : " + possIMG);
    }

    void setUp() {
        avatar = findViewById(R.id.avatar);
        group = findViewById(R.id.groupRadio);
        player1 = findViewById(R.id.radio1);
        player2 = findViewById(R.id.radio2);
        inputName = findViewById(R.id.name);
        listImg = getListAvatar();
        preRegisterInfor();
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getInformation();
            }
        });
        settingScreenInformation(playerInfor[0]);
    }

    private ArrayList<Integer> getListAvatar() {
        ArrayList<Integer> listAvt = new ArrayList<>();
        String[] imgAvt = getResources().getStringArray(R.array.list_name);
        for (int i = 0; i < imgAvt.length; i++) {
            int idImg = getResources().getIdentifier(imgAvt[i], "drawable", getPackageName());
            listAvt.add(idImg);
        }
        return listAvt;
    }

    void setChangePlayer() {

    }

    private void getInformation() {
        Player p = getInforScreen();
        if (player1.isChecked()) {
            playerInfor[1] = p;
            settingScreenInformation(playerInfor[0]);
        } else {
            playerInfor[0] = p;
            settingScreenInformation(playerInfor[1]);
        }
    }

    // create new player from information in screen
    Player getInforScreen() {
        String name = inputName.getText().toString().trim();
        int imgId = listImg.get(possIMG);
        return new Player(name, imgId, possIMG);
    }

    // go to pre picture
    public void onClickPre(View view) {
        possIMG = possIMG == 0 ? possIMG : possIMG - 1;
        avatar.setImageResource(listImg.get(possIMG));
        System.out.println("Poss Pre : " + possIMG);
    }

    // go to next picture
    public void onClickNext(View view) {
        possIMG = possIMG == listImg.size() - 1 ? possIMG : possIMG + 1;
        avatar.setImageResource(listImg.get(possIMG));
        System.out.println("Poss Next : " + possIMG);
    }


    // transmit information of player
    public void onClickSave(View view) {
        Player p = getInforScreen();
        if (player1.isChecked()) {
            playerInfor[0] = p;
        } else {
            playerInfor[1] = p;
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("player1", playerInfor[0]);
        intent.putExtra("player2", playerInfor[1]);
        startActivity(intent);
    }
}