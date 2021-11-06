| <img src="https://github.com/gzeinnumer/EasyOptionMenu/blob/1.0.0/preview/preview_1.gif" width="300"/> |
|--|

<h1 align="center">
  EasyOptionMenu - Easy Multi Level Options Menu
</h1>

<div align="center">
    <a><img src="https://img.shields.io/badge/Version-1.1.0-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Kotlin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Simple way to select Item Single or Multi</p>
</div>

---
# Content List
* [Download](#download)
* [Feature List](#feature-list)
* [Tech stack and 3rd library](#tech-stack-and-3rd-library)
* [Usage](#usage)
* [Example Code/App](#example-codeapp)
* [Version](#version)
* [Contribution](#contribution)

---
# Download
Add maven `jitpack.io` and `dependencies` in `build.gradle (Project)` :
```gradle
// build.gradle project
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}

// build.gradle app/module
dependencies {
  ...
  implementation 'com.google.android.material:material:1.2.1'
  implementation 'com.github.gzeinnumer:EasyOptionMenu:version'

  implementation 'com.github.gzeinnumer:SimpleMaterialStyle:last-vesion'
  //check on https://github.com/gzeinnumer/SimpleMaterialStyle

    implementation 'com.github.gzeinnumer:EasyDialogFragment:last-vesion'
  //check on https://github.com/gzeinnumer/EasyDialogFragment
}
```
---
# Feature List
- [x] [Single Level Menu](#single-level-menu)
- [x] [Multi Level Menu](#multi-level-menu)
- [x] [Filter](#Filter)

---
# Tech stack and 3rd library
- Material.io ([docs](https://material.io/develop/android/docs/getting-started))
- Multi and Single Selection in Recyclerview ([docs](https://medium.com/@maydin/multi-and-single-selection-in-recyclerview-d29587a7dee2)) ([example](https://github.com/gzeinnumer/MultiandSingleSelectioninRecyclerView))
- Android Slidr ([docs](https://github.com/florent37/android-slidr)) Spesial Thanks.

---
# Usage

**First Step**. Use `MaterialComponents` in your style :

```xml
<style name="AppTheme" parent="Theme.MaterialComponents.Light.NoActionBar">
    <!-- Customize your theme here. -->
</style>

<style name="DynamicOptionMenuDefault" parent="Theme.MaterialComponents.Light.Dialog">
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
    <item name="android:windowMinWidthMajor">80%</item>
    <item name="android:windowMinWidthMinor">80%</item>
    <item name="android:windowEnterAnimation">@anim/anim_in</item>
    <item name="android:windowExitAnimation">@anim/anim_out</item>
</style>
```

Add This Line to `res/color.xml`. **Important**
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="colorPrimary">#FF6200EE</color>
    <color name="colorPrimaryDark">#FF3700B3</color>
    <color name="colorAccent">#FF03DAC5</color>
</resources>
```

#
### Type Data
- **Content Item** there is 2 types data that you can sent to this dialog.

**Type 1**
```java
ArrayList<String> level1 = new ArrayList<>();
level1.add("Lorem ipsum dolor");
new DynamicOptionMenuBuilder<String>(getSupportFragmentManager())
    .builder(level1)
    .setTitle("Choise Brand")
    ...
```
**Type 3** for this type you should override function `toString()` in your `model pojo`
```java
public class ExampleModel {

    private int id;
    private String name;
    private String address;

    //constructor

    //getter
    //setter

    @Override
    public String toString() {
        return name+"."+address;
    }
}
```
And dont forget to declare your `model pojo` after `DynamicOptionMenuBuilder<ModelPojo>`
```java
ArrayList<ExampleModel> level1 = new ArrayList<>();
level1.add(new ExampleModel(1, "Zein", "Balbar"));

new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
    .builder(level1)
    .setTitle("Choise Brand")
    ...
```
#
#### Single Level Menu
Use only `finalCallBack(...)`
```java
List<ExampleModel> level1 = new ArrayList<>();

level1.add(new ExampleModel(1, "Title 1", "Address 1"));
level1.add(new ExampleModel(2, "Title 2", "Address 2"));
level1.add(new ExampleModel(3, "Title 3", "Address 3"));
level1.add(new ExampleModel(4, "Title 4", "Address 4"));
level1.add(new ExampleModel(5, "Title 5", "Address 5"));

new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
    .builder(level1)
    .setTitle("Choise Brand")
    .finalCallBack(new DynamicOptionMenu.CallBackFinal<ExampleModel>() {
        @Override
        public void positionItem(ExampleModel data) {
            appent("Level 1_" + data);
        }
    })
    .show();
```
#
#### Multi Level Menu
- 2 Level Menu
Use `addSub(...)` and `finalCallBack(...)`.

Use `addSub(CallBack)` and return you next level menu with same `ModelPojo`. example

```java
.addSub(new DynamicOptionMenu.CallBack<ExampleModel>() {
    @Override
    public List<ExampleModel> positionItem(ExampleModel data) {
        appent("Level 1_" + data);
        return level2;
    }
})
```
Full code. Just Copy and Paste it
```java
List<ExampleModel> level1 = new ArrayList<>();
List<ExampleModel> level2 = new ArrayList<>();

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

new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
    .builder(level1)
    .setTitle("Choise Brand")
    .addSub(new DynamicOptionMenu.CallBack<ExampleModel>() {
        @Override
        public List<ExampleModel> positionItem(ExampleModel data) {
            appent("Level 1_" + data);
            return level2;
        }
    })
    .finalCallBack(new DynamicOptionMenu.CallBackFinal<ExampleModel>() {
        @Override
        public void positionItem(ExampleModel data) {
            appent("Level 2_" + data);
        }
    })
    .show();
```

#

- More Than 2 Level Menu
Use `addSub(CallBack[])` and `finalCallBack(...)`.

Use `addSub(CallBack[])` and return you next level menu with same `ModelPojo`, and do it again in next pojo. example
```java
.addSub(
    new DynamicOptionMenu.CallBack<ExampleModel>() {
        @Override
        public List<ExampleModel> positionItem(ExampleModel data) {
            appent("Level 1_" + data);
            return level2; //return level 2 menus
        }
    },
    new DynamicOptionMenu.CallBack<ExampleModel>() {...},
    new DynamicOptionMenu.CallBack<ExampleModel>() {...}
)
```
Full code. Just Copy and Paste it
```java
List<ExampleModel> level1 = new ArrayList<>();
List<ExampleModel> level2 = new ArrayList<>();
List<ExampleModel> level3 = new ArrayList<>();
List<ExampleModel> level4 = new ArrayList<>();

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

new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
    .builder(level1)
    .setTitle("Choise Brand")
    //ignore if your menu only have 1 level
    .addSub(
        new DynamicOptionMenu.CallBack<ExampleModel>() {
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
        }
    )
    //add this callback. important line
    .finalCallBack(new DynamicOptionMenu.CallBackFinal<ExampleModel>() {
        @Override
        public void positionItem(ExampleModel data) {
            appent("Level 4_" + data);
        }
    })
    .show();
```

#

- Disable Filter
```java
new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
    ...
    .setEnableFilter(false)
    ...
```

**Preview** :

|<img src="https://github.com/gzeinnumer/EasyOptionMenu/blob/1.0.0/preview/preview_1.gif" width="300"/>|<img src="https://github.com/gzeinnumer/EasyOptionMenu/blob/1.0.0/preview/preview_2.gif" width="300"/>|<img src="https://github.com/gzeinnumer/EasyOptionMenu/blob/1.0.0/preview/preview_3.gif" width="300"/>|
|---|---|---|


---
# Example Code/App

**FullCode [MainActivity](https://github.com/gzeinnumer/EasyOptionMenu/blob/1.0.0/app/src/main/java/com/gzeinnumer/easyoptionmenu/MainActivity.java)**

**[Example App](https://github.com/gzeinnumer/EasyOptionMenuExample)**

---

# Version
- **1.0.0**
  - First Release
- **1.0.1**
  - New Filter Style
- **1.1.0**
  - Enable Filter

---

# Contribution
You can sent your constibution to `branch` `open-pull`.

---

```
Copyright 2021 M. Fadli Zein
```
