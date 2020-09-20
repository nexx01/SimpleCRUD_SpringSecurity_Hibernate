
package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/").setViewName("home");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");//указываем что ресурсы ищем в дереве файлов

    }

    //i18n
    //Чтобы  приложение могло определить, какая локаль используется в данный момент,
    //имеет реализации, определяющие текущую локаль на основе сеанса, файлов cookie,
    // заголовка Accept-Language или фиксированного значения
    @Bean
    public LocaleResolver localeResolver() {
        //slr.setDefaultLocale(Locale.ENGLISH);
        return new CookieLocaleResolver();
    }

    /*Bean-перехватчик, который переключится
    на новую локаль на основе значения параметра lang, добавленного к запросу*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    /*Чтобы вступить в силу, этот боб должен быть
    добавлен в реестр перехватчиков приложения.*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}



