package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by danielmedalsi1234 on 24/03/18.
 */

@Entity(tableName = "contact")
public class Contact {

    @PrimaryKey
    @NonNull
    public String _name;

    @ColumnInfo
    public int _phone_number;

    public Contact(String _name, int _phone_number) {
        this._name = _name;
        this._phone_number = _phone_number;
    }

    // getting name
    public String getName() {
        return this._name;
    }

    // setting name
    public void setName(String name) {
        this._name = name;
    }

    // getting phone number
    public int getPhoneNumber() {
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(int phone_number) {
        this._phone_number = phone_number;
    }


}
