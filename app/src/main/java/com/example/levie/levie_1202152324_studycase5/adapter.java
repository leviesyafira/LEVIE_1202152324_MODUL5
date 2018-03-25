package com.example.levie.levie_1202152324_studycase5;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by varian on 3/25/2018.
 */

public class adapter extends RecyclerView.Adapter<adapter.holder> {
    //mendeklarasi variable yang digunakan
    private Context context;
    private List<itemTodo> item;
    int id;

    //constructor
    public adapter(Context context, List<itemTodo> item, int id) {
        this.context = context;
        this.item = item;
        this.id = id;
    }

    //untuk menentukan viewholder pada recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview, parent, false);
        holder holder = new holder(view);
        return holder;
    }

    //menentukan nilai objeknya sesuai yang didapatkan pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        itemTodo itemm = item.get(position);
        holder.td.setText(itemm.getTodo());
        holder.ds.setText(itemm.getDesc());
        holder.pr.setText(itemm.getPrior());
        holder.card.setCardBackgroundColor(context.getResources().getColor(this.id));
    }
    public itemTodo getItem(int position){
        return item.get(position);
    }

    //menghapus list pada todolist
    public void removeitem(int i){
        item.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, item.size());
    }

    //mendapatkan jumlah list
    @Override
    public int getItemCount() {
        return item.size();
    }

    class holder extends RecyclerView.ViewHolder{
        //mendeklarasi variable yang digunakan
        TextView td, ds, pr;
        CardView card;
        public holder(View itemView) {
            super(itemView);
            //mengakses id text view pada layout dan juga cardview
            td = itemView.findViewById(R.id.todorv);
            ds = itemView.findViewById(R.id.descrv);
            pr = itemView.findViewById(R.id.priorv);
            card = itemView.findViewById(R.id.cd);
        }
    }
}

