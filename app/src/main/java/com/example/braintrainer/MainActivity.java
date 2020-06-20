package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer>  answers=new ArrayList<Integer>();
    int correctPosition;
    TextView rView;
    TextView resultTextView;
    TextView textView;
    GridLayout gridLayout;
    TextView finalTextView;

    TextView sumView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    int totalQuestion;
    int correctAnswer;
    Button playAgain;

    public void playAgain(View view){

        totalQuestion=0;
        correctAnswer=0;
        resultTextView.setText(Integer.toString(correctAnswer)+"/"+Integer.toString(totalQuestion));
        playAgain.setVisibility(View.INVISIBLE);
        sumView.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        finalTextView.setVisibility(View.INVISIBLE);


        newQuestion();

        new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            textView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                rView.setText("Game Over");
                playAgain.setVisibility(View.VISIBLE);
                sumView.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                resultTextView.setVisibility(View.INVISIBLE);
                gridLayout.setVisibility(View.INVISIBLE);
                finalTextView.setText("Your Score : "+Integer.toString(correctAnswer)+"/"+Integer.toString(totalQuestion));
                finalTextView.setVisibility(View.VISIBLE);

            }
        }.start();

    }
    public void getAnswer(View view){

        if(Integer.toString(correctPosition).equals(view.getTag().toString())){
            correctAnswer++;
            rView.setText("Correct");

        }
        else{

            rView.setText("Incorrect");
        }
        resultTextView.setText(Integer.toString(correctAnswer)+"/"+Integer.toString(totalQuestion));
        newQuestion();
    }


    public void newQuestion(){
        totalQuestion++;
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        while(a==b) {
            b=rand.nextInt(21);
        }

        sumView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        correctPosition=rand.nextInt(4);
        answers.clear();

        for (int i=0;i<4;i++){
            if (i==correctPosition){
                answers.add(a+b);
            }
            else {

                int wrong=rand.nextInt(41);
                while(wrong==a+b){
                    wrong=rand.nextInt(41);
                }
                answers.add(wrong);
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

        sumView=findViewById(R.id.textView2);
         button0=findViewById(R.id.button0);
         button1=findViewById(R.id.button1);
         button2=findViewById(R.id.button2);
         button3=findViewById(R.id.button3);
         playAgain=findViewById(R.id.playAgain);
        rView=findViewById(R.id.resultTextView);
        resultTextView=findViewById(R.id.textView3);
        textView=findViewById(R.id.textView);
        gridLayout=findViewById(R.id.gridLayout);
        finalTextView=findViewById(R.id.finalTextView);


playAgain(findViewById(R.id.gridLayout));

    }


}
