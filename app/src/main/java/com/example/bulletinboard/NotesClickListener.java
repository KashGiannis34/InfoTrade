package com.example.bulletinboard;

import androidx.cardview.widget.CardView;

import com.example.bulletinboard.Models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongCLick(Notes notes, CardView cardView);
}
