package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.DialogFragment;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DialogLoginManager extends DialogFragment {

    private EditText txtNameManager,txtPassword;
    private Button BtnOk,BtnCancel;
    private TextView lblTittle;

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

                if (name_tournament.equals("tzlilKatan") && password.equals("tzlil1234")){
                    startActivity(new Intent(view.getContext(),ListOfItems.class));
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