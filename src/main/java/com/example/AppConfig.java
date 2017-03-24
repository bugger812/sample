package com.example;

import com.example.Services.impl.GeneratorManagerImpl;
import com.example.Services.impl.QuestionManagetImpl;
import com.example.Services.impl.TutorManagerImpl;
import com.example.Services.impl.ExampleServiceImpl;
import com.example.Utils.EmailUtils;
import com.example.Utils.QuestionsUtils;
import com.example.Utils.TemplateUtils;
import com.example.Utils.Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Sigal on 5/16/2016.
 */
@Configuration
//@EnableWebMvc
public class AppConfig {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**").addResourceLocations(
//                "classpath:/static/css/");
//        registry.addResourceHandler("/bootstrap/**").addResourceLocations("classpath:/static/bootstrap/");
//        registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:/static/scripts/");
//    }

    @Bean
    public Utils getUtils() {
        return new Utils();
    }

    @Bean
    public ExampleServiceImpl getUserManager() {
        return new ExampleServiceImpl();
    }

    @Bean
    public QuestionManagetImpl getQuestionManagetImpl() {
        return new QuestionManagetImpl();
    }

    @Bean
    public QuestionsUtils getQuestionUtils() {
        return new QuestionsUtils();
    }

    @Bean
    public TemplateUtils getTemplateUtils() {
        return new TemplateUtils();
    }

    @Bean
    public TutorManagerImpl getTutorManagerImpl() {
        return new TutorManagerImpl();
    }

    @Bean
    public GeneratorManagerImpl getGenetatorManager() {
        return new GeneratorManagerImpl();
    }

    @Bean
    public EmailUtils getEmailUtils() {
        return new EmailUtils();
    }

}
