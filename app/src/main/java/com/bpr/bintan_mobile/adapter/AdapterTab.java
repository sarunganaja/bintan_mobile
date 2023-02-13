package com.bpr.bintan_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bpr.bintan_mobile.R;
import com.bpr.bintan_mobile.tabungan.TabModel;

import java.util.List;

public class AdapterTab extends RecyclerView.Adapter<AdapterTab.HolderData> {

    private Context ctx;
    private List<TabModel> listTab;

    public AdapterTab(Context ctx, List<TabModel> listTab) {
        this.ctx = ctx;
        this.listTab = listTab;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tab, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        TabModel tm = listTab.get(position);

        holder.tvid.setText(String.valueOf(tm.getId()));
        holder.tvcif.setText(tm.getCif());
        holder.tvproduk.setText(tm.getTabungan());
        holder.tvnorek.setText(tm.getNorek());
        holder.tvsaldo.setText(tm.getSaldo());

    }

    @Override
    public int getItemCount() {
        return listTab.size();
    }

    public class HolderData extends RecyclerView.ViewHolder{
        TextView tvid, tvcif, tvproduk, tvnorek, tvsaldo;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvid = itemView.findViewById(R.id.tv_idnas);
            tvcif = itemView.findViewById(R.id.tv_cif);
            tvproduk = itemView.findViewById(R.id.tv_produk);
            tvnorek = itemView.findViewById(R.id.tv_norek);
            tvsaldo = itemView.findViewById(R.id.tv_saldo);
        }
    }
}