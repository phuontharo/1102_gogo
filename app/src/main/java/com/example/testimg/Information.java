package com.example.testimg;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Information extends AppCompatActivity {
    int currentPlayer = 0;
    private static int possIMG = 0, preIMG, nextIMG;
    EditText inputName;
    ImageView avatar, btnPre, btnNext;
    ArrayList<Integer> listImg;
    Player[] playerInfor;
    Intent intent;
    private Bitmap operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_information);
        setUp();
    }

    private void setUp() {
        btnPre = findViewById(R.id.imagePre);
        btnNext = findViewById(R.id.imageNext);
        avatar = findViewById(R.id.avatar);
        inputName = findViewById(R.id.name);
        listImg = getListAvatar();
        preRegisterInfor();
        setScreenInformation(playerInfor[0]);
        intent = new Intent(this, MainActivity.class);
    }

    private void preRegisterInfor() { // set infor default for player
        playerInfor = new Player[2];
        Player p1 = new Player("Joe", listImg.get(0), 0);
        Player p2 = new Player("Akaly", listImg.get(1), 1);
        playerInfor[0] = p1;
        playerInfor[1] = p2;
    }


    private void setScreenInformation(Player player) {
        inputName.setHint("Player " + (currentPlayer + 1));
        inputName.setText(player.getName());
        avatar.setImageResource(player.getImgId());
        possIMG = player.getPositionImgInList();
        setImageForButton();
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

    // create new player from information in screen
    Player getInforScreen() {
        String name = inputName.getText().toString().trim();
        int imgId = listImg.get(possIMG);
        return new Player(name, imgId, possIMG);
    }

    private void setImageForButton() {
        preIMG = possIMG == 0 ? listImg.size() - 1 : possIMG - 1;
        btnPre.setImageResource(listImg.get(preIMG));
        btnPre.setImageBitmap(effectForPicture(((BitmapDrawable) btnPre.getDrawable()).getBitmap()));
        nextIMG = possIMG == listImg.size() - 1 ? 0 : possIMG + 1;
        btnNext.setImageResource(listImg.get(nextIMG));
        btnNext.setImageBitmap(effectForPicture(((BitmapDrawable) btnNext.getDrawable()).getBitmap()));
    }

    // go to pre picture
    public void onClickPre(View view) {
        possIMG = possIMG == 0 ? listImg.size() - 1 : possIMG - 1;
        avatar.setImageResource(listImg.get(possIMG));
        setImageForButton();
    }

    // go to next picture
    public void onClickNext(View view) {
        possIMG = possIMG == listImg.size() - 1 ? 0 : possIMG + 1;
        avatar.setImageResource(listImg.get(possIMG));
        setImageForButton();
    }

    public Bitmap effectForPicture(Bitmap bmp) {
        //create bitmap
        Float darkness = 2f;
        int opacity = 50;
        operation = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        for (int i = 0; i < bmp.getWidth(); i++) {
            for (int j = 0; j < bmp.getHeight(); j++) {
                // set px in picture
                int p = bmp.getPixel(i, j);
                int r = Color.red(p);
                int g = Color.green(p);
                int b = Color.blue(p);
                int alpha = Color.alpha(p);
                // change color
                r = r / darkness < 0 ? 0 : (int) (r / darkness);
                g = g / darkness < 0 ? 0 : (int) (g / darkness);
                b = b / darkness < 0 ? 0 : (int) (b / darkness);
                alpha = alpha - opacity < 0 ? 0 : alpha - opacity;
                // set picture
                operation.setPixel(i, j, Color.argb(alpha, r, g, b));
            }
        }
        return operation;
    }

    // transmit information of player
    public void onClickOk(View view) {
        Player p = getInforScreen();
        if (currentPlayer == 0) {
            intent.putExtra("player1", playerInfor[currentPlayer] = p);
            currentPlayer++;
            setScreenInformation(playerInfor[1]);
        } else {
            intent.putExtra("player2", playerInfor[currentPlayer] = p);
            startActivity(intent);
        }
    }
}