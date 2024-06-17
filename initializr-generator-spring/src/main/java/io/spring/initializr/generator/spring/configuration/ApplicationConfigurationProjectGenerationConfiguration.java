/*
 * Copyright 2012-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.initializr.generator.spring.configuration;

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.condition.ConditionalOnLanguage;
import io.spring.initializr.generator.condition.ConditionalOnPropertyFile;
import io.spring.initializr.generator.language.java.JavaLanguage;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.propertyfile.properties.Properties;
import io.spring.initializr.generator.propertyfile.yml.Yml;
import io.spring.initializr.generator.spring.properties.ApplicationProperties;
import io.spring.initializr.generator.spring.properties.ApplicationPropertiesContributor;
import io.spring.initializr.metadata.InitializrMetadata;

import org.springframework.context.annotation.Bean;

/**
 * Configuration for application-related contributions to a generated project.
 *
 * @author Stephane Nicoll
 * @author Moritz Halbritter
 */
@ProjectGenerationConfiguration
public class ApplicationConfigurationProjectGenerationConfiguration {

    @Bean
    WebFoldersContributor webFoldersContributor(Build build, InitializrMetadata metadata,
            ProjectDescription projectDescription) {
        return new WebFoldersContributor(build, metadata, projectDescription);
    }

//    @Bean
//    @ConditionalOnPropertyFile(Properties.ID)
//    public ApplicationPropertiesContributor applicationPropertiesContributor(
//            ApplicationProperties properties) {
//        return new ApplicationPropertiesContributor(properties);
//    }

    @Bean
    @ConditionalOnPropertyFile(Yml.ID)
    public ApplicationYmlContributor applicationYmlContributor() {
        return new ApplicationYmlContributor();
    }

    @Bean
    @ConditionalOnLanguage(JavaLanguage.ID)
    public JavaFoldersContributor javaFoldersContributor(InitializrMetadata metadata) {
        return new JavaFoldersContributor(metadata);
    }

}
