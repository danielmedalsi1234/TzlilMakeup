package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by danielmedalsi1234 on 24/03/18.
 */

@Entity(tableName = "contact")
public class Contact {

    public Contact(String fullName,String phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "fullName")
    public String fullName;

    @ColumnInfo(name = "phone_number")
    public String phoneNumber;

    public String get_name() {
        return fullName;
    }

    public void set_name(String _name) {
        this.fullName = _name;
    }

    public String get_phone_number() {
        return phoneNumber;
    }

    public void set_phone_number(String _phone_number) {
        this.phoneNumber = _phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
