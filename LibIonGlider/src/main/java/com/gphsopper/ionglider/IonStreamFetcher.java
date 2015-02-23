package com.gphsopper.ionglider;

import android.util.Log;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.model.GlideUrl;
import com.koushikdutta.ion.builder.FutureBuilder;
import com.koushikdutta.ion.builder.RequestBuilder;
import com.koushikdutta.ion.future.ResponseFuture;

import java.io.InputStream;

/**
 * Created by robinsda on 2/2/15.
 */
public class IonStreamFetcher implements DataFetcher<InputStream> {

    private final GlideUrl url;
    private final FutureBuilder ionRequestBuilder;
    private ResponseFuture<InputStream> future = null;
    
    //
    public IonStreamFetcher(GlideUrl url, FutureBuilder ionRequestBuilder){
        this.ionRequestBuilder = ionRequestBuilder;
        this.url = url;    
    }
    
    @Override
    public InputStream loadData(Priority priority) throws Exception {
        Log.e("IonStreamFetcher", "loading Ion Data");
        future = ionRequestBuilder.asInputStream();
        return future.get();
    }

    @Override
    public void cleanup() {

    }

    @Override
    public String getId() {
        return url.toString();
    }

    @Override
    public void cancel() {
        if(future != null) {
            future.cancel();
        }
    }
}
