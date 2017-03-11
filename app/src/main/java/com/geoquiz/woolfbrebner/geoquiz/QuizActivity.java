package com.geoquiz.woolfbrebner.geoquiz;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageButton;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    public void updateQuestion(){
        Log.d(TAG, "Updating question text for question#"+mCurrentIndex, new Exception());
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        }else{
            messageResId=R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mQuestionTextView;
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.questions_oceans, false),
            new TrueFalse(R.string.questions_mideast, true),
            new TrueFalse(R.string.questions_africa, true),
            new TrueFalse(R.string.questions_americas, false),
            new TrueFalse(R.string.questions_asia, true),
            new TrueFalse(R.string.questions_1, false),
            new TrueFalse(R.string.questions_2, false),
            new TrueFalse(R.string.questions_3, true),
            new TrueFalse(R.string.questions_4, false),
            new TrueFalse(R.string.questions_5, false),
            new TrueFalse(R.string.questions_6, false),
            new TrueFalse(R.string.questions_7, true),
    };
    private int mCurrentIndex = 0;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate(Bundle) called");

        mQuestionTextView = (TextView)findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
            checkAnswer(true);

            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
              checkAnswer(false);
            }
        });
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;


                updateQuestion();
            }
        });
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View x) {
                if (mCurrentIndex != 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                }else {
                    mCurrentIndex = mQuestionBank.length - 1;
                    updateQuestion();
                }
            }
        });
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateQuestion();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onstart(), called");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause(), called");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume(), called");

    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop(), called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy(), called");
    }
}
