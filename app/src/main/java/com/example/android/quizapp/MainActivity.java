package com.example.android.quizapp;

import java.text.NumberFormat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //This value reflects the number of questions in the quiz.
    // TODO: Update this number when adding new questions
    int numberOfQuestions = 5;

    boolean questionOneScore = false;
    boolean questionTwoScore = false;
    boolean questionThreeScore = false;
    boolean questionFourScore = false;
    boolean questionFiveScore = false;

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

        //Shows the user their score. The toast changes depending on how the user scored.
        if (calculateScore(view) > 0.7) {
            Toast toast = Toast.makeText(this, "Great job, you scored " +
                    userScore, Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Toast toast = Toast.makeText(this, "Room for improvement! You scored " +
                    userScore, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * This method checks each question and calculates the score
     *
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
        //Question four radio group updates score as user selects the option.
        if (questionFourScore) {
            score = score + 1;
        }
        checkQuestionFive(view);
        if (questionFiveScore) {
            score = score + 1;
        }

        //Divide number of correct answers by total number of questions to derive the user score
        score = score / numberOfQuestions;

        return score;
    }

    /**
     * This method resets all the answer scores and restarts the activity when the "reset" button
     * is clicked
     */
    public void onResetButtonClicked(View view) {
        questionOneScore = false;
        questionTwoScore = false;
        questionThreeScore = false;
        questionFourScore = false;
        questionFiveScore = false;
        finish();
        startActivity(getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
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

    /**
     * This method checks the third question.
     */
    public void checkQuestionThree(View view) {
        RadioButton questionThreeFalse = (RadioButton) findViewById(R.id.questionThreeFalse);

        questionThreeScore = questionThreeFalse.isChecked();

    }

    /**
     * This method checks the fourth question. This method runs via onClick from activity_main.xml
     */
    public void checkQuestionFour(View view) {

        //Is an answer selected?
        boolean selected = ((RadioButton) view).isChecked();

        //The second option is correct
        switch (view.getId()) {
            case R.id.questionFourOptionOne:
                if (selected) {
                    questionFourScore = false;
                }
                break;
            case R.id.questionFourOptionTwo:
                if (selected) {
                    questionFourScore = true;
                }
                break;
            case R.id.questionFourOptionThree:
                if (selected) {
                    questionFourScore = false;
                }
                break;
            case R.id.questionFourOptionFour:
                if (selected) {
                    questionFourScore = false;
                }
                break;
        }
    }

    /**
     * This method checks the fifth question.
     */
    public void checkQuestionFive(View view) {
        EditText questionFiveAnswer = (EditText) findViewById(R.id.questionFiveEditText);
        String questionFiveAnswerString = questionFiveAnswer.getText().toString().toLowerCase();
        if (questionFiveAnswerString.equals("tricuspid")) {
            questionFiveScore = true;
        }

    }
}
