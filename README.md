<p align="center">
  <img src="https://dafunda.com/wp-content/uploads/2019/10/Aplikasi-sering-force-close-min.jpg"/>
</p>

<h1 align="center">
EasyOptionMenu
</h1>

<div align="center">
    <a><img src="https://img.shields.io/badge/Version-1.0.0-brightgreen.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/ID-gzeinnumer-blue.svg?style=flat"></a>
    <a><img src="https://img.shields.io/badge/Java-Suport-green?logo=java&style=flat"></a>
    <a><img src="https://img.shields.io/badge/Kotlin-Suport-green?logo=kotlin&style=flat"></a>
    <a href="https://github.com/gzeinnumer"><img src="https://img.shields.io/github/followers/gzeinnumer?label=follow&style=social"></a>
    <br>
    <p>Dynamic Option Menu With Multi Level</p>
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
Add maven `jitpack.io` and `dependencies` in build.gradle (Project) :
```gradle
// build.gradle project
allprojects {
  repositories {
    google()
    jcenter()
    maven { url 'https://jitpack.io' }
  }
}

// build.gradle app/module
dependencies {
  ...
  implementation 'com.github.gzeinnumer:EasyOptionMenu:versi'
}
```

---
# Feature List
- [x] Single Level Options
- [x] Multi Level Options

---
# Tech stack and 3rd library
- DialogFragment ([docs](https://developer.android.com/guide/fragments/dialogs))

---
# Usage

#### Make Model
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

#### Single Level Options

- Declare Dummy List
```java
List<ExampleModel> level1 = new ArrayList<>();
level1.add(new ExampleModel(1, "Title 1", "Address 1"));
level1.add(new ExampleModel(2, "Title 2", "Address 2"));
level1.add(new ExampleModel(3, "Title 3", "Address 3"));
level1.add(new ExampleModel(4, "Title 4", "Address 4"));
level1.add(new ExampleModel(5, "Title 5", "Address 5"));
```

- Show OptionMenu
```java
new DynamicOptionMenuBuilder<ExampleModel>(getSupportFragmentManager())
    .builder(level1)
    .setTitle("Pick One")
    //add this callback. important line
    .finalCallBack(new DynamicOptionMenu.CallBackFinal<ExampleModel>() {
        @Override
        public void positionItem(ExampleModel data) {
            appent("Level 1_" + data);
        }
    })
    .show();
```
#
#### 2 Level Options

- Declare Dummy List

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
```

- Show OptionMenu
```java
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
            appent("Level 4_" + data);
        }
    }).show();
```
#
#### More Than 2 Level Options

- Declare Dummy List

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
```

- Show OptionMenu
```java
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
    }).show();
```
#
#### Preview

|![]()|
|---|

---
## Example Code/App

**FullCode [MainActivity](https://github.com/gzeinnumer/EasyOptionMenu/blob/master/app/src/main/java/com/gzeinnumer/easyoptionmenu/MainActivity.java)**

---

## Version
- **1.0.0**
  - First Release

---

## Contribution
You can sent your constibution to `branch` `open-pull`.

---

```
Copyright 2021 M. Fadli Zein
```
