# WordFilter

#### Introduction

一个基于DFA实现的轻量级敏感词过滤库

#### Usage

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
instance.loadCustomKeyWords(keyWordList);
```

每个敏感词为一个String，存入List\<String\>

#### Theory

待补充

