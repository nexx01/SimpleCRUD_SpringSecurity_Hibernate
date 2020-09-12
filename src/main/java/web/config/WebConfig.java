
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
    }



                                //i18n
    //Чтобы наше приложение могло определить, какая локаль используется в данный момент,
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




/*Если ресурсы по стандартному пути то резольверы
 * переопределять не нужно.*/

/*
    private final ApplicationContext applicationContext;

    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    //объявление статических ресурсов
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    //Активизирует обработчик статических ресурсов.
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }

    //Одна из реализаций ITemplateResolver resolves templates using Spring's Resource Resolution mechanism
    //(see ResourceLoader.getResource(String))
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }*/
/*
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }*/

//Configure view resolvers to translate String-based view names returned from
//   controllers into concrete View implementations to perform rendering with.
/*    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
        ThymeleafViewResolver viewResolver;
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("text/html; charset=UTF-8");
}

*/


//Папка где находятся ресурсы для i18n
/*
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("WEB-INF/i18n/message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }




*/



