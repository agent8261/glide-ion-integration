package com.gphsopper.ionglider;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.widget.ProgressBar;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.http.body.Part;
import com.koushikdutta.ion.HeadersCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.koushikdutta.ion.builder.Builders;
import com.koushikdutta.ion.builder.LoadBuilder;


import org.apache.http.NameValuePair;
import org.w3c.dom.Document;

import java.io.File;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

/**
 * 
 * Example usage:
 *   IonGlider.with(context)
 *   .load(IMAGE_URL)
 *   .progressBar(progressBar)
 *   .glide()    // after this no more "Ion" calls can be made
 *   .placeholder(R.drawable.image_placeholder)
 *   .into(imageView);
 * 
 * Created by Darren on 2/23/15.
 */
public class IonGlider <I extends Builders.Any.B & Builders.Any.F & Builders.Any.M & Builders.Any.U & LoadBuilder<Builders.Any.B>> {    

    public final I loadBuilder;
    
    final private WeakReference<Context> contextRef;
    final private Context appContext;
    private GlideUrl glideUrl;
    
    //
    public static final IonGlider with(Context context){
        return new IonGlider(context);   
    }
    
    //
    public static final IonGlider with(Fragment fragment){
        return new IonGlider(fragment);
    }
    
    //
    public static final IonGlider with(android.support.v4.app.Fragment fragment){
        return new IonGlider(fragment);
    }  
    
    //
    public IonGlider(final Context context){
        if(context == null){
            throw new IllegalArgumentException("context can not be null");
        }
        contextRef = new WeakReference<Context>(context);
        appContext  = context.getApplicationContext();
        loadBuilder = (I) Ion.with(context);
    }

    //
    public IonGlider(Fragment fragment){
        if(fragment == null){
            throw new IllegalArgumentException("fragment can not be null");
        }
        Context context = fragment.getActivity();
        contextRef = new WeakReference<Context>(context);
        appContext  = context.getApplicationContext();
        loadBuilder = (I) Ion.with(fragment);
    }
    
    //
    public IonGlider(android.support.v4.app.Fragment fragment){
        if(fragment == null){
            throw new IllegalArgumentException("fragment can not be null");
        }
        Context context = fragment.getActivity();
        contextRef = new WeakReference<Context>(context);
        appContext  = context.getApplicationContext();
        loadBuilder = (I) Ion.with(fragment);
    }  
    

    /**
     * Call once done using any of the ion methods exposed by this class
     * Equivalent to doing Glide.with().load(url)
     * @return
     */
    public DrawableTypeRequest<String> glide(){
        Context context = contextRef.get();
        if(context == null){
            context = appContext;  // probably not necessary but ensures safety -- Darren
        }
        String url = glideUrl.toString();
        IonUrlLoader.addIonRequest(url, loadBuilder);
        return Glide.with(context).load(url);
    }

    
    //
    public IonGlider load(String uri) {
        glideUrl = new GlideUrl(uri);
        loadBuilder.load(uri);
        return this;
    }

    //
    public IonGlider load(String method, String url) {
        glideUrl = new GlideUrl(url);
        loadBuilder.load(method, url);
        return this;
    }

    //
    public IonGlider setLogging(String tag, int level) {
        loadBuilder.setLogging(tag, level);
        return this;
    }

    //
    public IonGlider proxy(String host, int port) {
        loadBuilder.proxy(host, port);
        return this;
    }

    //
    public IonGlider progress(ProgressCallback callback) {
        loadBuilder.progress(callback);
        return this;
    }

    //
    public IonGlider progressHandler(ProgressCallback callback) {
        loadBuilder.progressHandler(callback);
        return this;
    }

    //
    public IonGlider progressBar(ProgressBar progressBar) {
        loadBuilder.progressBar(progressBar);
        return this;
    }

    //
    public IonGlider progressDialog(ProgressDialog progressDialog) {
        loadBuilder.progressDialog(progressDialog);
        return this;
    }

    //
    public IonGlider uploadProgress(ProgressCallback callback) {
        loadBuilder.uploadProgress(callback);
        return this;
    }

    //
    public IonGlider uploadProgressHandler(ProgressCallback callback) {
        loadBuilder.uploadProgressHandler(callback);
        return this;
    }

    //
    public IonGlider uploadProgressBar(ProgressBar progressBar) {
        loadBuilder.uploadProgressBar(progressBar);
        return this;
    }

    //
    public IonGlider uploadProgressDialog(ProgressDialog progressDialog) {
        loadBuilder.progressDialog(progressDialog);
        return this;
    }

