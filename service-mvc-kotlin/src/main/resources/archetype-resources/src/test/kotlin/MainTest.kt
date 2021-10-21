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

import org.assertj.core.api.Assertions.assertThat

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment

import org.springframework.boot.test.web.client.TestRestTemplate

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [Main::class], webEnvironment = WebEnvironment.RANDOM_PORT)
class MainTest(@Autowired val testRestTemplate: TestRestTemplate) {
    @Test
    fun testHelloWorld() {
        val actualResponse: ResponseEntity<String> = testRestTemplate.getForEntity("/", String::class.java)
        assertThat(actualResponse.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(actualResponse.body).isEqualTo("Hello, world!")
    }
}
