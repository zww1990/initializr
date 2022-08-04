package io.spring.initializr.generator.propertyfile;

import java.util.Objects;

import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * 属性配置文件类型
 * 
 * @author zhang weiwei
 * @since 2022年8月4日,下午2:19:35
 */
public interface PropertyFile {

	/**
	 * @return id
	 * @author zhang weiwei
	 * @since 2022年8月4日,下午2:27:15
	 */
	String id();

	/**
	 * @param id
	 * @return 属性配置文件类型
	 * @author zhang weiwei
	 * @since 2022年8月4日,下午2:30:11
	 */
	static PropertyFile forId(String id) {
		return SpringFactoriesLoader.loadFactories(PropertyFileFactory.class, PropertyFile.class.getClassLoader())
				.stream().map(factory -> factory.createPropertyFile(id)).filter(Objects::nonNull).findFirst()
				.orElseThrow(() -> new IllegalStateException("Unrecognized propertyFile id '" + id + "'"));
	}
}
