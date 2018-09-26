package com.itconsult.nlp.mvvm;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivityFS extends AppCompatActivity {
    private NoteViewModelFS noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NoteAdapterFS adapter = new NoteAdapterFS();
        recyclerView.setAdapter(adapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModelFS.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<NoteFS>>() {
            @Override
            public void onChanged(@Nullable List<NoteFS> notes) {
                //update RecyclerView
                adapter.setNotes(notes);
            }
        });
    }
}