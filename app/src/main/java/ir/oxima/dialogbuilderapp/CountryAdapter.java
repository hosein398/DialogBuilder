package ir.oxima.dialogbuilderapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.RowHolder> {


    class RowHolder extends RecyclerView.ViewHolder {

        public TextView txt_name;
        public TextView txt_code;

        public RowHolder(View itemView) {
            super(itemView);
            txt_code = itemView.findViewById(R.id.txt_code);
            txt_name = itemView.findViewById(R.id.txt_name);
        }
    }

    private ArrayList<Country> feedItemList;
    private Context context;


    public CountryAdapter(Context context, ArrayList<Country> array) {
        this.feedItemList = array;
        this.context = context;
    }


    @Override
    public RowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_country, viewGroup, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(final RowHolder holder, final int position) {
        if (feedItemList == null){
            return;
        }

        Country country = feedItemList.get(position);
        holder.txt_code.setText("+" + country.getCode());
        holder.txt_name.setText(country.getName());
    }

    @Override
    public int getItemCount() {
        return (feedItemList != null ? feedItemList.size() : 0);
    }
}
