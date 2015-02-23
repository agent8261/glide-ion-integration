package com.gphsopper.ionglider;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Created by robinsda on 2/2/15.
 */
public class IonGlideModule implements GlideModule {
    
    
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // does nothing
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        glide.register(GlideUrl.class, InputStream.class, new IonUrlLoader.Factory());
    }
}
