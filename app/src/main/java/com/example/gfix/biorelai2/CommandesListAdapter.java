package com.example.gfix.biorelai2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mmaitre on 05/04/2019.
 */

public class CommandesListAdapter extends BaseAdapter {
    private List<Commande> listCommandes;
    private LayoutInflater layoutInflater;
    private Context context;

    public CommandesListAdapter(Context aContext,  List<Commande> listCommande) {
        this.context = aContext;
        this.listCommandes = listCommande;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listCommandes.size();
    }

    @Override public Object getItem(int position) {
        return listCommandes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_item_commandes, null);
            holder = new ViewHolder();
            holder.textViewIdCommande = (TextView) convertView.findViewById(R.id.textView_idCommande);
            holder.textViewNomAdherent = (TextView) convertView.findViewById(R.id.textView_nomAdherent);
            holder.textViewPrenomAdherent = (TextView) convertView.findViewById(R.id.textView_prenom);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Commande commande = this.listCommandes.get(position);
        holder.textViewIdCommande.setText("Commande n° " + commande.getIdCommande());
        holder.textViewNomAdherent.setText(commande.getIdAdherent());
        holder.textViewPrenomAdherent.setText(commande.getIdAdherent());
        return convertView;
    }

    static class ViewHolder {
        TextView textViewIdCommande;
        TextView textViewNomAdherent;
        TextView textViewPrenomAdherent;
    }


}