    //
    public IonGlider setHandler(Handler handler) {
        loadBuilder.setHandler(handler);
        return this;
    }

    //
    public IonGlider setHeader(String name, String value) {
        loadBuilder.setHeader(name, value);
        return this;
    }

    //
    public IonGlider setHeader(NameValuePair... header) {
        loadBuilder.setHeader(header);
        return this;
    }

    //
    public IonGlider noCache() {
        loadBuilder.noCache();
        return this;
    }

    //
    public IonGlider followRedirect(boolean follow) {
        loadBuilder.followRedirect(follow);
        return this;
    }

    //
    public IonGlider addHeader(String name, String value) {
        loadBuilder.addHeader(name, value);
        return this;
    }

    //
    public IonGlider addHeaders(Map<String, List<String>> params) {
        loadBuilder.addHeaders(params);
        return this;
    }

    //
    public IonGlider addQuery(String name, String value) {
        loadBuilder.addQuery(name, value);
        return this;
    }

    //
    public IonGlider addQueries(Map<String, List<String>> params) {
        loadBuilder.addQueries(params);
        return this;
    }

    //
    public IonGlider userAgent(String userAgent) {
        loadBuilder.userAgent(userAgent);
        return this;
    }

    //
    public IonGlider setTimeout(int timeoutMilliseconds) {
        loadBuilder.setTimeout(timeoutMilliseconds);
        return this;
    }

    //
    public IonGlider onHeaders(HeadersCallback callback) {
        loadBuilder.onHeaders(callback);
        return this;
    }

    //
    public IonGlider basicAuthentication(String username, String password) {
        loadBuilder.basicAuthentication(username, password);
        return this;
    }

    //
    public IonGlider setJsonObjectBody(JsonObject jsonObject) {
        loadBuilder.setJsonObjectBody(jsonObject);
        return this;
    }

    //
    public <T> IonGlider setJsonPojoBody(T object, TypeToken<T> token) {
        loadBuilder.setJsonPojoBody(object, token);
        return this;
    }

    public <T> IonGlider setJsonPojoBody(T object) {
        loadBuilder.setJsonPojoBody(object);
        return this;
    }

    //
    public IonGlider setJsonArrayBody(JsonArray jsonArray) {
        loadBuilder.setJsonArrayBody(jsonArray);
        return this;
    }

    //
    public IonGlider setStringBody(String string) {
        loadBuilder.setStringBody(string);
        return this;
    }

    //
    public IonGlider setDocumentBody(Document document) {
        setDocumentBody(document);
        return this;
    }

    //
    public IonGlider setFileBody(File file) {
        loadBuilder.setFileBody(file);
        return this;
    }

    //
    public IonGlider setByteArrayBody(byte[] bytes) {
        loadBuilder.setByteArrayBody(bytes);
        return this;
    }

    //
    public IonGlider setStreamBody(InputStream inputStream) {
        loadBuilder.setStreamBody(inputStream);
        return this;
    }

    //
    public IonGlider setStreamBody(InputStream inputStream, int length) {
        loadBuilder.setStreamBody(inputStream, length);
        return this;
    }

    //
    public IonGlider setMultipartParameter(String name, String value) {
        loadBuilder.setMultipartParameter(name, value);
        return this;
    }

    //
    public IonGlider setMultipartParameters(Map<String, List<String>> params) {
        loadBuilder.setMultipartParameters(params);
        return this;
    }

    //
    public IonGlider setMultipartFile(String name, File file) {
        loadBuilder.setMultipartFile(name, file);
        return this;
    }

    //
    public IonGlider setMultipartFile(String name, String contentType, File file) {
        loadBuilder.setMultipartFile(name, contentType, file);
        return this;
    }

    //
    public IonGlider addMultipartParts(Iterable<Part> parameters) {
        loadBuilder.addMultipartParts(parameters);
        return this;
    }

    //
    public IonGlider addMultipartParts(Part... parameters) {
        loadBuilder.addMultipartParts(parameters);
        return this;
    }

    //
    public IonGlider setMultipartContentType(String contentType) {
        loadBuilder.setMultipartContentType(contentType);
        return this;
    }

    //
    public IonGlider setBodyParameter(String name, String value) {
        loadBuilder.setBodyParameter(name, value);
        return this;
    }

    //
    public IonGlider setBodyParameters(Map<String, List<String>> params) {
        loadBuilder.setBodyParameters(params);
        return this;
    }
    
}
