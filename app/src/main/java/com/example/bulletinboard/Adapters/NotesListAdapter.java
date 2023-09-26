package com.example.bulletinboard.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bulletinboard.Models.Notes;
import com.example.bulletinboard.NotesClickListener;
import com.example.bulletinboard.R;

import java.util.ArrayList;
import java.util.List;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    Context context;
    List<Notes> list;
    NotesClickListener listener;

    public NotesListAdapter(Context context, List<Notes> list, NotesClickListener listener) {
        this.listener = listener;
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textViewTitle.setText(list.get(position).getTitle());
        holder.textViewTitle.setSelected(true);

        holder.textViewNotes.setText(list.get(position).getNotes());

        holder.textViewDate.setText(list.get(position).getDate());
        holder.textViewDate.setSelected(true);

        if (list.get(position).isPinned())
        {
            holder.imageViewPin.setImageResource(R.drawable.ic_pin);
        }
        else
        {
            holder.imageViewPin.setImageResource(0);
        }

        int color_code = getRandomColor();

        holder.notesContainer.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));

        holder.notesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClick(list.get(holder.getAdapterPosition()));
            }
        });

        holder.notesContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongCLick(list.get(holder.getAdapterPosition()), holder.notesContainer);
                return true;
            }
        });
    }

    private int getRandomColor()
    {
        List<Integer> colors = new ArrayList<>();
        colors.add(R.color.color1);
        colors.add(R.color.color2);
        colors.add(R.color.color3);
        colors.add(R.color.color4);
        colors.add(R.color.color5);
        colors.add(R.color.color6);

        return colors.get((int) (Math.random()*colors.size()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Notes> filteredList)
    {
        list = filteredList;
        notifyDataSetChanged();
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder {

    CardView notesContainer;
    TextView textViewTitle, textViewNotes, textViewDate;
    ImageView imageViewPin;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        notesContainer = itemView.findViewById(R.id.notes_container);
        textViewNotes = itemView.findViewById(R.id.textView_notes);
        textViewTitle = itemView.findViewById(R.id.textView_title);
        imageViewPin = itemView.findViewById(R.id.imageView_pin);
        textViewDate = itemView.findViewById(R.id.textView_date);
    }
}
