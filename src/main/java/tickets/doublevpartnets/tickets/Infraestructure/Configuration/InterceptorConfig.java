package tickets.doublevpartnets.tickets.Infraestructure.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tickets.doublevpartnets.tickets.Infraestructure.Interceptors.RequestLogInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLogInterceptor logInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor)
                .addPathPatterns("/api/v1/**"); // Todos los End Points a Interceptar de la version 1 de las URL
    }
    
}
