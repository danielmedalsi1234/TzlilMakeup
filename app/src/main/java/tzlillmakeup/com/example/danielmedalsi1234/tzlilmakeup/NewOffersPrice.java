package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by danielmedalsi1234 on 24/03/18.
 */

public class NewOffersPrice extends Activity {

    EditText txtName;
    TextView lblName;
    EditText txtPhone;
    TextView lblPhone;
    Button BtnDone;
    AppDataBaseContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_offers_new);

        txtName = findViewById(R.id.txt_name);
        lblName = findViewById(R.id.lbl_name);
        txtPhone = findViewById(R.id.txt_phone);
        lblPhone = findViewById(R.id.lbl_phone);
        BtnDone = findViewById(R.id.btn_done);

        BtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameEntery = txtName.getText().toString();
                String phoneEntery = txtPhone.getText().toString();
                if (txtPhone.length() != 0){
                    AppDataBaseContact db = Room.databaseBuilder(NewOffersPrice.this.getApplicationContext(),AppDataBaseContact.class,"contact")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                    db.contactDao().insertAll(new Contact(nameEntery,phoneEntery));
                    Toast.makeText(NewOffersPrice.this, "פנייתך הועברה לטיפול תודה !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewOffersPrice.this,MainActivity.class));
                }else
                    Toast.makeText(NewOffersPrice.this, "פנייתך לא התקבלה", Toast.LENGTH_SHORT).show();
            }
        });
    }





}
