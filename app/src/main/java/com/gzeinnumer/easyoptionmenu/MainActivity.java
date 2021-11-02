package com.gzeinnumer.easyoptionmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzeinnumer.eom.dialog.DynamicOptionMenu;
import com.gzeinnumer.eom.DynamicOptionMenuBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ExampleModel> level1 = new ArrayList<>();
    List<ExampleModel> level2 = new ArrayList<>();
    List<ExampleModel> level3 = new ArrayList<>();
    List<ExampleModel> level4 = new ArrayList<>();

    private TextView tv;
    private Button btnSingle;
    private Button btn2Level;
    private Button btnMoreThan2Level;

    private void appent(String s) {
        String last = tv.getText().toString();
        last = last + "\n" + s;
        tv.setText(last);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        btnSingle = findViewById(R.id.btn_single);
        btn2Level = findViewById(R.id.btn_2_level);
        btnMoreThan2Level = findViewById(R.id.btn_more_than_2_level);

        initDummyData();

        sample1();
        sample2();
        sample3();
    }

    private void initDummyData() {
        level1.add(new ExampleModel(1, "Title 1", "Address 1"));
        level1.add(new ExampleModel(2, "Title 2", "Address 2"));
        level1.add(new ExampleModel(3, "Title 3", "Address 3"));
        level1.add(new ExampleModel(4, "Title 4", "Address 4"));
        level1.add(new ExampleModel(5, "Title 5", "Address 5"));

        level2.add(new ExampleModel(6, "Title 1.1", "Address 6"));
        level2.add(new ExampleModel(7, "Title 1.2", "Address 7"));
        level2.add(new ExampleModel(8, "Title 1.3", "Address 8"));
        level2.add(new ExampleModel(9, "Title 1.4", "Address 9"));
        level2.add(new ExampleModel(10, "Title 1.5", "Address 10"));

        level3.add(new ExampleModel(11, "Title 1.1.1", "Address 11"));
        level3.add(new ExampleModel(12, "Title 1.1.2", "Address 12"));
        level3.add(new ExampleModel(13, "Title 1.1.3", "Address 13"));

        level4.add(new ExampleModel(14, "Title 1.1.1.1", "Address 14"));
        level4.add(new ExampleModel(15, "Title 1.1.1.2", "Address 15"));
    }

    private void sample1() {
        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
                        .builder(level1)
                        .setTitle("Pilih Merek")
                        .finalCallBack(new DynamicOptionMenu.CallBackFinal<ExampleModel>() {
                            @Override
                            public void positionItem(ExampleModel data) {
                                appent("Level 2_" + data);
                            }
                        })
                        .show();
            }
        });
    }

    private void sample2() {
        btn2Level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
                        .builder(level1)
                        .setTitle("Pilih Merek")
                        //ignore if your menu only have 1 level
                        .addSub(new DynamicOptionMenu.CallBack<ExampleModel>() {
                            @Override
                            public List<ExampleModel> positionItem(ExampleModel data) {
                                appent("Level 1_" + data);
                                return level2;
                            }
                        })
                        //add this callback. important line
                        .finalCallBack(new DynamicOptionMenu.CallBackFinal<ExampleModel>() {
                            @Override
                            public void positionItem(ExampleModel data) {
                                appent("Level 2_" + data);
                            }
                        })
                        .show();
            }
        });
    }

    private void sample3() {
        btnMoreThan2Level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("");
                new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
                        .builder(level1)
                        .setTitle("Pilih Merek")
                        //ignore if your menu only have 1 level
                        .addSub(new DynamicOptionMenu.CallBack<ExampleModel>() {
                            @Override
                            public List<ExampleModel> positionItem(ExampleModel data) {
                                appent("Level 1_" + data);
                                return level2;
                            }
                        }, new DynamicOptionMenu.CallBack<ExampleModel>() {
                            @Override
                            public List<ExampleModel> positionItem(ExampleModel data) {
                                appent("Level 2_" + data);
                                return level3;
                            }
                        }, new DynamicOptionMenu.CallBack<ExampleModel>() {
                            @Override
                            public List<ExampleModel> positionItem(ExampleModel data) {
                                appent("Level 3_" + data);
                                return level4;
                            }
                        })
                        //add this callback. important line
                        .finalCallBack(new DynamicOptionMenu.CallBackFinal<ExampleModel>() {
                            @Override
                            public void positionItem(ExampleModel data) {
                                appent("Level 4_" + data);
                            }
                        })
                        .show();
            }
        });
    }
}