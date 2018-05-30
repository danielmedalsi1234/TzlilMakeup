package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button BtnList;
    private Button btn_prices;
    private Button btn_ManagerLogin;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_prices = findViewById(R.id.btn_prices);
        BtnList = findViewById(R.id.btn_sendDetails);
        btn_ManagerLogin = findViewById(R.id.btn_login_manager);

        btn_ManagerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoginManager dialog = new DialogLoginManager();
                dialog.show(getFragmentManager(),TAG);
                dialog.setCancelable(true);
            }
        });
    }


    public void onClickPrices(View view) {
        Intent intent = new Intent(MainActivity.this,Prices.class);
        startActivity(intent);
    }

    public void onClickList(View view) {
        Intent intent = new Intent(MainActivity.this,ListOfItems.class);
        startActivity(intent);
    }

}
