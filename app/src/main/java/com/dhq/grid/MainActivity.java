package com.dhq.grid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.dhq.grid.custom.CustomGridListener;
import com.dhq.gridview.GridLayout;
import com.dhq.gridview.divider.DividerBuilder;
import com.dhq.gridview.divider.RvDivider;
import com.dhq.gridview.gridlistener.GridImageListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridLayout gridImageView,gridImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridImageView = findViewById(R.id.giv_img);
        gridImageView2 = findViewById(R.id.giv_img2);

        RvDivider divider = DividerBuilder.getInstance(this)
                .setSpaceColor(android.R.color.transparent,R.dimen.dp750_20)
                .build();

        gridImageView.getGridImageBuild().setCanAdd(true)
                .setMaxCount(9)
                .setDivider(divider)
                .setImageListener(new GridImageListener() {
                    @Override
                    public void addClick(int position, int count) {
                        //增加点击事件

                    }

                    @Override
                    public void loadImage(ImageView imageView, Object item) {
                        //加载图片
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    }
                }).build();



        gridImageView2.getGridImageBuild().setCanAdd(true)
                .setMaxCount(9)
                .setDivider(divider)
                .setImageListener(new CustomGridListener()).build();




        ArrayList arrayList = new ArrayList();
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");

        gridImageView.setDatas(arrayList);
        gridImageView2.setDatas(arrayList);
    }

}
