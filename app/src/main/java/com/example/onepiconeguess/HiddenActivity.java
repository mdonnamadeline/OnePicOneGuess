package com.example.onepiconeguess;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HiddenActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView txtHint, txtWord, txtScore;
    private EditText txtFinalWord;
    private Button btnSubmit, btnNext;
    private int currentImageIndex = 0;
    private int score = 0;

    private String[] imageNames;
    private int[] imageIds;
    private String[] hints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);

        imageView = findViewById(R.id.imageView);
        txtHint = findViewById(R.id.txtHint);
        txtWord = findViewById(R.id.txtWord);
        txtFinalWord = findViewById(R.id.txtFinalWord);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnNext = findViewById(R.id.btnNext);
        txtScore = findViewById(R.id.txtScore);

        String category = getIntent().getStringExtra("category");
        if (category.equals("Fruits")) {
            imageNames = new String[]{"apple", "banana", "coconut", "grapes", "guava", "mango", "orange", "pineapple", "strawberry", "watermelon"};
            imageIds = new int[]{R.drawable.apple, R.drawable.banana, R.drawable.coconut, R.drawable.grapes, R.drawable.guava, R.drawable.mango, R.drawable.orange, R.drawable.pineapple, R.drawable.strawberry, R.drawable.watermelon};
            hints = new String[]{"A common fruit, usually red.", "A long and yellow fruit.", "A tropical fruit with hard shell.", "Small and often purple fruit.", "A tropical fruit, green when raw.", "A tropical fruit, known for its large seed.", "A citrus fruit, usually orange.", "A tropical fruit with spiky skin.", "A small red fruit with seeds on the outside.", "A large fruit, usually eaten in the summer."};
        } else if (category.equals("Animals")) {
            imageNames = new String[]{"antelope", "bear", "dog", "elephant", "flamingo", "giraffe", "lion", "monkey", "owl", "zebra"};
            imageIds = new int[]{R.drawable.antelope, R.drawable.bear, R.drawable.dog, R.drawable.elephant, R.drawable.flamingo, R.drawable.giraffe, R.drawable.lion, R.drawable.monkey, R.drawable.owl, R.drawable.zebra};
            hints = new String[]{"A type of deer found in Africa.", "A large and powerful animal, often found in forests.", "Man's best friend.", "The largest land animal.", "A bird known for its pink color.", "A tall animal, known for its long neck.", "The king of the jungle.", "A primate, known for its ability to climb trees.", "A bird, known for its hooting sound.", "A wild horse with black and white stripes."};
        } else if (category.equals("Sports")) {
            imageNames = new String[]{"badminton", "baseball", "basketball", "billiard", "bowling", "chess", "golf", "lawntennis", "skateboarding", "soccer"};
            imageIds = new int[]{R.drawable.badminton, R.drawable.baseball, R.drawable.basketball, R.drawable.billiard, R.drawable.bowling, R.drawable.chess, R.drawable.golf, R.drawable.lawntennis, R.drawable.skateboarding, R.drawable.soccer};
            hints = new String[]{"A racquet sport played using a shuttlecock.", "A sport played with a bat and a ball.", "A sport played with a ball and a hoop.", "A game played on a table with cues and billiard balls.", "A sport in which a player rolls a bowling ball.", "A two-player strategy board game.", "A club-and-ball sport.", "A racquet sport that can be played singles or doubles.", "An action sport that involves riding and performing tricks.", "A team sport played with a spherical ball."};
        }

        updateImageAndHint();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guess = txtFinalWord.getText().toString();
                if (guess.equalsIgnoreCase(imageNames[currentImageIndex])) {
                    score++;
                    Toast.makeText(HiddenActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HiddenActivity.this, "Incorrect.", Toast.LENGTH_SHORT).show();
                }
                txtScore.setText("Score: " + score);
                btnSubmit.setEnabled(false); // disable the button after one guess
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex = (currentImageIndex + 1) % imageIds.length;
                updateImageAndHint();
                txtFinalWord.setText("");
                btnSubmit.setEnabled(true);
            }
        });
    }

    private void updateImageAndHint() {
        imageView.setImageResource(imageIds[currentImageIndex]);
        txtWord.setText(new String(new char[imageNames[currentImageIndex].length()]).replace("\0", "_ "));
        txtHint.setText(hints[currentImageIndex]);
    }
}