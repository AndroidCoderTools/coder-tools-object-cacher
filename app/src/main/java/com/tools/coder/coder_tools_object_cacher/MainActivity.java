package com.tools.coder.coder_tools_object_cacher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tools.coder.cacher.impl.LocalCache;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTv;
    private Button mBtn;
    private ArrayList<String> keys = new ArrayList<String>();
    LocalCache localCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView)findViewById(R.id.text);
        mTv.setText("Click add to add data to save data and show the data you saved");
        mBtn = (Button)findViewById(R.id.button);
        localCache = new LocalCache();
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                show();
            }
        });

    }

    private void saveData(){
        String key = System.currentTimeMillis()+"1";
        localCache.putAndSave(this, key, "String:" + System.currentTimeMillis());
        keys.add(key);

        key = System.currentTimeMillis()+"2";
        localCache.putAndSave(this, key, 'c');
        keys.add(key);

        key = System.currentTimeMillis()+"3";
        localCache.putAndSave(this, key, 3);
        keys.add(key);

        key = System.currentTimeMillis()+"4";
        localCache.putAndSave(this, key, 4.001);
        keys.add(key);

        Test test = new Test();
        test.name = "test";
        test.value = "value";

        key = System.currentTimeMillis()+"5";
        localCache.putAndSave(this, key, test);
        keys.add(key);
    }

    private void show(){
        mTv.setText("");
        for (int i=0; i<keys.size(); i++){
            String key = keys.get(i);
            switch (i%5){
                case 0:
                    String vS = (String)localCache.getInner(MainActivity.this,key);
                    mTv.append("String :"+vS+"\n");
                    break;
                case 1:
                    char vC = (char)localCache.getInner(MainActivity.this,key);
                    mTv.append("char :"+new String(new char[]{vC})+"\n");
                    break;

                case 2:
                    int vI = (int)localCache.getInner(MainActivity.this,key);
                    mTv.append("int :"+vI+"\n");
                    break;

                case 3:
                    double vD = (double)localCache.getInner(MainActivity.this,key);
                    mTv.append("double :"+vD+"\n");
                    break;

                case 4:
                    Test vT = (Test)localCache.getInner(MainActivity.this,key);
                    mTv.append("Serializable Object :"+"name:"+vT.name+"  value:"+vT.value+"\n");
                    break;
            }
        }
    }

}
