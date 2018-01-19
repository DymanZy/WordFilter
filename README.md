# WordFilter

#### Introduction

一个基于DFA实现的轻量级敏感词过滤库

#### Usage

1.在根目录下的build.gradle添加：

```java
allprojects {
  repositories {
    ...
    maven { url 'https://www.jitpack.io' }
  }
}
```

2.添加依赖

```java
dependencies {
  compile 'com.github.DymanZy:WordFilter:1.0'
}
```

3.使用

```java
FilterManager instance = FilterManager.getInstance(mContext, FilterManager.Type.BASE);

instance.loadDefaultKeyWords();

String text = "待检测的语句";
instance.findFirst(text);
instance.findAll(text);
instance.containsAny(text);
instance.replace(text);
instance.replace(text, '*');
```

如果你想使用自己的敏感词库，请使用

```java
instance.loadCustomKeyWords(yourkeyWordList);
```

