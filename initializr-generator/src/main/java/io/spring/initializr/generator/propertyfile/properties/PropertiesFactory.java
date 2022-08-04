package io.spring.initializr.generator.propertyfile.properties;

import io.spring.initializr.generator.propertyfile.PropertyFile;
import io.spring.initializr.generator.propertyfile.PropertyFileFactory;

/**
 * properties文件类型工厂
 * 
 * @author zhang weiwei
 * @since 2022年8月4日,下午3:25:29
 */
class PropertiesFactory implements PropertyFileFactory {

	@Override
	public PropertyFile createPropertyFile(String id) {
		if (Properties.ID.equals(id)) {
			return new Properties();
		}
		return null;
	}

}
