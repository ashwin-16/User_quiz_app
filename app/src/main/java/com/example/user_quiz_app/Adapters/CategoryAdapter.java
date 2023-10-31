package com.example.user_quiz_app.Adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.user_quiz_app.Models.category_model;
import com.example.user_quiz_app.R;
import com.example.user_quiz_app.SetsActivity;
import com.example.user_quiz_app.databinding.ItemCategoryBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.viewHolder>{

    Context context;
    ArrayList<category_model>list;

    public CategoryAdapter(Context context, ArrayList<category_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_category,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        category_model model=list.get(position);

        holder.binding.categoryName.setText(model.getCategoryName());

        Picasso.get()
                .load(model.getCategoryImage())
                .placeholder(R.drawable.logo)
                .into(holder.binding.categoryImages);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(context, SetsActivity.class);
                        intent.putExtra("category",model.getCategoryName());
                        intent.putExtra("sets",model.getSetNum());
                        intent.putExtra("key",model.getKey());

                        context.startActivity(intent);
                    }
                });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            binding= ItemCategoryBinding.bind(itemView);

        }
    }
}
