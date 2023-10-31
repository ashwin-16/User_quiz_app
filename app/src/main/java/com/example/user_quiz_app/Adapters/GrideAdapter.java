package com.example.user_quiz_app.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.user_quiz_app.QuestionActivity;
import com.example.user_quiz_app.R;


public class GrideAdapter extends BaseAdapter {

    public int sets=0;
    private String category;

    public GrideAdapter(int sets, String category) {
        this.sets = sets;
        this.category = category;
    }

    @Override
    public int getCount() {
        return sets+1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View view1;

        if(view==null){
            view1= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sets,viewGroup,false);
        }
        else{
            view1=view;
        }
        if(i==0){
            ((CardView)view1.findViewById(R.id.setsCard)).setVisibility(View.GONE);
        }
        else{
            ((TextView)view1.findViewById(R.id.setName)).setText(String.valueOf(i));
        }

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent=new Intent(viewGroup.getContext(), QuestionActivity.class);
                    intent.putExtra("setNum",1);
                    intent.putExtra("categoryName",category);
                    viewGroup.getContext().startActivity(intent);
//                    Toast.makeText(viewGroup.getContext(), "wait",Toast.LENGTH_LONG).show();

            }
        });
        return view1;
    }

}
