package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by danielmedalsi1234 on 24/03/18.
 */

public class Prices extends Activity {


    EditText txtName;
    TextView lblName;
    EditText txtPhone;
    TextView lblPhone;
    Button BtnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prices);

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
                    AddData(nameEntery,phoneEntery);
                    txtName.setText("");
                    phoneEntery.getBytes();
                }else
                    Toast.makeText(Prices.this, "Erorr", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void AddData(String name,String phone){

    }




}
