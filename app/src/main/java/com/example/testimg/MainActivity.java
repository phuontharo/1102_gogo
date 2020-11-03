package com.example.testimg;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Node[][] board;
    TableLayout layout;
    int id = 0;
    Controller controller;
    int black = Color.BLACK;
    int white = Color.WHITE;
    int yellow = Color.YELLOW;
    int defaultColor = black;
    int hpLost = 0;
    ImageView player1_avatar, player2_avatar;
    ProgressBar progressBar1, progressBar2;
    TextView textViewName1, textViewName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setting();
    }

    void setting() {
        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        player1_avatar = findViewById(R.id.avatar_player1);
        player2_avatar = findViewById(R.id.avatar_player2);
        textViewName1 = findViewById(R.id.textViewName1);
        textViewName2 = findViewById(R.id.textViewName2);
        board = new Node[10][10];
        layout = findViewById(R.id.table);
        controller = new Controller(board);
        Bundle bundle = getIntent().getExtras();
        Player player1 = (Player) bundle.get("player1");
        Player player2 = (Player) bundle.get("player2");
        textViewName1.setText(player1.getName());
        textViewName2.setText(player2.getName());
        player1_avatar.setImageResource(player1.getImgId());
        player2_avatar.setImageResource(player2.getImgId());
        progressBar1.setProgress(100);
        progressBar2.setProgress(100);
        Init();
    }

    void Init() {
        for (int i = 0; i < board.length; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < board[0].length; j++) {
                final int x = i;
                final int y = j;
                final Button button = new Button(this);
                button.setId(id);
                button.setText("+");
                TableRow.LayoutParams layout = new TableRow.LayoutParams(110, 110);
                button.setLayoutParams(layout);
                button.setBackgroundColor(yellow);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        button.setBackgroundColor(defaultColor);
                        hpLost = controller.execute(x, y);
                        if (hpLost > 0) {
                            subHP();
                        }
                        defaultColor = defaultColor == black ? white : black;
                    }
                });
                Node node = new Node(id, i, j, button, false);
                board[i][j] = node;
                row.addView(button);
            }
            layout.addView(row);
        }
    }


    void subHP() {
        if (defaultColor == black) {
            changeSizeHP(progressBar1);
        } else if (defaultColor == white) {
            changeSizeHP(progressBar2);
        }
    }

    public void newGameOnClick(View view) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j].setColorButton(yellow);
            }
        }
        progressBar1.setProgress(100);
        progressBar2.setProgress(100);
    }

    public void quitOnClick(View view) {
        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

    void changeSizeHP(ProgressBar pb) {
        pb.setProgress(pb.getProgress() - hpLost * 10);
    }
}