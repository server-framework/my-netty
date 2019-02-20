package com.mjump.game.ws;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author
 * @date 2018/3/9
 */
@SpringBootApplication
@EnableScheduling
@Configuration
@ComponentScan(basePackages = {"com.mjump.game.ws"})
public class MemberWsApplication {

    private static Logger logger = LoggerFactory.getLogger(MemberWsApplication.class);
    public static void main(String[] args) {
        logger.info("netty is starting");

        SpringApplication app = new SpringApplication(MemberWsApplication.class);
        app.setWebEnvironment(false);
        app.run(args);
        logger.info("netty is started");
    }
    
    /**
     * 参数校验
     * @return
     * @author jiangxinjun
     * @createDate 2018年3月29日
     * @updateDate 2018年3月29日
     */
    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        return validator;
    }
}
