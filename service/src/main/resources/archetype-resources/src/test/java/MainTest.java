package ${package};

import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.opentable.jaxrs.JaxRsClientFactory;
import com.opentable.jaxrs.StandardFeatureGroup;
import com.opentable.server.HttpServerInfo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MainTest.TestConfiguration.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class MainTest {
    @Inject
    private HttpServerInfo serverInfo;

    @Inject
    private Client client;

    @Test
    public void testHelloWorld() {
        final String target = String.format("http://localhost:%s/my/resource", serverInfo.getPort());
        try (final Response r = client.target(target).request().get()) {
            assertThat(r.getStatus()).isEqualTo(200);
            assertThat(r.readEntity(String.class)).isEqualTo("Hello, World!");
        }
    }

    @Configuration
    @Import(Main.class)
    static class TestConfiguration {
        @Bean(destroyMethod = "close")
        Client testClient(final JaxRsClientFactory clientFactory) {
            return clientFactory.newClient("test-client", StandardFeatureGroup.PLATFORM_INTERNAL);
        }

    }
}
