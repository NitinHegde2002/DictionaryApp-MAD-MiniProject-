package com.example.dictionaryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.ViewHolders.MeaningsViewHolder;
import com.example.dictionaryapp.models.Meanings;

import java.util.List;

public class MeaningAdapter extends RecyclerView.Adapter<MeaningsViewHolder> {
    private Context context;
    private List<Meanings>meaningsList;

    public MeaningAdapter(Context context, List<Meanings> meaningsList) {
        this.context = context;
        this.meaningsList = meaningsList;
    }

    @NonNull
    @Override
    public MeaningsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MeaningsViewHolder(LayoutInflater.from(context).inflate(R.layout.meanings_list_items, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MeaningsViewHolder holder, int position) {
        holder.textView_partsOfSpeech.setText("Parts of Speech: " + meaningsList.get(position).getPartsOfSpeech());
        holder.recycler_definitions.setHasFixedSize(true);
        holder.recycler_definitions.setLayoutManager(new GridLayoutManager(context, 1));
        DefinitionAdapter definitionAdapter=new DefinitionAdapter(context, meaningsList.get(position).getDefinitions());
        holder.recycler_definitions.setAdapter(definitionAdapter);
    }

    @Override
    public int getItemCount() {
        return meaningsList.size();
    }
}
