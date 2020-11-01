package io.spring.initializr.web.support;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.spring.initializr.metadata.DefaultMetadataElement;
import io.spring.initializr.metadata.InitializrMetadata;

/**
 * 惰性初始化器元数据更新策略
 * 
 * @author home
 */
public class LazyInitializrMetadataUpdateStrategy implements InitializrMetadataUpdateStrategy {
	private static final Logger log = LoggerFactory.getLogger(LazyInitializrMetadataUpdateStrategy.class);
	private final RestTemplate restTemplate;
	private final ObjectMapper objectMapper;

	public LazyInitializrMetadataUpdateStrategy(RestTemplate restTemplate, ObjectMapper objectMapper) {
		this.restTemplate = restTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	public InitializrMetadata update(InitializrMetadata current) {
		String url = current.getConfiguration().getEnv().getSpringBootMetadataUrl();
		List<DefaultMetadataElement> bootVersions = fetchSpringBootVersions(url);
		if (bootVersions != null && !bootVersions.isEmpty()) {
			if (bootVersions.stream().noneMatch(DefaultMetadataElement::isDefault)) {
				// No default specified
				bootVersions.get(0).setDefault(true);
			}
			current.updateSpringBootVersions(bootVersions);
		}
		return current;
	}

	private List<DefaultMetadataElement> fetchSpringBootVersions(String url) {
		Path path = Paths.get("spring-boot.metadata");
		if (Files.isReadable(path)) {
			try {
				log.info("Fetching Spring Boot metadata from {}", path);
				return this.objectMapper.readValue(Files.newInputStream(path), this.objectMapper.getTypeFactory()
						.constructParametricType(List.class, DefaultMetadataElement.class));
			} catch (Exception e) {
				log.warn("Failed to fetch Spring Boot metadata", e);
			}
		}
		if (StringUtils.hasText(url)) {
			try {
				log.info("Fetching Spring Boot metadata from {}", url);
				List<DefaultMetadataElement> versions = new SpringBootMetadataReader(this.objectMapper,
						this.restTemplate, url).getBootVersions();
				Files.write(path, this.objectMapper.writeValueAsBytes(versions), StandardOpenOption.CREATE);
				log.info("Saving Spring Boot metadata from {}", path);
				return versions;
			} catch (Exception ex) {
				log.warn("Failed to fetch Spring Boot metadata", ex);
			}
		}
		return null;
	}
}
