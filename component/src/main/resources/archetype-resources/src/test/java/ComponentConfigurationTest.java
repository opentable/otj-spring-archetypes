package ${package};
import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration(classes= {
    ComponentConfiguration.class
})
@TestPropertySource(properties= {
    "ot.component.config=foo"
})
@RunWith(SpringRunner.class)
public class ComponentConfigurationTest {
    @Inject
    Component component;

    @Test
    public void testComponent() {
        assertEquals("foo", component.getConfigurable());
    }
}
