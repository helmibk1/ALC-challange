package hbk.com.helmibk.journalapplication.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import hbk.com.helmibk.journalapplication.database.JounalEntryLocalStroage;
import hbk.com.helmibk.journalapplication.model.JournalEntryRoom;

public class JournalEntryViewModel extends AndroidViewModel {

    public static final String TAG = JournalEntryViewModel.class.getSimpleName();

    private LiveData<List<JournalEntryRoom>> listLiveData;
    public JournalEntryViewModel(@NonNull Application application) {
        super(application);

        Log.d(TAG, "Retrieving datas");

        JounalEntryLocalStroage database = JounalEntryLocalStroage.getInstance(this.getApplication());
        listLiveData = database.entryDAO().fetchAllEntries();
    }

    public LiveData<List<JournalEntryRoom>> getListLiveData() {
        return listLiveData;
    }
}
