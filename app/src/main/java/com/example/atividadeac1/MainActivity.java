package com.example.atividadeac1;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ADD = 1;
    private static final int REQUEST_EDIT = 2;

    private ArrayList<Habit> habits = new ArrayList<>();
    private HabitAdapter adapter;
    private int habitIdCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        FloatingActionButton fab = findViewById(R.id.fab_add);

        adapter = new HabitAdapter(habits, new HabitAdapter.HabitClickListener() {
            @Override
            public void onClick(Habit habit) {
                Intent intent = new Intent(MainActivity.this, HabitFormActivity.class);
                intent.putExtra("habit", habit);
                startActivityForResult(intent, REQUEST_EDIT);
            }

            @Override
            public void onLongClick(Habit habit) {
                habits.remove(habit);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onToggle(Habit habit) {
                // Atualiza visualmente
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HabitFormActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("name");
            String description = data.getStringExtra("description");
            int id = data.getIntExtra("id", habitIdCounter++);

            Habit habit = new Habit(id, name, description);
            for (int i = 0; i < habits.size(); i++) {
                if (habits.get(i).getId() == id) {
                    habits.set(i, habit);
                    adapter.notifyItemChanged(i);
                    return;
                }
            }

            habits.add(habit);
            adapter.notifyDataSetChanged();
        }
    }
}