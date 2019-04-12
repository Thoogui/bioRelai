package com.example.gfix.biorelai2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CommandesListeAdapter extends BaseAdapter {
    private List<Commande> listCommandes;
    private LayoutInflater layoutInflater;
    private Context context;

    public CommandesListeAdapter(Context aContext, List<Commande>listComm)
    {
        this.context=aContext;
        this.listCommandes=listComm;
        layoutInflater=LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listCommandes.size();
    }

    @Override
    public Object getItem(int position) {
        return listCommandes.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_item_commandes, null);
            holder = new ViewHolder();
            holder.textViewIdCommande = (TextView) convertView.findViewById(R.id.textView_idCommande);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Commande commande = this.listCommandes.get(position);
        holder.textViewIdCommande.setText("Commande n° " + commande.getIdCommande());
        return convertView;
    }

    static class ViewHolder {
        TextView textViewIdCommande;

}
}





