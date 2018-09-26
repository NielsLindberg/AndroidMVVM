package com.itconsult.nlp.mvvm;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Query.Direction;

import java.util.ArrayList;
import java.util.List;
import android.arch.core.util.Function;


public class NoteRepositoryFS {
    private static final String TAG = "NotesRepositoryFS";

    private FirebaseFirestore db;
    private FirebaseQueryLiveData liveData;
    private LiveData<List<NoteFS>> allNotes;

    public NoteRepositoryFS() {
        db = FirebaseFirestore.getInstance();
        liveData = new FirebaseQueryLiveData(getAllQuery());
        allNotes = Transformations.map(liveData, new Deserializer());
    }

    private Query getAllQuery() {
        final Query query = db.collection("Notes").orderBy("priority", Direction.ASCENDING);
        return query;
    }

    private class Deserializer implements Function<QuerySnapshot, List<NoteFS>> {
        @Override
        public List<NoteFS> apply(QuerySnapshot querySnapshot) {
            return convertToList(querySnapshot);
        }
    }

    private List<NoteFS> convertToList(QuerySnapshot querySnapshot) {
        List<NoteFS> notesList=new ArrayList<>();

        if (!querySnapshot.isEmpty()){
            for (DocumentSnapshot snapshot:querySnapshot)
                notesList.add(snapshot.toObject(NoteFS.class));
        }

        return notesList;
    }
    @NonNull
    public LiveData<List<NoteFS>> getAllNotes() {
        return allNotes;
    }
}
