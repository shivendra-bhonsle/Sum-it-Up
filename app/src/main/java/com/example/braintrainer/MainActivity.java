package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    int locationOfCorrectAnswer;
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    int score=0;
    int numberOfQuestions;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);

        gameLayout.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.button3));//here the passed view does not matter as we don't use it inside it just used for avoiding error


    }

    public void playAgain(View view){
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("10s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        gameLayout.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        new CountDownTimer(10100,1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText(("Done"));
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString()))
        {
           resultTextView.setText("Correct");
           score++;
        }else{
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void newQuestion(){
        Random rand=new Random();
        int a =rand.nextInt(21);
        int b =rand.nextInt(21);



        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);//random number can be placed randomly anywhere in 4 places
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer) {
                answers.add(a + b);
            }
            else{
                int wrongAnswer=rand.nextInt(41);
                while (wrongAnswer==(a+b)){
                    wrongAnswer=rand.nextInt(41);
                }
                answers.add(wrongAnswer);

            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton=findViewById(R.id.goButton);
        sumTextView=findViewById(R.id.sumTextView);
        resultTextView=findViewById(R.id.resultTextView);
        scoreTextView=findViewById(R.id.scoreTextView);
        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        playAgainButton=findViewById(R.id.playAgainButton);
        timerTextView=findViewById(R.id.timerTextView);
        gameLayout=findViewById(R.id.gameLayout);
        goButton=findViewById(R.id.goButton);
       // playAgain(findViewById(R.id.button3));
        goButton.setVisibility((View.VISIBLE));
        gameLayout.setVisibility(View.INVISIBLE);

    }


}