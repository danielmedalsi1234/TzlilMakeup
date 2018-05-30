package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewHolderPriceOffersList extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
    private LinearLayout item;
    private static final String TAG = "RecyclerViewHolder";
    public TextView txt_descriptionName,txt_descriptionPhone;
    public ItemClickListener itemClickListener;

    public RecyclerViewHolderPriceOffersList(View itemView) {
        super(itemView);
        item = (LinearLayout) itemView.findViewById(R.id.ListOffersPrices);
        txt_descriptionName = (TextView)itemView.findViewById(R.id.txt_description_name);
        txt_descriptionPhone = (TextView)itemView.findViewById(R.id.txt_description_phone);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.OnClick(v,getAdapterPosition(),false);
        Log.d(TAG,"ONCLICK:WORK!!!");
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.OnClick(v,getAdapterPosition(),true);
        return true;
    }
}
//Todo Adapter for tournament -------------------------------------------------------------!!!
class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolderPriceOffersList>  {
    private final static String TAG = null;
    private List<Contact> listData = new ArrayList<>();
    private Context context;




    public RecyclerAdapter(List<Contact> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolderPriceOffersList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.text_name_tournament_for_recyler,parent,false);
        return new RecyclerViewHolderPriceOffersList(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolderPriceOffersList holder, int position) {
        holder.txt_descriptionName.setText(listData.get(position).get_name());
        holder.txt_descriptionPhone.setText(listData.get(position).get_phone_number());

        //todo holder.txt_title.setText(listData.get(position).getFirstName());
        Log.d(TAG,"OnBindWork");

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position, boolean isLongClick) {
                String inputName = listData.get(position).get_name();
                String inputPhone = listData.get(position).get_phone_number();

                /*dialogMyTournament = new DialogMyTournament();
                User user = listData.get(position);
                dialogMyTournament.setUserPosition(user);
                DialogEnterTournament dialogEnterTournament = new DialogEnterTournament();
                dialogEnterTournament.setPasswordOfPosition(listData.get(position).getPassword());

                DialogMyTournament dialog = new DialogMyTournament();
                FragmentManager manager = ((Activity) context).getFragmentManager();
                dialog.show(manager,"DialogMyTournament");
                dialog.setCancelable(true);
                dialog.setGetNamePosition(input);*/

            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    {

    }
}
