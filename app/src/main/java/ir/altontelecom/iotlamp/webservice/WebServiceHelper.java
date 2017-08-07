package ir.altontelecom.iotlamp.webservice;

import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ir.altontelecom.iotlamp.webservice.WebServiceEndpoints.BASE_URL;

public class WebServiceHelper {
    private static Gson sGson;
    private static Interceptor sHeaderInterceptor;
    private static Interceptor sLoggerInterceptor;
    private static OkHttpClient sOkHttpClient;
    private static Retrofit sRetrofit;
    private static WebServiceMethods sWebServiceMethods;

    private static Gson getGson() {
        if (sGson == null) {
            sGson = new Gson();
        }
        return sGson;
    }

    private static Interceptor getHeaderInterceptor() {
        if (sHeaderInterceptor == null) {
            sHeaderInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("Accept", "application/json")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            };
        }
        return sHeaderInterceptor;
    }

    private static Interceptor getLoggerInterceptor() {
        if (sLoggerInterceptor == null) {
            sLoggerInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return sLoggerInterceptor;
    }

    private static OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            sOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(getHeaderInterceptor())
                    .addInterceptor(getLoggerInterceptor())
                    .build();
        }
        return sOkHttpClient;
    }

    private static Retrofit getRetrofit() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .client(getOkHttpClient())
                    .baseUrl(BASE_URL)
                    .build();
        }
        return sRetrofit;
    }

    public static WebServiceMethods getWebServiceMethods() {
        if (sWebServiceMethods == null) {
            sWebServiceMethods = getRetrofit().create(WebServiceMethods.class);
        }
        return sWebServiceMethods;
    }


}