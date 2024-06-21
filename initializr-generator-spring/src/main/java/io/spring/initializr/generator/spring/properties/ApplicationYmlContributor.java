package io.spring.initializr.generator.spring.properties;

import io.spring.initializr.generator.project.contributor.ProjectContributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 将application.yml文件添加到项目里
 * 
 * @author Zhang WeiWei
 * @since 2020年10月30日,上午9:55:58
 */
public class ApplicationYmlContributor implements ProjectContributor {
	public static Logger log = LoggerFactory.getLogger(ApplicationYmlContributor.class);
	private static final String FILE = "src/main/resources/application.yml";

	private final ApplicationProperties properties;
    private final Yaml yaml;

	public ApplicationYmlContributor(ApplicationProperties properties) {
		this.properties = properties;
        DumperOptions dumper = new DumperOptions();
		dumper.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
		this.yaml = new Yaml(dumper);
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {
		Path output = projectRoot.resolve(FILE);
		if (!Files.exists(output)) {
			Files.createDirectories(output.getParent());
			Files.createFile(output);
		}
		try (PrintWriter writer = new PrintWriter(Files.newOutputStream(
				output, StandardOpenOption.APPEND), false, StandardCharsets.UTF_8)) {
//			this.properties.writeTo(writer);
			this.yaml.dump(this.setValue(this.properties.getProperties()), writer);
		}
	}

	private Object setValue(Map<String, Object> inputMap) {
		log.info("setValue(): inputMap = {}", inputMap);
		if (inputMap == null) {
			return Collections.emptyMap();
		}
		Map<String, Object> outputMap = new HashMap<>();
		inputMap.forEach((key, value) -> {
			String[] keys = key.split("\\.");
			int length = keys.length;
			if (length == 1) {
				outputMap.put(key, value);
			} else {
				Map<String, Object> temp = outputMap;
				for (int i = 0; i < length - 1; i++) {
					if (!temp.containsKey(keys[i])) {
						temp.put(keys[i], new HashMap<>());
					}
					temp = (Map) temp.get(keys[i]);
					if (i == length - 2) {
						temp.put(keys[i + 1], value);
					}
				}
			}
		});
		log.info("setValue(): outputMap = {}", outputMap);
		return outputMap;
	}

}
