package com.example.czlgh.cool;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czlgh.cool.db.Province;
import com.example.czlgh.cool.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private Button button;

    private TextView textView;
    private List<Province> provinceList;
    private List<String> datalist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.get);
       textView=findViewById(R.id.tex);

        queryFromRequest("http://guolin.tech/api/china/");
        queryProvinces();
        textView.setText(datalist.get(1));

    }
    private void  queryFromRequest(String address) {


        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(address).build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() { //将请求发送到消息队列中
            @Override
            public void onFailure(Call call, IOException e) {
                //Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException { //异步线程加载
                String responsetext=response.body().string();
                handleProvinceResponse(responsetext);

            }
        });
    }

    public  void handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvince=new JSONArray(response);
                for(int i=0; i<allProvince.length(); i++){
                    JSONObject jsonObject=allProvince.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(jsonObject.getString("name"));
                    province.setProvinceCode(jsonObject.getInt("id"));
                    if(province.save()){
                        Log.d("MainActivity","储存成功");
                    }
                    else
                    {
                        Log.d("MainActivity","储存失败");
                    }
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }



    private void queryProvinces(){
        provinceList= DataSupport.findAll(Province.class);//省的实例
        datalist=new ArrayList<String>();

        if(provinceList.size()>0){
            for(Province province:provinceList){
                datalist.add(province.getProvinceName());
            }
        }
    }
}


