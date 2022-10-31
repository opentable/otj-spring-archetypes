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
import org.springframework.web.client.RestTemplate;

import com.opentable.server.OTApplication;
import com.opentable.server.mvc.MVCServer;
import com.opentable.service.ServiceInfo;
import com.opentable.service.discovery.client.EnableDiscoveryClient;

import com.opentable.resttemplate.RestTemplateConfiguration;
import com.opentable.resttemplate.RestTemplateFactory;
import com.opentable.resttemplate.RestTemplateFeatureBinding;

@Configuration
@MVCServer
@EnableDiscoveryClient
@Import({RestTemplateConfiguration.class, MyController.class})
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
    public RestTemplate curlClient(RestTemplateFactory restTemplateFactory) {
        return restTemplateFactory.newTemplate("${artifactId}", RestTemplateFeatureBinding.INTERNAL);
    }
}
