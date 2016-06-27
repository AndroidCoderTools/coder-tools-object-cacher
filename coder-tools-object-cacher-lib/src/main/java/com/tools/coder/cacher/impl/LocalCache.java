package com.tools.coder.cacher.impl;

import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

import com.tools.coder.cacher.util.ByteUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Cache data in memory and save the encrypt data on disk
 * Created by Spring-Xu on 15/12/29.
 */
public class LocalCache extends BaseCache {

    protected static final String TYPE_STRING = "String";
    protected static final String TYPE_INT = "int";
    protected static final String TYPE_LONG = "long";
    protected static final String TYPE_CHAR = "char";
    protected static final String TYPE_DOUBLE = "double";
    protected static final String TYPE_FLOAT = "float";
    protected static final String TYPE_SHORT = "short";

    public LocalCache() {
        super();
    }

    /**
     * initialize cache with cache path
     *
     * @param category
     */
    public LocalCache(String category) {
        super(category);
    }

    /**
     * 缓存
     *
     * @param context
     * @param key
     * @param value
     */
    public void putAndSave(Context context, String key, Parcelable value) {
        if (key != null && value != null) {
            put(key, value);
            save(context, key, ByteUtils.getBytes(value), value.getClass().getName());
        }
    }

    /**
     * 有效获取缓存项
     *
     * @param key
     * @return
     */
    protected <T extends Object> T getParcelable(Context context, String key, Parcelable.Creator<T> creator) {
        Object value = get(key);
        if (value != null) {
            return (T) value;
        }
        DiskCache.DEntry entry = load(context, key);
        try {
            if (entry != null && entry.data != null) {
                value = ByteUtils.getParcelable(entry.data, creator);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (T) value;
    }

    /**
     * 有效获取缓存项
     *
     * @param key
     * @return
     */
    protected long getLong(DiskCache.DEntry entry, String key) {
        Object value = get(key);
        if (value != null) {
            return (long) value;
        }
        if (entry != null && entry.data != null) {
            value = ByteUtils.getLong(entry.data);
        }

        return (long) value;
    }

    /**
     * 缓存
     *
     * @param context
     * @param key
     * @param value
     */
    public void putAndSave(Context context, String key, long value) {
        if (key != null) {
            put(key, value);
            save(context, key, ByteUtils.getBytes(value), TYPE_LONG);
        }
    }

    /**
     * 有效获取缓存项
     *
     * @param key
     * @return
     */
    protected short getShort(DiskCache.DEntry entry, String key) {
        Object value = get(key);
        if (value != null) {
            return (short) value;
        }
        if (entry != null && entry.data != null) {
            value = ByteUtils.getShort(entry.data);
        }

        return (short) value;
    }

    /**
     * 缓存
     *
     * @param context
     * @param key
     * @param value
     */
    public void putAndSave(Context context, String key, short value) {
        if (key != null) {
            put(key, value);
            save(context, key, ByteUtils.getBytes(value), TYPE_SHORT);
        }
    }

    /**
     * 有效获取缓存项
     *
     * @param key
     * @return
     */
    protected char getChar(DiskCache.DEntry entry, String key) {
        Object value = get(key);
        if (value != null) {
            return (char) value;
        }
        if (entry != null && entry.data != null) {
            value = ByteUtils.getChar(entry.data);
        }

        return (char) value;
    }

    /**
     * 缓存
     *
     * @param context
     * @param key
     * @param value
     */
    public void putAndSave(Context context, String key, char value) {
        if (key != null) {
            put(key, value);
            save(context, key, ByteUtils.getBytes(value), TYPE_CHAR);
        }
    }

    /**
     * 有效获取缓存项
     *
     * @param key
     * @return
     */
    protected double getDouble(DiskCache.DEntry entry, String key) {
        Object value = get(key);
        if (value != null) {
            return (double) value;
        }
        if (entry != null && entry.data != null) {
            value = ByteUtils.getDouble(entry.data);
        }

        return (double) value;
    }

    /**
     * 缓存
     *
     * @param context
     * @param key
     * @param value
     */
    public void putAndSave(Context context, String key, double value) {
        if (key != null) {
            put(key, value);
            save(context, key, ByteUtils.getBytes(value), TYPE_DOUBLE);
        }
    }

    /**
     * 缓存
     *
     * @param context
     * @param key
     * @param value
     */
    public void putAndSave(Context context, String key, int value) {
        if (key != null) {
            put(key, value);
            save(context, key, ByteUtils.getBytes(value), TYPE_INT);
        }
    }

    /**
     * 有效获取缓存项
     *
     * @param key
     * @return
     */
    protected int getInteger(DiskCache.DEntry entry, String key) {
        Object value = get(key);
        if (value != null) {
            return (int) value;
        }
        if (entry != null && entry.data != null) {
            value = ByteUtils.getInt(entry.data);
        }

        return value == null ? 0 : (int) value;
    }

    /**
     * 缓存
     *
     * @param context
     * @param key
     * @param value
     */
    public void putAndSave(Context context, String key, float value) {
        if (key != null) {
            put(key, value);
            save(context, key, ByteUtils.getBytes(value), TYPE_FLOAT);
        }
    }

    /**
     * 有效获取缓存项
     *
     * @param key
     * @return
     */
    protected float getFloat(DiskCache.DEntry entry, String key) {
        Object value = get(key);
        if (value != null) {
            return (float) value;
        }
        if (entry != null && entry.data != null) {
            value = ByteUtils.getFloat(entry.data);
        }

        return value == null ? 0 : (float) value;
    }

    /**
     * 缓存
     *
     * @param context
     * @param key
     * @param value
     */
    public void putAndSave(Context context, String key, Serializable value) {
        if (key != null) {
            put(key, value);
            save(context, key, ByteUtils.getBytes(value), value.getClass().getName());
        }
    }

    /**
     * 有效获取缓存项
     *
     * @param key
     * @return
     */
    protected Object getSerializable(Context context, String key) {
        Object value = get(key);
        if (value != null) {
            return value;
        }
        DiskCache.DEntry entry = load(context, key);
        if (entry != null && entry.data != null) {
            value = ByteUtils.getSerializable(entry.data);
        }

        return value;
    }

    /**
     * 缓存
     *
     * @param context
     * @param key
     * @param value
     */
    public void putAndSave(Context context, String key, String value) {
        if (key != null && value != null) {
            put(key, value);
            save(context, key, value.getBytes(), TYPE_STRING);
        }
    }

    /**
     * 有效获取缓存项
     *
     * @param key
     * @return
     */
    protected String getString(DiskCache.DEntry entry, String key) {
        Object value = get(key);
        if (value != null) {
            return (String) value;
        }
        if (entry != null && entry.data != null) {
            value = ByteUtils.getString(entry.data);
        }

        return (String) value;
    }

    public <T> Object getInner(Context context, String key) {
        Object value = get(key);
        if (value != null) {
            return value;
        }

        DiskCache.DEntry entry = load(context, key);
        String type = entry.etag;
        if (TextUtils.isEmpty(type)) {
            Log.e("", "key=" + key + "  error:type is null");
            return null;
        }

        do {
            if (TYPE_CHAR.equals(type)) {
                value = getChar(entry, key);
                break;
            }

            if (TYPE_DOUBLE.equals(type)) {
                value = getDouble(entry, key);
                break;
            }

            if (TYPE_FLOAT.equals(type)) {
                value = getFloat(entry, key);
                break;
            }

            if (TYPE_INT.equals(type)) {
                value = getInteger(entry, key);
                break;
            }

            if (TYPE_LONG.equals(type)) {
                value = getLong(entry, key);
                break;
            }

            if (TYPE_STRING.equals(type)) {
                value = getString(entry, key);
                break;
            }

            if (TYPE_SHORT.equals(type)) {
                value = getShort(entry, key);
                break;
            }

            try {
                Class ss = Class.forName(type);
                if (Serializable.class.isAssignableFrom(ss)) {
                    value = getSerializable(context, key);
                    break;
                }

                if (Parcelable.class.isAssignableFrom(ss)) {
                    //create creator;
                    Parcelable.Creator<T> creator;
                    Parcelable parcelable = (Parcelable) ss.newInstance();
                    Field field = ss.getDeclaredField("CREATOR");
                    if (field == null) {
                        return null;

                    } else {
                        field.setAccessible(true);
                        creator = (Parcelable.Creator<T>) field.get(ss.newInstance());
                    }

                    value = getParcelable(context, key, creator);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (false);

        if (value != null) {
            put(key, value);
        }

        return value;
    }

    public List<String> getKeyList(Context context) {
        return super.getKeyList(context);
    }
}