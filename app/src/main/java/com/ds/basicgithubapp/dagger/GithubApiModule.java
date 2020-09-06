package com.ds.basicgithubapp.dagger;

import com.ds.basicgithubapp.repo.api.retrofit.GithubRepoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ds.basicgithubapp.repo.api.retrofit.RetrofitClientInstance.BASE_URL;

@Module
public class GithubApiModule {

//    @Provides
//    @Singleton
//    Gson provideGson() {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        return gsonBuilder.create();
//    }


//    /*
//     * The method returns the Cache object
//     * */
//    @Provides
//    @Singleton
//    Cache provideCache(Application application) {
//        long cacheSize = 10 * 1024 * 1024; // 10 MB
//        File httpCacheDirectory = new File(application.getCacheDir(), "http-cache");
//        return new Cache(httpCacheDirectory, cacheSize);
//    }
//
//
//    /*
//     * The method returns the Okhttp object
//     * */
//    @Provides
//    @Singleton
//    OkHttpClient provideOkhttpClient(Cache cache) {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//        httpClient.cache(cache);
//        httpClient.addInterceptor(logging);
//        httpClient.addNetworkInterceptor(new RequestInterceptor());
//        httpClient.connectTimeout(30, TimeUnit.SECONDS);
//        httpClient.readTimeout(30, TimeUnit.SECONDS);
//        return httpClient.build();
//    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    GithubRepoService provideGithubRepoService (Retrofit retrofit) {
        return retrofit.create(GithubRepoService.class);
    }

}
