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

import com.opentable.resttemplate.RestTemplateConfiguration
import com.opentable.resttemplate.RestTemplateFactory

import com.opentable.server.OTApplication
import com.opentable.server.mvc.MVCServer

import com.opentable.service.ServiceInfo
import com.opentable.service.discovery.client.EnableDiscoveryClient

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

import org.springframework.web.client.RestTemplate

@Configuration
@MVCServer
@EnableDiscoveryClient
@Import(RestTemplateConfiguration::class, MyController::class)
class Main {
    @Bean
    fun serviceInfo(): ServiceInfo = ServiceInfo { "service-it" }

    @Bean("curlClient")
    fun curlClient(restTemplateFactory: RestTemplateFactory): RestTemplate =
            restTemplateFactory.newTemplate(true, "curlClient")
}

fun main(args: Array<String>) {
    OTApplication.run(Main::class.java, *args)
}
