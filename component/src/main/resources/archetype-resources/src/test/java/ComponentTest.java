package ${package};

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComponentTest {
    @Test
    public void testComponent() {
        final String foo = "foo";
        Component c = new Component(foo);
        assertEquals(foo, c.getConfigurable());
    }
}
