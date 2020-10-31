package io.spring.initializr.generator.spring.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

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
		String pkgName = metadata.getPackageName().getContent();
		String pkgPath = pkgName.replace('.', File.separatorChar);
		String first = "/**";
		String last = " */";
		Files.createDirectories(projectRoot.resolve(String.format("src/main/java/%s/domain", pkgPath)));
		Files.createDirectories(projectRoot.resolve(String.format("src/main/java/%s/repository", pkgPath)));
		Files.createDirectories(projectRoot.resolve(String.format("src/main/java/%s/service", pkgPath)));
		Files.createDirectories(projectRoot.resolve(String.format("src/main/java/%s/service/impl", pkgPath)));
		Files.createDirectories(projectRoot.resolve(String.format("src/main/java/%s/controller", pkgPath)));
		Files.createDirectories(projectRoot.resolve(String.format("src/main/java/%s/config", pkgPath)));
		Files.createDirectories(projectRoot.resolve(String.format("src/main/java/%s/constant", pkgPath)));
		Files.createDirectories(projectRoot.resolve(String.format("src/main/java/%s/utils", pkgPath)));
		Files.createDirectories(projectRoot.resolve(String.format("src/main/java/%s/exception", pkgPath)));
		Files.write(projectRoot.resolve(String.format("src/main/java/%s/domain/package-info.java", pkgPath)),
				Arrays.asList(first, " * 数据实体类", last, String.format("package %s.domain;", pkgName)),
				StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		Files.write(projectRoot.resolve(String.format("src/main/java/%s/repository/package-info.java", pkgPath)),
				Arrays.asList(first, " * 数据访问接口类", last, String.format("package %s.repository;", pkgName)),
				StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		Files.write(projectRoot.resolve(String.format("src/main/java/%s/service/package-info.java", pkgPath)),
				Arrays.asList(first, " * 数据服务接口类", last, String.format("package %s.service;", pkgName)),
				StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		Files.write(projectRoot.resolve(String.format("src/main/java/%s/service/impl/package-info.java", pkgPath)),
				Arrays.asList(first, " * 数据服务实现类", last, String.format("package %s.service.impl;", pkgName)),
				StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		Files.write(projectRoot.resolve(String.format("src/main/java/%s/controller/package-info.java", pkgPath)),
				Arrays.asList(first, " * 前端控制器类", last, String.format("package %s.controller;", pkgName)),
				StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		Files.write(projectRoot.resolve(String.format("src/main/java/%s/config/package-info.java", pkgPath)),
				Arrays.asList(first, " * 配置类", last, String.format("package %s.config;", pkgName)),
				StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		Files.write(projectRoot.resolve(String.format("src/main/java/%s/constant/package-info.java", pkgPath)),
				Arrays.asList(first, " * 常量、枚举类", last, String.format("package %s.constant;", pkgName)),
				StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		Files.write(projectRoot.resolve(String.format("src/main/java/%s/utils/package-info.java", pkgPath)),
				Arrays.asList(first, " * 工具类", last, String.format("package %s.utils;", pkgName)),
				StandardCharsets.UTF_8, StandardOpenOption.CREATE);
		Files.write(projectRoot.resolve(String.format("src/main/java/%s/exception/package-info.java", pkgPath)),
				Arrays.asList(first, " * 异常类", last, String.format("package %s.exception;", pkgName)),
				StandardCharsets.UTF_8, StandardOpenOption.CREATE);
	}
}
