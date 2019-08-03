package com.example.xyz.basictodo.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xyz.basictodo.R;
import com.example.xyz.basictodo.data.entity.Note;
import com.example.xyz.basictodo.utility.Utils;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList = new ArrayList<>();
    private Context context;

    public NoteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvDescription.setText(note.getDescription());
        holder.tvDate.setText(Utils.getFormattedTime(note.getDeadline(), Utils.FORMAT_2));

        if (System.currentTimeMillis() > note.getDeadline())
            holder.tvDate.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
        else
            holder.tvDate.setTextColor(ContextCompat.getColor(context, R.color.black));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
        notifyDataSetChanged();
    }

    public Note getNoteAtPos(int position){
        return noteList.get(position);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvDescription, tvDate;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}