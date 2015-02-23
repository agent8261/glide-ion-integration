package com.gphsopper.ionglider;

import android.content.Context;


import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;

import com.koushikdutta.ion.builder.FutureBuilder;

import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.TreeMap;

/**
 * Created by robinsda on 2/2/15.
 */
public class IonUrlLoader implements ModelLoader<GlideUrl, InputStream> {

    private static final TreeMap<String, SoftReference<FutureBuilder>> ionRequestMap = new TreeMap();
    
    //
    public IonUrlLoader() {
    }
    
    @Override
    public DataFetcher<InputStream> getResourceFetcher(GlideUrl model, int width, int height) {
        FutureBuilder ionRequestBuilder = null;
        synchronized (ionRequestMap){
            String key = model.toString();
            SoftReference<FutureBuilder> reqRef = ionRequestMap.get(key);
            ionRequestBuilder = reqRef.get();
            ionRequestMap.remove(key);
        }
        return new IonStreamFetcher(model, ionRequestBuilder);
    }
    
    //
    public static void addIonRequest(String url, FutureBuilder ionRequestBuilder){
        synchronized (ionRequestMap){
            ionRequestMap.put(url, new SoftReference<FutureBuilder>(ionRequestBuilder));
        }
    }
    
    //
    public static class Factory implements ModelLoaderFactory<GlideUrl, InputStream> {
        
        //
        public Factory() {
        }
        
        @Override
        public ModelLoader<GlideUrl, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new IonUrlLoader();
        }

        @Override
        public void teardown() {
        }
    }
}
