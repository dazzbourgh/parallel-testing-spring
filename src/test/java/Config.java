import org.junit.rules.ExternalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.SimpleThreadScope;

@Configuration
public class Config {
    @Bean
    @Scope("prototype")
    public SharedResource sharedResource() {
        return new SharedResource();
    }

    @Bean
    @Scope("simpleThread")
    @Autowired
    public ExternalResource webDriver(SharedResource sharedResource) {
        return new ExternalResource() {
            @Override
            protected void before() {
                //different sharedResource instances here because of "prototype" scope
                System.out.println("\n\n\nOpening session with " + sharedResource + "\n\n\n");
            }

            @Override
            protected void after() {
                System.out.println("\n\n\nMaybe some teardown logic here\n\n\n");
            }
        };
    }

    @Bean
    public BeanFactoryPostProcessor beanFactoryPostProcessor() {
        return beanFactory -> beanFactory.registerScope("simpleThread", new SimpleThreadScope());
    }
}
