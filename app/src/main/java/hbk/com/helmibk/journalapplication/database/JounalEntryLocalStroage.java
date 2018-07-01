package hbk.com.helmibk.journalapplication.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import hbk.com.helmibk.journalapplication.dao.EntryDAO;
import hbk.com.helmibk.journalapplication.model.JournalEntryRoom;
import hbk.com.helmibk.journalapplication.utils.DateConverter;

@Database(entities = {JournalEntryRoom.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class JounalEntryLocalStroage extends RoomDatabase {

    private static final String TAG = JounalEntryLocalStroage.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABSE_NAME = "journalentry";
    private static JounalEntryLocalStroage instance;

    public static JounalEntryLocalStroage getInstance(Context context){
        if(instance == null){
            synchronized (LOCK){
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        JounalEntryLocalStroage.class, DATABSE_NAME)
                        .allowMainThreadQueries()
                        .build();
            }
        }

        return instance;
    }

    public abstract EntryDAO entryDAO();
}
