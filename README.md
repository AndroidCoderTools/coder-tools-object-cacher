# What is Object cache ?
We can save Objects or some parameter in memory and local cache, this project meets this issue. And
we can load it when we want to use. 
# How to use ?
gradle config:
```gradle
    compile 'coder.tools.sdk:coder-tools-object-cacher-lib:1.0.5'
```

```java
    LocalCache = new LocalCache();
```
Or

```java
    LocalCache = new LocalCache(cachePath);
```

And save and get:

```java
    String key = System.currentTimeMillis()+"1";
    localCache.putAndSave(this, key, "String:" + System.currentTimeMillis());
    String vS = (String)localCache.getInner(context,key);
```
