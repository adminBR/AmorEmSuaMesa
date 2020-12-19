package com.example.amoremsuamesa;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {

    private List<ProductsClass> itensList;
    private List<ProductsClass> itensListFull;
    //private ClickListener clickListener;

    MyAdapter(List<ProductsClass> mItemList, List<ProductsClass> mItemListFull){
        this.itensList = mItemList;
        this.itensListFull = mItemListFull;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, final int position) {
        final ProductsClass item = itensList.get(position);
        holder.name.setText(item.getNome());
        holder.price.setText(String.valueOf("R$ "+item.getPreco()+",00"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                staticStorageClass.selectedProduct = itensList.get(position);
                Intent intent = new Intent(v.getContext(), teste.class);
                //intent.putExtra("product_id", ""+staticStorageClass.ListaCompletaProdutos.get(staticStorageClass.ListaCompletaProdutos.indexOf(itensList.get(position))));
                v.getContext().startActivity(intent);
                Toast toast = Toast.makeText(v.getContext(),""+position,Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return itensList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductsClass> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(itensListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for( ProductsClass item : itensListFull){
                    if(item.getNome().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itensList.clear();
            itensList.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView name,price;
        private LinearLayout itemLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            price = itemView.findViewById(R.id.tvPrice);
            itemLayout =  itemView.findViewById(R.id.itemLayout);
        }
    }
}