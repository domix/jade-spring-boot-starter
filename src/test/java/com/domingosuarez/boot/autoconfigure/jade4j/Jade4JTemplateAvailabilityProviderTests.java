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

import com.domingosuarez.boot.autoconfigure.jade4j.Jade4JTemplateAvailabilityProvider;
import org.junit.Test;
import org.springframework.boot.autoconfigure.template.TemplateAvailabilityProvider;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link com.domingosuarez.boot.autoconfigure.jade4j.Jade4JTemplateAvailabilityProvider}.
 *
 * @author Domingo Suarez Torres
 */
public class Jade4JTemplateAvailabilityProviderTests {
  private final TemplateAvailabilityProvider provider = new Jade4JTemplateAvailabilityProvider();

  private final ResourceLoader resourceLoader = new DefaultResourceLoader();

  private final MockEnvironment environment = new MockEnvironment();

  @Test
  public void availabilityOfTemplateInDefaultLocation() {
    assertTrue(this.provider.isTemplateAvailable("home", this.environment,
        getClass().getClassLoader(), this.resourceLoader));
  }

  @Test
  public void availabilityOfTemplateThatDoesNotExist() {
    assertFalse(this.provider.isTemplateAvailable("whatever", this.environment,
        getClass().getClassLoader(), this.resourceLoader));
  }

  @Test
  public void availabilityOfTemplateWithCustomPrefix() {
    this.environment.setProperty("spring.jade4j.prefix", "classpath:/custom-templates/");

    assertTrue(this.provider.isTemplateAvailable("custom", this.environment,
        getClass().getClassLoader(), this.resourceLoader));
  }

  @Test
  public void availabilityOfTemplateWithCustomSuffix() {
    this.environment.setProperty("spring.jade4j.suffix", ".jade4j");

    assertTrue(this.provider.isTemplateAvailable("suffixed", this.environment,
        getClass().getClassLoader(), this.resourceLoader));
  }
}
