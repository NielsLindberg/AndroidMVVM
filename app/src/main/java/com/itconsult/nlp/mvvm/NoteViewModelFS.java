package com.itconsult.nlp.mvvm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;


import java.util.List;

public class NoteViewModelFS extends AndroidViewModel {
    private NoteRepositoryFS repository;
    private LiveData<List<NoteFS>> allNotes;

    public NoteViewModelFS(@NonNull Application application) {
        super(application);
        repository = new NoteRepositoryFS();
        allNotes = repository.getAllNotes();
    }

    public LiveData<List<NoteFS>> getAllNotes() {
        return allNotes;
    }
}