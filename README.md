# 九宫格控件
1.使用说明
	
- 布局文件引用
    ```
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent" >

        <com.dhq.gridview.GridLayout
            android:id="@+id/giv_img"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:gridImageColumn="3">

        </com.dhq.gridview.GridLayout>

    </LinearLayout>
    ```
- 代码中引用

	```
    //分割线
    RvDivider divider = DividerBuilder.getInstance(this)
                .setSpaceColor(android.R.color.transparent,R.dimen.20dp)//设置分割线颜色和宽度
                .build();

        gridImageView.getGridImageBuild().setCanAdd(true)
                .setMaxCount(9)//设置显示的最多布局
                .setDivider(divider)//设置分割线
                //设置九宫格监听（可更换）
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

	```