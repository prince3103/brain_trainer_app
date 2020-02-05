package com.example.braintrainerupdated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button go_button;
    ConstraintLayout game_layout;
    TextView sum_textView;
    TextView timer_textView;
    TextView answer_textView;
    TextView score_textView;
    Button answer_button1;
    Button answer_button2;
    Button answer_button3;
    Button answer_button4;
    Button play_again_button;
    ArrayList<Integer> answers= new ArrayList<Integer>();
    CountDownTimer countDownTimer;
    int correct_ans_index;
    int score = 0;
    int questions = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go_button = findViewById(R.id.go_button);
        game_layout = findViewById(R.id.game_layout);
        sum_textView = findViewById(R.id.sum_textView);
        answer_button1 = findViewById(R.id.answer_button1);
        answer_button2 = findViewById(R.id.answer_button2);
        answer_button3 = findViewById(R.id.answer_button3);
        answer_button4 = findViewById(R.id.answer_button4);
        timer_textView = findViewById(R.id.timer_textView);
        play_again_button = findViewById(R.id.play_again_button);
        answer_textView = findViewById(R.id.answer_textView);
        score_textView = findViewById(R.id.score_textView);

        go_button.setVisibility(View.VISIBLE);
        game_layout.setVisibility(View.INVISIBLE);

    }

    public void startGame(View view){
        go_button.setVisibility(View.INVISIBLE);
        game_layout.setVisibility(View.VISIBLE);
        gameQuestion();

        counDownStart();
    }

    public void gameQuestion(){
        Random random = new Random();
        int no1 = random.nextInt(21);
        int no2 = random.nextInt(21);
        correct_ans_index = random.nextInt(4);
        sum_textView.setText(Integer.toString(no1)+"+"+Integer.toString(no2));

        for (int i =0; i<4; i++){
            if(i==correct_ans_index){
                answers.add(no1+no2);
            } else {
                int wrong_ans = random.nextInt(41);
                while (wrong_ans==(no1+no2)){
                    wrong_ans = random.nextInt(41);
                }
                answers.add(wrong_ans);
            }
        }
        answer_button1.setText(Integer.toString(answers.get(0)));
        answer_button2.setText(Integer.toString(answers.get(1)));
        answer_button3.setText(Integer.toString(answers.get(2)));
        answer_button4.setText(Integer.toString(answers.get(3)));


    }

    public void answerButtonPressed(View view){
        int _tag = Integer.parseInt((String) view.getTag());
        if(_tag-1==correct_ans_index){
            answer_textView.setText("Correct");
            score++;
        }
        else {
            answer_textView.setText("Incorrect");
        }
        questions++;
        score_textView.setText(Integer.toString(score)+"/"+Integer.toString(questions));

        answers.clear();
        gameQuestion();
    }

    public void playAgain(View view){
        score=0;
        answers.clear();
        questions =0;
        score_textView.setText(Integer.toString(score)+"/"+Integer.toString(questions));
        answer_textView.setText("");
        answer_textView.setVisibility(View.VISIBLE);

        play_again_button.setVisibility(View.INVISIBLE);
        answer_button1.setClickable(true);
        answer_button2.setClickable(true);
        answer_button3.setClickable(true);
        answer_button4.setClickable(true);
        answer_textView.setVisibility(View.VISIBLE);
        timer_textView.setText(Integer.toString(30)+"s");
        gameQuestion();
        counDownStart();

    }

    public void counDownStart(){
        countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long l) {
                timer_textView.setText(Integer.toString((int)l/1000)+"s");
            }

            @Override
            public void onFinish() {

                play_again_button.setVisibility(View.VISIBLE);
                answer_button1.setClickable(false);
                answer_button2.setClickable(false);
                answer_button3.setClickable(false);
                answer_button4.setClickable(false);
                answer_textView.setVisibility(View.INVISIBLE);

                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                mediaPlayer.start();

            }
        }.start();
    }

}
