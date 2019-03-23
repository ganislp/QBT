package co.za.quickbuyticketcomponent;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;
import java.util.Locale;

@SpringBootApplication
@ComponentScan(
        basePackages = {"co.za.quickbuyticketcomponent.*"})
@EnableScheduling
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Autowired
    private MessageSource messageSource;



    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }



    public static void main(String[] arguments) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setAdditionalProfiles(arguments);
        application.run();


    }






}