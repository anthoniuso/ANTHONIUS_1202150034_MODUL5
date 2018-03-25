package com.levegra.anthonius_1202150034_modul5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.container> {
    private Context context;
    private List<ModelAddData> list;
    int warna;

    //Constructor
    public RecyclerViewAdapter(Context konteks, List<ModelAddData> list, int warna){
        this.context=konteks;
        this.list=list;
        this.warna=warna;
    }

    class container extends RecyclerView.ViewHolder {
        public TextView todo, description, priority;
        public CardView card_view;
        public container(View itemView){
            super(itemView);

            todo = itemView.findViewById(R.id.todo);
            description = itemView.findViewById(R.id.description);
            priority = itemView.findViewById(R.id.number);
            card_view = itemView.findViewById(R.id.cardview);
        }
    }

    @Override
    public container onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        container holder = new container(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(container holder, int position) {
        ModelAddData data = list.get(position);
        holder.todo.setText(data.getTodo());
        holder.description.setText(data.getDesc());
        holder.priority.setText(data.getPrior());
        holder.card_view.setCardBackgroundColor(context.getResources().getColor(this.warna));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public ModelAddData getData(int position){
        return list.get(position);
    }

    public void deleteData(int i){
        list.remove(i);

        notifyItemRemoved(i);
        notifyItemRangeChanged(i, list.size());
    }

}

