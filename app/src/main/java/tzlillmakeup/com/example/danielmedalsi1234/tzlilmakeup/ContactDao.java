package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contact")
    List<Contact> getAll();

    @Query("SELECT * FROM contact")
    Cursor getCursorAll();

    @Insert
    void insertAll(Contact... contacts);

    @Delete
    void delete(Contact... contact);

    @Query("DELETE FROM contact")
    void deleteAll();



}
