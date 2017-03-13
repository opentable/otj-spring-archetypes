package ${package};

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Main.class, webEnvironment=WebEnvironment.RANDOM_PORT)
public class MainTest {

    @Inject
    @LocalServerPort
    int port;

    @Test
    public void testHelloWorld() throws Exception {
        final Client httpClient = ClientBuilder.newClient();
        try {
            Response r = httpClient.target(String.format("http://localhost:%s/my/resource", port)).request().get();
            assertThat(r.getStatus()).isEqualTo(200);
            assertThat(r.readEntity(String.class)).isEqualTo("Hello, World!");
        } finally {
            httpClient.close();
        }
    }
}
