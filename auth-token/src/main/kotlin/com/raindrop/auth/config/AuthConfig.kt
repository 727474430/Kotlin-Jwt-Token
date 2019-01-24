package com.raindrop.auth.config

import com.raindrop.auth.interceptors.AuthInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AuthConfig : WebMvcConfigurer {

    /**
     * 添加拦截器
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        var excludePath = listOf("/auth/login")
        registry.addInterceptor(AuthInterceptor()).excludePathPatterns(excludePath)
    }

}