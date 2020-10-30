package io.spring.initializr.generator.spring.configuration;

import io.spring.initializr.generator.project.contributor.SingleResourceProjectContributor;

/**
 * @author ZhangWeiWei
 * @date 2020年10月30日,上午9:55:58
 * @description 将application.yml文件添加到项目里
 */
public class ApplicationYmlContributor extends SingleResourceProjectContributor {

	public ApplicationYmlContributor() {
		this("classpath:configuration/application.yml");
	}

	public ApplicationYmlContributor(String resourcePattern) {
		super("src/main/resources/application.yml", resourcePattern);
	}
}
