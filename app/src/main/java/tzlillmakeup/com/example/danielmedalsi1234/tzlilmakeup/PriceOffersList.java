package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PriceOffersList extends Activity {

    RecyclerView recyclerView;
    private FloatingActionButton fabDelete;
    private RecyclerView.LayoutManager layoutManager;
    List<Contact> listData = new ArrayList<>();
    AppDataBaseContact db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_offers_prices);
        fabDelete = findViewById(R.id.fabDelete);

         db = Room.databaseBuilder(PriceOffersList.this, AppDataBaseContact.class, "contact")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        listData = db.contactDao().getAll();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final RecyclerView.Adapter adapter = new RecyclerAdapter(listData, this);
        recyclerView.setAdapter(adapter);

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.contactDao().deleteAll();
                if (!listData.isEmpty()){
                    Toast.makeText(PriceOffersList.this,"Delete all Seccessfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PriceOffersList.this,MainActivity.class));
                }else {
                    Toast.makeText(PriceOffersList.this, "Delete Error!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}