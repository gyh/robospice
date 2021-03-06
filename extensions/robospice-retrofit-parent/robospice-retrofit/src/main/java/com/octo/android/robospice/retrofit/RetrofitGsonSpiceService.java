package com.octo.android.robospice.retrofit;

import java.io.File;

import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;
import android.app.Application;

import com.google.gson.Gson;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.retrofit.RetrofitObjectPersisterFactory;

/**
 * A pre-set, easy to use, retrofit service. It will use retrofit for network
 * requests and both networking and caching will use Gson. To use it, just add
 * to your manifest :
 * 
 * <pre>
 * &lt;service
 *   android:name="com.octo.android.robospice.retrofit.RetrofitGsonSpiceService"
 *   android:exported="false" /&gt;
 * </pre>
 * @author SNI
 */
public abstract class RetrofitGsonSpiceService extends RetrofitSpiceService {

    private Converter converter = new GsonConverter(new Gson());

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        cacheManager.addPersister(new RetrofitObjectPersisterFactory(application, converter, getCacheFolder()));
        return cacheManager;
    }

    @Override
    protected Builder createRestAdapterBuilder() {
        RestAdapter.Builder restAdapter = super.createRestAdapterBuilder();
        restAdapter.setConverter(converter);
        return restAdapter;
    }

    public File getCacheFolder() {
        return null;
    }

}
