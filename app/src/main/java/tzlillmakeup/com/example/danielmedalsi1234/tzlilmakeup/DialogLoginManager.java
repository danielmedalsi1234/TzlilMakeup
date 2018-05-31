package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.DialogFragment;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DialogLoginManager extends DialogFragment {

    private EditText txtNameManager,txtPassword;
    private Button BtnOk,BtnCancel;
    private TextView lblTittle;
    public static final String TAG = "DialogLoginManager";



    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.manager_login_dialog,container,false);

        BtnOk = view.findViewById(R.id.BtnOk);
        BtnCancel = view.findViewById(R.id.BtnCancel);
        txtPassword = view.findViewById(R.id.txtPassword);
        txtNameManager = view.findViewById(R.id.txtNameManager);
        lblTittle = view.findViewById(R.id.lblTittleManager);


        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        BtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_tournament = txtNameManager.getText().toString();
                String password = txtPassword.getText().toString();

                if (name_tournament.equals("1") && password.equals("1")){
                    AppDataBaseContact db;
                    List<Contact> listData = new ArrayList<>();
                    db = Room.databaseBuilder(view.getContext(), AppDataBaseContact.class, "contact")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                    listData = db.contactDao().getAll();
                    if (listData.isEmpty()){
                        Toast.makeText(view.getContext(),"The list is Empty!",Toast.LENGTH_SHORT).show();
                    }else {
                        startActivity(new Intent(view.getContext(),PriceOffersList.class));
                    }
                }
                else {
                    Toast.makeText(view.getContext(), "Manger or Password incorrect",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
