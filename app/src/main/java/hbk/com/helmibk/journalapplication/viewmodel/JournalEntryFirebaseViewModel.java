package hbk.com.helmibk.journalapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Map;

import hbk.com.helmibk.journalapplication.database.JounalEntryLocalStroage;
import hbk.com.helmibk.journalapplication.database.JournalEntryFirebaseDB;
import hbk.com.helmibk.journalapplication.interfaces.Entry;
import hbk.com.helmibk.journalapplication.model.JournalEntry;
import hbk.com.helmibk.journalapplication.model.JournalEntryRoom;

public class JournalEntryFirebaseViewModel extends AndroidViewModel {

    public static final String TAG = JournalEntryFirebaseViewModel.class.getSimpleName();
    private JournalEntryFirebaseDB db;

    private List<JournalEntryRoom> listLiveData;
    private Entry.JournalEntriesFetchOnceListener listener;

    public JournalEntryFirebaseViewModel(@NonNull Application application) {
        super(application);

        Log.d(TAG, "Retrieving data");

        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db = JournalEntryFirebaseDB.getInstance(userId);
    }

    public void setOnDataFetchListener(Entry.JournalEntriesFetchOnceListener listener) {
        this.listener = listener;

        db .fetchJournalEntries(new Entry.JournalEntriesFetchOnceListener() {
            @Override
            public void onJournalEntryFetchFailed() {
                listener.onJournalEntryFetchFailed();
            }

            @Override
            public void onJournalEntryFetchSuccess(Map<String, JournalEntry> entryMap) {
                listener.onJournalEntryFetchSuccess(entryMap);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(db != null){
            db.clearListeners();
        }
    }
}
