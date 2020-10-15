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
#set( $D = '$' )
#set( $H = '#' )
package ${package};

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * A Spring Configuration class for our ComponentJava.
 * Inject configuration values, set up your beans, etc.
 */
@Configuration
public class ComponentJavaConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(ComponentJavaConfiguration.class);

    @Value("${D}{ot.component.config:${H}{null}}")
    private String configurable;

    @Bean
    public ComponentJava component() {
        LOG.info("Initializing Component (Java) with configurable {}", configurable);
        return new ComponentJava(configurable);
    }
}
