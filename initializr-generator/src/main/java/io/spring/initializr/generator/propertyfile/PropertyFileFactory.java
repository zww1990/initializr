package io.spring.initializr.generator.propertyfile;

/**
 * 属性配置文件类型工厂
 * 
 * @author zhang weiwei
 * @since 2022年8月4日,下午2:24:10
 */
public interface PropertyFileFactory {
	/**
	 * @param id
	 * @return 创建属性配置文件类型
	 * @author zhang weiwei
	 * @since 2022年8月4日,下午2:25:48
	 */
	PropertyFile createPropertyFile(String id);
}
