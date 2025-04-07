package com.example.atividadeac1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class HabitFormActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText descriptionEditText;
    private Button saveButton;

    private Habit habitToEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_form);

        nameEditText = findViewById(R.id.edit_text_name);
        descriptionEditText = findViewById(R.id.edit_text_description);
        saveButton = findViewById(R.id.button_save);

        Intent intent = getIntent();
        if (intent.hasExtra("habit")) {
            habitToEdit = (Habit) intent.getSerializableExtra("habit");
            nameEditText.setText(habitToEdit.getName());
            descriptionEditText.setText(habitToEdit.getDescription());
        }

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String description = descriptionEditText.getText().toString();

            Intent resultIntent = new Intent();
            resultIntent.putExtra("name", name);
            resultIntent.putExtra("description", description);

            if (habitToEdit != null) {
                resultIntent.putExtra("id", habitToEdit.getId());
            }

            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
