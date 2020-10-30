package io.spring.initializr.generator.spring.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import io.spring.initializr.generator.project.contributor.ProjectContributor;
import io.spring.initializr.metadata.InitializrMetadata;

/**
 * @author ZhangWeiWei
 * @date 2020年10月30日,上午10:56:19
 * @description 当检测到与java相关的项目时，将创建java特定目录。
 */
public class JavaFoldersContributor implements ProjectContributor {
	private final InitializrMetadata metadata;

	public JavaFoldersContributor(InitializrMetadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public void contribute(Path projectRoot) throws IOException {
		String javaRoot = "src/main/java/";
		String pkgName = metadata.getPackageName().getContent();
		String pkgPath = javaRoot + pkgName.replace('.', File.separatorChar);
		Files.createDirectories(projectRoot.resolve(pkgPath + "/domain"));
		Files.createDirectories(projectRoot.resolve(pkgPath + "/repository"));
		Files.createDirectories(projectRoot.resolve(pkgPath + "/service"));
		Files.createDirectories(projectRoot.resolve(pkgPath + "/service/impl"));
		Files.createDirectories(projectRoot.resolve(pkgPath + "/controller"));
		Files.createDirectories(projectRoot.resolve(pkgPath + "/config"));
		Files.createDirectories(projectRoot.resolve(pkgPath + "/constant"));
		Files.createDirectories(projectRoot.resolve(pkgPath + "/utils"));
		Files.createDirectories(projectRoot.resolve(pkgPath + "/exception"));
	}
}
