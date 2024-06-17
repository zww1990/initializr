package io.spring.initializr.generator.spring.properties;

import io.spring.initializr.generator.project.contributor.ProjectContributor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * 将application.yml文件添加到项目里
 * 
 * @author Zhang WeiWei
 * @since 2020年10月30日,上午9:55:58
 */
public class ApplicationYmlContributor implements ProjectContributor {

	private static final String FILE = "src/main/resources/application.yml";

	private final ApplicationProperties properties;

	public ApplicationYmlContributor(ApplicationProperties properties) {
		this.properties = properties;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {
		Path output = projectRoot.resolve(FILE);
		if (!Files.exists(output)) {
			Files.createDirectories(output.getParent());
			Files.createFile(output);
		}
		try (PrintWriter writer = new PrintWriter(Files.newOutputStream(output, StandardOpenOption.APPEND), false,
				StandardCharsets.UTF_8)) {
			this.properties.writeTo(writer);
		}
	}

}
