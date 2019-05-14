package fb.fandroid.adv.rxjava2albumapp;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fb.fandroid.adv.rxjava2userauthapp.model.User;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marat.taychinov
 */

public class ApiUtils {
    private static OkHttpClient client;
    private static Retrofit retrofit;
    private static Gson gson;
    private static AcademyApi api;

    public static OkHttpClient getBasicAuthClient(final String email, final String password, boolean createNewInstance) {
        if (createNewInstance || client == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            builder.authenticator((route, response) -> {
                String credential = Credentials.basic(email, password);
                return response.request().newBuilder().header("Authorization", credential).build();

            });
            if (!BuildConfig.BUILD_TYPE.contains("release")) {
                builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
            }

            client = builder.build();
        }
        return client;
    }

    public static Retrofit getRetrofit() {
        if (gson == null) {
            gson = new Gson();
        }
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.SERVER_URL)
                    // need for interceptors
                    .client(getBasicAuthClient("", "", false))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                     .build();
        }
        return retrofit;
    }

    public static AcademyApi getApiService() {
        if (api == null) {
            api = getRetrofit().create(AcademyApi.class);
        }
        return api;
    }

    public static AcademyApi getApiService(String email, String password, boolean createNewInstance) {
        if (createNewInstance || api == null) {


            api = rebuildRetrofit(getBasicAuthClient(
                    email,
                    password,
                    true))
                    .create(AcademyApi.class);
        }
        return api;
    }

    private static GsonConverterFactory buildUserGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Adding custom deserializer
        gsonBuilder.registerTypeAdapter(User.class, new UserDeserializer());
        Gson myGson = gsonBuilder.create();

        return GsonConverterFactory.create(myGson);
    }

    public static Retrofit rebuildRetrofit(OkHttpClient client) {
        if (gson == null) {
            gson = new Gson();
        }

        retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                // need for interceptors
                .client(client)
                .addConverterFactory(buildUserGsonConverter())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //строка ниже нужна чтобы избежать ошибки Exeption
                //Unable to create call adapter for io.reactivex.Single
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }

}











