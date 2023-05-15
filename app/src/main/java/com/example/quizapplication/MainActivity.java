package com.example.quizapplication;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private RadioButton option1RadioButton;
    private RadioButton option2RadioButton;
    private RadioButton option3RadioButton;
    private RadioButton option4RadioButton;
    private Button submitBtn;
    private TextView resultTextView;

    private String currentAnswer;
    private int score = 0;
    private int questionIndex = 0;

    private String[] questions = {
            "Who is the author of the Harry Potter book series?",
            "What is the capital city of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest ocean on Earth?",
            "What is the chemical symbol for the element Gold?"
    };

    private String[][] options = {
            {"J.K. Rowling", "Stephenie Meyer", "Dan Brown", "George R.R. Martin"},
            {"London", "Rome", "Paris", "Berlin"},
            {"Jupiter", "Venus", "Mercury", "Mars"},
            {"Indian Ocean", "Atlantic Ocean", "Pacific Ocean", "Arctic Ocean"},
            {"Ag", "Au", "Fe", "Cu"}
    };

    private String[] answers = {
            "J.K. Rowling",
            "Paris",
            "Mars",
            "Pacific Ocean",
            "Au"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        option1RadioButton = findViewById(R.id.option1RadioButton);
        option2RadioButton = findViewById(R.id.option2RadioButton);
        option3RadioButton = findViewById(R.id.option3RadioButton);
        option4RadioButton = findViewById(R.id.option4RadioButton);
        submitBtn = findViewById(R.id.submitBtn);
        resultTextView = findViewById(R.id.resultTextView);

        displayQuestion();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void displayQuestion() {
        questionTextView.setText(questions[questionIndex]);
        option1RadioButton.setText(options[questionIndex][0]);
        option2RadioButton.setText(options[questionIndex][1]);
        option3RadioButton.setText(options[questionIndex][2]);
        option4RadioButton.setText(options[questionIndex][3]);

        currentAnswer = answers[questionIndex];
        questionIndex++;
        resultTextView.setText("");
        optionsRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int selectedRadioButtonId = optionsRadioGroup.getCheckedRadioButtonId();

        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            String selectedAnswer = selectedRadioButton.getText().toString();

            if (selectedAnswer.equals(currentAnswer)) {
                score++;
            }

            if (questionIndex < questions.length) {
                displayQuestion();
            } else {
                showResult();
            }
        } else {
            resultTextView.setText("Please select an answer");
        }
    }

    private void showResult() {
        String result = "Quiz completed!\nYour score: " + score + "/" + questions.length;
        resultTextView.setText(result);
        submitBtn.setEnabled(false);
    }
}

