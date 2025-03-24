package com.starwars.starwars.config;

import feign.*;
import feign.codec.ErrorDecoder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@Configuration
public class FiegnConfiguration {

    @Bean
    public Feign.Builder feignBuilder() {
        return Feign.builder()
                .client(new Client.Default(getSSLSocketFactory(), new NoopHostnameVerifier()));

    }

    private SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext context = SSLContext.getInstance("TLS");
            TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};
            context.init(null, trustManagers, new SecureRandom());
            return context.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
