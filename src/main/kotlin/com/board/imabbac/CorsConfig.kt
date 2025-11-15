package com.board.imabbac

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

//CORS : 브라우저의 동일 출처 정책(SOP) 때문에, 다른 출처의 서버로 요청을 보낼 때
// 서버가 적절한 허용 헤더를 주지 않으면 브라우저가 응답을 막아버리는 현상임.
//웹에서는 cors 문제가 생길 수도 있어서 방지하기 위해 만들어여함.
@Configuration
class CorsConfig {
    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**") // 외부에서 받는 모든 URL
                    .allowedOrigins("http://localhost:3000") // 허용할 origin(프론트 url)
                    .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                    .allowedHeaders("*") //프론트 url에서 오는 요청값이나 헤더값을 다 허용해서 cors막음
                    .allowCredentials(true)
            }
        }
    }
}
