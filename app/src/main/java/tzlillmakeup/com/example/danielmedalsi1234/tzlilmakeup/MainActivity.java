package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button BtnList;
    private Button btn_prices;
    private Button btn_ManagerLogin;
    private Button btn_chat;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_prices = findViewById(R.id.btn_prices);
        BtnList = findViewById(R.id.btn_list);
        btn_ManagerLogin = findViewById(R.id.btn_login_manager);
        btn_chat = findViewById(R.id.btn_login_chat);

        btn_ManagerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoginManager dialog = new DialogLoginManager();
                dialog.show(getFragmentManager(),TAG);
                dialog.setCancelable(true);
            }
        });
        BtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ListOfItems.class);
                    startActivity(intent);

            }
        });
        btn_prices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewOffersPrice.class));
            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ChatOnline.class));
            }
        });
    }
}
