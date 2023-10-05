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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

import com.opentable.server.OTApplication;
import com.opentable.server.reactive.ReactiveServer;
import com.opentable.service.ServiceInfo;
import com.opentable.service.discovery.client.EnableDiscoveryClient;

import com.opentable.webclient.WebClientConfiguration;
import com.opentable.webclient.WebClientBuilderFactory;
import com.opentable.webclient.WebClientFeatureBinding;

@Configuration
@ReactiveServer
@EnableDiscoveryClient
@Import({WebClientConfiguration.class, MyController.class})
public class Main {
    public static void main(final String[] args) {
        OTApplication.run(Main.class, args);
    }

    @Bean
    public ServiceInfo serviceInfo() {
        return new ServiceInfo() {
            @Override
            public String getName() {
                return "${artifactId}";
            }
        };
    }

    @Bean
    public WebClient echoClient(WebClientBuilderFactory factory) {
        return factory.newBuilder("curl", WebClientFeatureBinding.INTERNAL)
            .baseUrl("https://postman-echo.com/")
            .build();
    }

}
