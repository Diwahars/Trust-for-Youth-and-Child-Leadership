package com.nkana.app.network;

import com.nkana.app.model.Login;
import com.squareup.okhttp.OkHttpClient;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by chokkar
 */

public class RestClient {

    private static Restapi REST_CLIENT;
    private static String ROOT =
            "https://52.22.24.182/api/v1/";

   /* static {
        setupRestClient();
    }
*/
    public static Restapi get() {
        return REST_CLIENT;
    }

    public static void setupRestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ROOT)
                .setClient(new OkClient(getUnsafeOkHttpClient()))
                .setRequestInterceptor(new SessionRequestInterceptor())
                .build();

        REST_CLIENT = restAdapter.create(Restapi.class);
    }

    public static void setupLoginRestClient(Login user) {
        ApiRequestInterceptor requestInterceptor = new ApiRequestInterceptor();
        requestInterceptor.setUser(user);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(ROOT)
                .setClient(new OkClient(getUnsafeOkHttpClient()))
                .setRequestInterceptor(requestInterceptor)
                .build();

        REST_CLIENT = restAdapter.create(Restapi.class);
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setSslSocketFactory(sslSocketFactory);
            okHttpClient.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}