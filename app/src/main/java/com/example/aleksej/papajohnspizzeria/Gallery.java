package com.example.aleksej.papajohnspizzeria;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Gallery extends AppCompatActivity {

    private LinearLayout mGallery;
    private int[] mImgIds;
    private LayoutInflater mInflater;
    private HorizontalScrollView horizontalScrollView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.gallery);

        mInflater = LayoutInflater.from(this);
        initData();
        initView();
    }

    private void initData() {
        mImgIds = new int[] { R.drawable.pizza1, R.drawable.pizza2, R.drawable.pizza3,R.drawable.pizza4, R.drawable.pizza5
        };
    }

    private void initView() {
        mGallery = (LinearLayout) findViewById(R.id.gallery);

        for (int i = 0; i < mImgIds.length; i++)
        {

            View view = mInflater.inflate(R.layout.gallery_item,
                    mGallery, false);
            ImageView img = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);
            img.setImageResource(mImgIds[i]);
            mGallery.addView(view);
        }
    }


}
