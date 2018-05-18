import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Configuration
@ContextConfiguration(classes = Config.class)
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
}
