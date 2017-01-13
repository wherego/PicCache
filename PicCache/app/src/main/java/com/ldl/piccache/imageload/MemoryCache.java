package com.ldl.piccache.imageload;


import android.graphics.Bitmap;
import android.support.annotation.StringDef;
import android.support.v4.util.LruCache;

/**
 * Created by ${ldl} on 2017/1/13.
 * 内存缓存工具类
 */

public class MemoryCache{
private static LruCache<String,Bitmap> mLrucache;
    static {
        int mazSize=(int)Runtime.getRuntime().maxMemory()/1024;
        mLrucache=new LruCache<String,Bitmap>(mazSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            }
        };
    }
    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mLrucache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mLrucache.get(key);
    }
}
