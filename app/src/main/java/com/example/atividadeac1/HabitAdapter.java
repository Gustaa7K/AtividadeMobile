package com.example.atividadeac1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.HabitViewHolder> {

    public interface HabitClickListener {
        void onClick(Habit habit);
        void onLongClick(Habit habit);
        void onToggle(Habit habit);
    }

    private List<Habit> habits;
    private HabitClickListener listener;

    public HabitAdapter(List<Habit> habits, HabitClickListener listener) {
        this.habits = habits;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HabitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habit, parent, false);
        return new HabitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HabitViewHolder holder, int position) {
        Habit habit = habits.get(position);
        holder.bind(habit);
    }

    @Override
    public int getItemCount() {
        return habits.size();
    }

    class HabitViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView descriptionText;
        CheckBox checkbox;
        ImageButton deleteButton;

        HabitViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.text_name);
            descriptionText = itemView.findViewById(R.id.text_description);
            checkbox = itemView.findViewById(R.id.checkbox_done);
            deleteButton = itemView.findViewById(R.id.button_delete);
        }

        void bind(Habit habit) {
            nameText.setText(habit.getName());
            descriptionText.setText(habit.getDescription());
            checkbox.setChecked(habit.isDoneToday());

            itemView.setOnClickListener(v -> listener.onClick(habit));
            itemView.setOnLongClickListener(v -> {
                listener.onLongClick(habit);
                return true;
            });

            checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                habit.setDoneToday(isChecked);
                listener.onToggle(habit);
            });

            deleteButton.setOnClickListener(v -> {
                listener.onLongClick(habit);
            });
        }
    }
}
