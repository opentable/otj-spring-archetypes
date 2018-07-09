## Licensed under the Apache License, Version 2.0 (the "License");
## you may not use this file except in compliance with the License.
## You may obtain a copy of the License at
##
## http://www.apache.org/licenses/LICENSE-2.0
##
## Unless required by applicable law or agreed to in writing, software
## distributed under the License is distributed on an "AS IS" BASIS,
## WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
## See the License for the specific language governing permissions and
## limitations under the License.
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
