package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Contact.class}, version=1,exportSchema = false)
public abstract class AppDataBaseContact extends RoomDatabase {
    public abstract ContactDao contactDao();
}
