package com.example.testimg;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.testimg.Start.musicBackgroundService;

//import static com.example.testimg.Start.musicBackgroundService;

public class MainActivity extends AppCompatActivity {
    ImageView curentChess;
    Node[][] board;
    TableLayout layout;
    int id = 0;
    Controller controller;
    int black = Color.BLACK;
    int white = Color.WHITE;
    int yellow = Color.YELLOW;
    int defaultColor = Values.imgBlack;
    int hpLost = 0;
    ImageView player1_avatar, player2_avatar;
    ProgressBar progressBar1, progressBar2;
    TextView textViewName1, textViewName2;
    TextView textTime1, textTime2;
    int p = 5, s = 5;
    int time = p * 60 + s;

    int buttonEffect = R.raw.choose_sound;
    int playSound = R.raw.play_sound;

    Handler handler;
    AtomicBoolean isBlackTimeRunning = new AtomicBoolean(false),
            isWhiteTimeRunning = new AtomicBoolean(false);

    String timeBlack, timeWhite;
    Thread thb;
    Thread thw;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = time * 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setting();
//        handler = new Handler() {
//            public void handleMessage(Message msg) {
//                Bundle bundle = msg.getData();
//                timeBlack = bundle.getString("timeBlack");
//                textTime1.setText(timeBlack);
//                timeWhite = bundle.getString("timeWhite");
//                textTime2.setText(timeWhite);
//                super.handleMessage(msg);
//            }
//        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (musicBackgroundService != null) {
            musicBackgroundService.resumeMusic();
        }
    }


    void subHP() {
        if (defaultColor == Values.imgBlack) {
            changeSizeHP(progressBar2);
        } else if (defaultColor == Values.imgWhite) {
            changeSizeHP(progressBar1);
        }
    }

    public void newGameOnClick(View view) {
        MediaPlayer mPlayer = MediaPlayer.create(this, buttonEffect);
        mPlayer.start();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j].getButton().setImageResource(Values.imgEmpty);
                board[i][j].setValue(Values.enptyValue);
            }
        }
        progressBar1.setProgress(100);
        progressBar2.setProgress(100);
    }

    public void quitOnClick(View view) {
        MediaPlayer mPlayer = MediaPlayer.create(this, buttonEffect);
        mPlayer.start();

        Intent intent = new Intent(this, Start.class);
        startActivity(intent);
    }

    void changeSizeHP(ProgressBar pb) {
        pb.setProgress(pb.getProgress() - hpLost * 10);
    }

    void setting() {
        curentChess = findViewById(R.id.curentChess);
        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        player1_avatar = findViewById(R.id.avatar_player1);
        player2_avatar = findViewById(R.id.avatar_player2);
        textViewName1 = findViewById(R.id.textViewName1);
        textViewName2 = findViewById(R.id.textViewName2);
        textTime1 = findViewById(R.id.textTime1);
        textTime2 = findViewById(R.id.textTime2);
        board = new Node[Values.boardSize][Values.boardSize];
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
        curentChess.setImageResource(defaultColor);
        Init();
        startTimer();

        // updateCountDownText();
        //  doStartTimeBlack();
    }

    void Init() {
        for (int i = 0; i < board.length; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < board[0].length; j++) {
                final int x = i;
                final int y = j;
                final ImageButton button = new ImageButton(this);
                button.setId(id);
                button.setImageResource(Values.imgEmpty);
                TableRow.LayoutParams layout = new TableRow.LayoutParams(110, 110);
                button.setLayoutParams(layout);
                final Node node = new Node(id, i, j, button, Values.enptyValue,false);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(node.getValue() == Values.enptyValue){
                            MediaPlayer mPlayer = MediaPlayer.create(getApplication(), playSound);
                            mPlayer.start();
                            button.setImageResource(defaultColor);
                            node.setValue(defaultColor);
                            hpLost = controller.execute(x, y);
                            if (hpLost != 0) {
                                subHP();
                            }
                            if (defaultColor == Values.imgBlack) {

                                pauseTimer();
                                //                            if (!isWhiteTimeRunning.get()) {
//                                isBlackTimeRunning.set(false);
//                                doStartTimeWhite();
//                            }
                            } else {

                                startTimer();
//                            if (!isBlackTimeRunning.get()) {
//                                isWhiteTimeRunning.set(false);
//                                doStartTimeBlack();
//                            }
                            }
                            defaultColor = defaultColor == Values.imgBlack ? Values.imgWhite : Values.imgBlack;
                            curentChess.setImageResource(defaultColor);
                        }
                    }
                });

                board[i][j] = node;
                row.addView(button);
            }
            layout.addView(row);
        }
    }

    public void doStartTimeBlack() {
        //isBlackTimeRunning.set(false);
        thb = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = time; i >= 0 && isBlackTimeRunning.get(); i--) {
                    //cho tiến trình tạm ngừng 100 mili second
                    SystemClock.sleep(1000);
                    //lấy message từ Main thread
                    Message msg = handler.obtainMessage();
                    //gán giá trị vào cho arg1 để gửi về Main thread
                    String data = ((int) (i / 60)) + ":" + i % 60;

                    Bundle bundle = new Bundle();
                    bundle.putString("timeBlack", data);
                    msg.setData(bundle);
                    //gửi lại Message này về cho Main Thread
                    handler.sendMessage(msg);
                }
            }
        });
        isBlackTimeRunning.set(true);
        thb.start();
    }

    public void doStartTimeWhite() {
        //   isWhiteTimeRunning.set(false);
        thw = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = time; i >= 0 && isWhiteTimeRunning.get(); i--) {
                    //cho tiến trình tạm ngừng 1000 mili second
                    SystemClock.sleep(1000);
                    //lấy message từ Main thread
                    Message msg = handler.obtainMessage();
                    String data = ((int) (i / 60)) + ":" + i % 60;

                    Bundle bundle = new Bundle();
                    bundle.putString("timeBlack", data);
                    msg.setData(bundle);
                    //gửi lại Message này về cho Main Thread
                    handler.sendMessage(msg);
                }
            }
        });
        isWhiteTimeRunning.set(true);
        thw.start();
    }


    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();
        mTimerRunning = true;
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
    }

    private void resetTimer() {
        mTimeLeftInMillis = time;
        updateCountDownText();
    }

    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        textTime1.setText(timeLeftFormatted);
    }
}