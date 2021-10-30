# EasyOptionMenu

![](https://github.com/gzeinnumer/EasyOptionMenu/blob/gradle/preview/preview_1.PNG)
![](https://github.com/gzeinnumer/EasyOptionMenu/blob/gradle/preview/preview_2.PNG)

```gradle
buildscript {
    dependencies {
        //todo 2
        classpath "com.android.tools.build:gradle:4.1.1"

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

//todo 1
allprojects {
    repositories {
        google()
        jcenter()

        maven { url 'https://jitpack.io' }
    }
}
```
```gradle
android {
    //todo 3
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```
- Gradle Setting
```gradle
rootProject.name = "EasyOptionMenu"
include ':app'
include ':eom'
//todo 4
```

---

```
Copyright 2021 M. Fadli Zein
```