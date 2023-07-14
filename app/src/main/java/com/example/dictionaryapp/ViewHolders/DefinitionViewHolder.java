package com.example.dictionaryapp.ViewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;

public class DefinitionViewHolder extends RecyclerView.ViewHolder {
    public TextView textView_definition,textView_example;

    public DefinitionViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_definition=itemView.findViewById(R.id.textView_definition);
        textView_example=itemView.findViewById(R.id.textView_example);

    }
}
