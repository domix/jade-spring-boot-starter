/**
 *
 * Copyright (C) 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.domingosuarez.boot.autoconfigure.jade4j;

import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;

/**
 * {@link org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider} that provides availability information for
 * jade4j view templates
 *
 * @author Domingo Suarez Torres
 */
public class Jade4JTemplateAvailabilityProvider implements TemplateAvailabilityProvider {

  @Override
  public boolean isTemplateAvailable(String view, Environment environment, ClassLoader classLoader, ResourceLoader resourceLoader) {
    if (ClassUtils.isPresent("de.neuland.jade4j.spring.template.SpringTemplateLoader", classLoader)) {
      String prefix = environment.getProperty("spring.jade4j.prefix", Jade4JAutoConfiguration.DEFAULT_PREFIX);
      String suffix = environment.getProperty("spring.jade4j.suffix", Jade4JAutoConfiguration.DEFAULT_SUFFIX);
      return resourceLoader.getResource(prefix + view + suffix).exists();
    }

    return false;
  }
}
