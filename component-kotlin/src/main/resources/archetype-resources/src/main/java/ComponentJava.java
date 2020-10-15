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

/**
 * A simple Spring managed bean.
 * You'll probably want to do something more than
 * store a String.
 */
public class ComponentJava {
    private final String configurable;

    public ComponentJava(String configurable) {
        this.configurable = configurable;
    }

    public String getConfigurable() {
        return configurable;
    }
}