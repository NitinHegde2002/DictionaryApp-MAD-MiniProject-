package com.example.dictionaryapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionaryapp.Adapters.MeaningAdapter;
import com.example.dictionaryapp.models.APIResponse;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SearchView search_view;
    TextView textView_word;
    RecyclerView recyclerView_phonetics, recycler_meanings;
    ProgressDialog progressDialog;
    MeaningAdapter meaningAdapter;

    ImageButton sound;

    String word;
    TextToSpeech txtspch;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_view= findViewById(R.id.search_view);
        textView_word=findViewById(R.id.textview_word);
        recycler_meanings=findViewById(R.id.recycler_meanings);
        recyclerView_phonetics=findViewById(R.id.recycler_phonetics);
        sound=(ImageButton) findViewById(R.id.imageButton);
        sound.setOnClickListener(this);
        txtspch=new TextToSpeech(getBaseContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR)
                {
                    Toast.makeText(getBaseContext(),
                            "Success",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        progressDialog=new ProgressDialog(this);

        progressDialog.setTitle("Loading.... ");
        progressDialog.show();
        RequestManager manager=new RequestManager(MainActivity.this);
        manager.getWordMeaning(listener, "hello");

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching response for " + query);
                progressDialog.show();
                RequestManager manager=new RequestManager(MainActivity.this);
                manager.getWordMeaning(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    protected final onFetchDataListener listener = new onFetchDataListener() {
        @Override
        public void onFetchData(APIResponse apiResponse, String message) {
            progressDialog.dismiss();
            if(apiResponse==null){
                Toast.makeText(MainActivity.this,"No data found!!!",Toast.LENGTH_SHORT).show();
                return;
            }
            showData(apiResponse);
        }

        @Override
        public void onError(String message) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, message,Toast.LENGTH_SHORT).show();
        }
    };
    private void showData(APIResponse apiResponse) {
        word=apiResponse.getWord();
        textView_word.setText("Word: "+word);

        recycler_meanings.setHasFixedSize(true);
        recycler_meanings.setLayoutManager(new GridLayoutManager(this,1));
        meaningAdapter=new MeaningAdapter(this,apiResponse.getMeanings());
        recycler_meanings.setAdapter(meaningAdapter);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(sound))
            txtspch.speak(word,TextToSpeech.QUEUE_FLUSH,null);
    }
}