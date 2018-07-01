package hbk.com.helmibk.journalapplication.interfaces;

import hbk.com.helmibk.journalapplication.model.JournalEntryRoom;

public interface AddEntry {

    public interface AddEntryListener{
        void onEntryAddSuccess();
        void onEntryAddFail();
    }

    public interface UpdateEntryListener{
        void onUpdateEntrySuccess();
        void onUpdateEntryFail();
    }

    public interface DeleteEntryListener{
        void onDeleteEntrySuccess();
        void onDeleteEntryFail();
    }
}
