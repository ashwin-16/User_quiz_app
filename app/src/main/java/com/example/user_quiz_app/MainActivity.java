package com.example.user_quiz_app;

import static android.R.color.transparent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user_quiz_app.Adapters.CategoryAdapter;
import com.example.user_quiz_app.Models.category_model;
import com.example.user_quiz_app.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseDatabase database;


    ArrayList<category_model> list;
    CategoryAdapter adapter;
    ProgressDialog progressDialog;
    Dialog loadingDialog;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        list = new ArrayList<>();

        loadingDialog=new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_dialog);
        if (loadingDialog.getWindow()!=null){
            loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
            loadingDialog.setCancelable(false);
        }
        loadingDialog.show();

        database = FirebaseDatabase.getInstance();

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.recyCategory.setLayoutManager(layoutManager);

        adapter = new CategoryAdapter(this, list);
        binding.recyCategory.setAdapter(adapter);




        database.getReference().child("categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        list.add(new category_model(
                                dataSnapshot.child("categoryName").getValue().toString(),
                                dataSnapshot.child("categoryImage").getValue().toString(),
                                dataSnapshot.getKey(), // Use the key as the category name
                                Integer.parseInt(dataSnapshot.child("setNum").getValue().toString())
                        ));
                    }
                    adapter.notifyDataSetChanged();
                    loadingDialog.dismiss();


                } else {
                    Toast.makeText(MainActivity.this, "Category not exist", Toast.LENGTH_LONG).show();

                    loadingDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}