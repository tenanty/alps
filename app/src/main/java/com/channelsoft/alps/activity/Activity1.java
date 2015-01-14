package com.channelsoft.alps.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.channelsoft.alps.R;
import com.channelsoft.alps.activity.adapter.RouteAdapter;
import com.channelsoft.alps.activity.base.BaseActivity;
import com.channelsoft.alps.activity.to.Route;

import java.util.ArrayList;
import java.util.List;

/**
 * 路线activity
 */
public class Activity1 extends BaseActivity {

    //将所有路线信息放置在集合中
    private List<Route> routes = new ArrayList<Route>();

    //跳转到录音页面
    private Button button;

    private ListView lv;

    //模拟ViewList数据集合
    private String[] data = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Graps", "Pineapples", "Strawberry", "Cherry", "Mango"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_layout);
        //初始化路线信息
        initRoute();
        RouteAdapter adapter = new RouteAdapter(Activity1.this, R.layout.route_item, routes);//使用自定义模板route_item

        button = (Button) findViewById(R.id.to_record);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Activity1.this, CustomSoundActivity.class);
                startActivity(intent);
            }
        });

//        ArrayAdapter aa = new ArrayAdapter<String>(Activity1.this, android.R.layout.simple_list_item_1, data);
        lv = (ListView) findViewById(R.id.list_view);
        lv.setAdapter(adapter);
        //设置ListView中item的点击回调方法
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(Activity1.this, "呵呵："+position, Toast.LENGTH_SHORT).show();
                Route route = routes.get(position);
//                Toast.makeText(Activity1.this, route.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.setClass(Activity1.this,CustomSoundActivity.class);
                startActivity(intent);

            }
        });
    }

    /**
     * 初始化路线信息
     */
    private void initRoute() {
        //添加信息
        Route route1 = new Route("航天桥 - 朝阳公园桥", R.drawable.route_1, 12);
        routes.add(route1);

        //添加信息
        Route route2 = new Route("航天桥 - 天通苑", R.drawable.route_2, 14);
        routes.add(route2);

        Route route3 = new Route("上地 - 广安门", R.drawable.route_3, 1);
        routes.add(route3);

        Route route4 = new Route("朝阳公园桥 - 广安门", R.drawable.route_4, 21);
        routes.add(route4);

        Route route5 = new Route("天通苑 - 九棵松", R.drawable.route_5, 33);
        routes.add(route5);
    }
}
