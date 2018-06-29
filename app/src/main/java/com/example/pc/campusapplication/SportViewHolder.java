package com.example.pc.campusapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    View mView;

    public SportViewHolder(View itemView) {
        super(itemView);

        mView = itemView;
//set onclick event
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, SportActivity.class);
                context.startActivity(intent);
            }
        });

    }
//set detail to item
    public void setDetail(Context context, String title, String image, String description){
        //View
        TextView mTitleView = (TextView) mView.findViewById(R.id.rTitle);
        ImageView mImagebuttonView = (ImageView) mView.findViewById(R.id.rImage);
        TextView mdescriptionView = (TextView) mView.findViewById(R.id.rdescription);
        //set Data
        mTitleView.setText(title);
        Picasso.get().load(image).into(mImagebuttonView);

        mdescriptionView.setText(description);

    }

    @Override
    public void onClick(View view) {
    }
}
