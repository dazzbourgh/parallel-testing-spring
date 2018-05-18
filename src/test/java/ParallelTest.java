import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Configuration
@ContextConfiguration(classes = ParallelTest.class)
public class ParallelTest {
    @Rule
    @Autowired
    //must be public
    public ExternalResource externalResource;

    @Test
    public void firstTest() {
        System.out.println("\n\n\nTest one started\n\n\n");
    }

    @Test
    public void secondTest() {
        System.out.println("\n\n\nTest two\n\n\n");
    }

    @Bean
    @Scope("prototype")
    public SharedResource sharedResource() {
        return new SharedResource();
    }

    @Bean
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
}
