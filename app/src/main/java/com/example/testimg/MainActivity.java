package com.example.testimg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    Node[][] board;
    TableLayout layout;
    int id = 0;
    Controller controller;
    int black = Color.BLACK;
    int white = Color.WHITE;
    int yellow = Color.YELLOW;
    int defaultColor = black;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setting();
    }

    void setting() {
        board = new Node[10][10];
        layout = findViewById(R.id.table);
        controller = new Controller(board);
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
           //             controller.execute(x, y);
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
}