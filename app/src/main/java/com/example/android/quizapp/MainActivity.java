package com.example.android.quizapp;

import java.text.NumberFormat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //This value reflects the number of questions in the quiz.
    // TODO: Update this when adding questions
    int numberOfQuestions = 4;

    boolean questionOneScore = false;
    boolean questionTwoScore = false;
    boolean questionThreeScore = false;
    boolean questionFourScore = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * When the "Submit" button is clicked this method prints the user's score to the screen
     * as a percentage (i.e. 80%)
     */
    public void onSubmitAnswersButtonClicked(View view) {

        //Takes the user score and stores it as a percentage
        NumberFormat percentFormatter = NumberFormat.getPercentInstance();
        String userScore = percentFormatter.format(calculateScore(view));


        Log.v("showScore", "questionOne: " + questionOneScore);
        Log.v("showScore", "questionTwo: " + questionTwoScore);
        Log.v("showScore", "Score: " + userScore);

        //Shows the user their score
        Toast toast = Toast.makeText(this, "Great job, you scored " +
                userScore, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * This method checks each question and calculates the score
     * @return the score divided by number of questions in quiz
     */
    private double calculateScore(View view) {
        double score = 0;

        checkQuestionOne(view);
        if (questionOneScore) {
            score = score + 1;
        }
        checkQuestionTwo(view);
        if (questionTwoScore) {
            score = score + 1;
        }
        checkQuestionThree(view);
        if (questionThreeScore) {
            score = score + 1;
        }
        if (questionFourScore) {
            score = score + 1;
        }

        //Divide number of correct answers by total number of questions to derive the user score
        score = score / numberOfQuestions;

        return score;
    }

    /**
     * This method checks the first question.
     */

    public void checkQuestionOne(View view) {
        CheckBox questionOneCheckBoxOne = (CheckBox) findViewById(R.id.questionOneCheckBoxOne);
        CheckBox questionOneCheckBoxTwo = (CheckBox) findViewById(R.id.questionOneCheckBoxTwo);
        CheckBox questionOneCheckBoxThree = (CheckBox) findViewById(R.id.questionOneCheckBoxThree);

        //If option One and option Three are selected the answer is correct
        if (questionOneCheckBoxOne.isChecked() && questionOneCheckBoxThree.isChecked()) {
            questionOneScore = true;
        } else {
            questionOneScore = false;
        }

        //If option Two is selected the answer is wrong
        if (questionOneCheckBoxTwo.isChecked()) {
            questionOneScore = false;
        }
    }

    /**
     * This method checks the second question.
     */
    public void checkQuestionTwo(View view) {
        CheckBox questionTwoCheckBoxOne = (CheckBox) findViewById(R.id.questionTwoCheckBoxOne);
        CheckBox questionTwoCheckBoxTwo = (CheckBox) findViewById(R.id.questionTwoCheckBoxTwo);
        CheckBox questionTwoCheckBoxThree = (CheckBox) findViewById(R.id.questionTwoCheckBoxThree);
        CheckBox questionTwoCheckBoxFour = (CheckBox) findViewById(R.id.questionTwoCheckBoxFour);

        //If options 3 and 4 are selected the answer is correct
        if (questionTwoCheckBoxThree.isChecked() && questionTwoCheckBoxFour.isChecked()) {
            questionTwoScore = true;
        } else {
            questionTwoScore = false;
        }

        //If either option 1 or 2 are selected the answer is wrong
        if (questionTwoCheckBoxOne.isChecked() || questionTwoCheckBoxTwo.isChecked()) {
            questionTwoScore = false;
        }
    }

    public void checkQuestionThree(View view) {
        RadioButton questionThreeTrue = (RadioButton) findViewById(R.id.questionThreeTrue);
        RadioButton questionThreeFalse = (RadioButton) findViewById(R.id.questionThreeFalse);

        questionThreeScore = questionThreeFalse.isChecked();

    }

    public void checkQuestionFour(View view) {
//        RadioButton optionOne = (RadioButton) R.id.questionFourOptionOne;
//        RadioButton optionTwo = (RadioButton) R.id.questionFourOptionOne;
//        RadioButton optionThree = (RadioButton) R.id.questionFourOptionOne;
//        RadioButton optionFour = (RadioButton) R.id.questionFourOptionOne;

        //Is an answer selected?
        boolean selected = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.questionFourOptionOne:
               //do nothing
                break;
            case R.id.questionFourOptionTwo:
                if (selected) {
                    questionFourScore = true;
                }
                break;
            case R.id.questionFourOptionThree:
                //do nothing
                break;
            case R.id.questionFourOptionFour:
                //do nothing
                break;
        }
    }
}
