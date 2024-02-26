package com.part3.msplus.global.config;

import com.part3.msplus.oauth2.infra.Oauth2GoogleProperty;
import com.part3.msplus.oauth2.infra.Oauth2KakaoProperty;
import com.part3.msplus.oauth2.infra.Oauth2LocalProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
    Oauth2KakaoProperty.class,
    Oauth2GoogleProperty.class,
    Oauth2LocalProperty.class,
})
public class PropertyConfig {
}
