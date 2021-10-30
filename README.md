# EasyOptionMenu
 
```gradle
    //maven { url 'https://jitpack.io' }
    implementation 'com.github.gzeinnumer:EasyOptionMenu:0.0.0-alpha9'
    implementation 'com.github.gzeinnumer:EasyDialogFragment:x.x.x'
    implementation 'com.github.gzeinnumer:SimpleMaterialStyle:x.x.x'
```

- MainActivity
```java
public class ExampleModel {

    private int id;
    private String name;
    private String address;

    //constructor
    //getter setter

    //important line
    @Override
    public String toString() {
        return name+"."+address;
    }
}
```
```java
public class MainActivity extends AppCompatActivity {

    List<ExampleModel> level1 = new ArrayList<>();
    List<ExampleModel> level2 = new ArrayList<>();
    List<ExampleModel> level3 = new ArrayList<>();
    List<ExampleModel> level4 = new ArrayList<>();

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);

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

        findViewById(R.id.btn).setOnClickListener(v -> {
            tv.setText("");
            DynamicOptionMenuBuilder<ExampleModel> dialog = new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
                    .builder(level1)
                    //abaikan ini jika hanya 1 level
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
                    .finalCallBack(new DynamicOptionMenu.CallBackFinal<ExampleModel>() {
                        @Override
                        public void positionItem(ExampleModel data) {
                            appent("Level 4_" + data);
                        }
                    });
            dialog.show();
        });
    }

    private void appent(String s) {
        String last = tv.getText().toString();
        last = last + "\n" + s;
        tv.setText(last);
    }
}
```

---

```
Copyright 2021 M. Fadli Zein
```