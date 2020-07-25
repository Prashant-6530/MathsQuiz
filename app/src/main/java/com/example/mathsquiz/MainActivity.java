package com.example.mathsquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mTimerTextview, mStatusTextview, mQuestionTextview, mCorrectTextview, resultTextview;
    private Button mOption, mOption1, mOption2, mOption3, startQuizButton;
    private CountDownTimer countDownTimer;
    ConstraintLayout constraintParent, constraintChild;
    Animation scaleUp, scaleDown;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    Random random = new Random();
    int score = 0, totalAttempt = 0;
    float percentage;
    int locationOfCorrectAnswer;

    public void newQuestion() {
        locationOfCorrectAnswer = random.nextInt(4);
        int firstNumber = random.nextInt(51);
        int secondNumber = random.nextInt(51);

        int correctAnswer = firstNumber + secondNumber;

        mQuestionTextview.setText(Integer.toString(firstNumber) +" + " +Integer.toString(secondNumber));

        answers.clear();

        for(int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(correctAnswer);
            } else {
                int wrongAnswer = random.nextInt(101);
                while (wrongAnswer == correctAnswer) {
                    wrongAnswer = random.nextInt(101);
                }
                answers.add(wrongAnswer);
            }
        }
        mOption.setText(Integer.toString(answers.get(0)));
        mOption1.setText(Integer.toString(answers.get(1)));
        mOption2.setText(Integer.toString(answers.get(2)));
        mOption3.setText(Integer.toString(answers.get(3)));
    }

    public void chooseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            mCorrectTextview.setText("Correct Answer");
            score++;
        } else {
            mCorrectTextview.setText("Wrong Answer");
        }
        totalAttempt++;
        mStatusTextview.setText(Integer.toString(score) +"/" +Integer.toString(totalAttempt));
        percentage = (float)(score*100)/totalAttempt;
        newQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerTextview = findViewById(R.id.timerTextView);
        mStatusTextview = findViewById(R.id.statusTextView);
        mQuestionTextview = findViewById(R.id.questionTextView);
        mCorrectTextview = findViewById(R.id.correctTextview);
        resultTextview = findViewById(R.id.resultTextView);

        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        mOption = findViewById(R.id.button);
        mOption1 = findViewById(R.id.button1);
        mOption2 = findViewById(R.id.button2);
        mOption3 = findViewById(R.id.button3);
        startQuizButton = findViewById(R.id.startQuizButton);

        constraintParent = findViewById(R.id.constraintParent);
        constraintChild = findViewById(R.id.constraintChild);

        mOption.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mOption.startAnimation(scaleUp);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    mOption.startAnimation(scaleDown);
                }
                return false;
            }
        });
        mOption1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mOption1.startAnimation(scaleUp);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    mOption1.startAnimation(scaleDown);
                }
                return false;
            }
        });
        mOption2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mOption2.startAnimation(scaleUp);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    mOption2.startAnimation(scaleDown);
                }
                return false;
            }
        });
        mOption3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    mOption3.startAnimation(scaleUp);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    mOption3.startAnimation(scaleDown);
                }
                return false;
            }
        });
        startQuizButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    startQuizButton.startAnimation(scaleUp);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    startQuizButton.startAnimation(scaleDown);
                }
                return false;
            }
        });
        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintChild.setVisibility(View.VISIBLE);
                startQuizButton.setVisibility(View.GONE);
                score = 0;
                totalAttempt = 0;
                mStatusTextview.setText("0/0");
                newQuestion();
                countDownTimer = new CountDownTimer(30100, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mTimerTextview.setText((int)millisUntilFinished/1000 + "s");
                    }

                    @Override
                    public void onFinish() {
                        constraintChild.setVisibility(View.GONE);
                        startQuizButton.setVisibility(View.VISIBLE);
                        resultTextview.setVisibility(View.VISIBLE);
                        resultTextview.setText("Time Up ! Your accuracy is " +percentage);
                        startQuizButton.setText("Play Again");
                    }
                }.start();
            }
        });
    }
}