package com.dhq.grid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.dhq.gridview.GridLayout;
import com.dhq.gridview.divider.BaseDividerFactory;
import com.dhq.gridview.divider.GridDivider;
import com.dhq.gridview.gridlistener.GridImageListener;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridImageView = findViewById(R.id.giv_img);

//        GridDivider gridDivider = BaseDividerFactory.builder(this).setSpace(R.dimen.fastscroll_margin).buildGridDivider();
//        BaseDividerFactory.Builder builder = BaseDividerFactory.builder(this);


//        GridDivider gridDivider = BaseDividerFactory.builder(this).setSpaceColor(android.R.color.transparent, R.dimen.fastscroll_margin).buildGridDivider();


        gridImageView.getGridImageBuild().setCanAdd(true)
                .setMaxCount(9)
                .setImageListener(new GridImageListener()).build();


    }

}
