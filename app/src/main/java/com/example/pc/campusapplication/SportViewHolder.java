package com.example.pc.campusapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SportViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView mTitleView, mdescriptionView;
    View mView;
    private ImageView mImagebuttonView;
    private SportListCallBack sportListCallback;
String imagein;

    public SportViewHolder(final View itemView) {
        super(itemView);

        mView = itemView;
        mTitleView = (TextView) mView.findViewById(R.id.rTitle);
        mImagebuttonView = (ImageView) mView.findViewById(R.id.rImage);
        mdescriptionView = (TextView) mView.findViewById(R.id.rdescription);
//set onclick event
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();

                if(position == 0) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, MarathonActivity.class);
                    intent.putExtra("title", mTitleView.getText().toString());
                    intent.putExtra("des", mdescriptionView.getText().toString());
                    context.startActivity(intent);
                }else if(position == 1){
                    Context context = v.getContext();
                    Intent intent = new Intent(context, RobotActivity.class);
                    intent.putExtra("title", mTitleView.getText().toString());
                    intent.putExtra("des", mdescriptionView.getText().toString());
                    context.startActivity(intent);
                }
            }
        });

    }

    //set detail to item
    public void setDetail(Context context, String title, String image, String description) {
        //View
        mTitleView = (TextView) mView.findViewById(R.id.rTitle);
        mImagebuttonView = (ImageView) mView.findViewById(R.id.rImage);
        mdescriptionView = (TextView) mView.findViewById(R.id.rdescription);
        //set Data
        mTitleView.setText(title);
        Picasso.get().load(image).into(mImagebuttonView);

        mdescriptionView.setText(description);

    }

        @Override
    public void onClick(View view) {

    }
    public void setCallback(SportListCallBack callback) {
        this.sportListCallback = callback;
    }
}


