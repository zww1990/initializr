package io.spring.initializr.generator.propertyfile.yml;

import io.spring.initializr.generator.propertyfile.PropertyFile;
import io.spring.initializr.generator.propertyfile.PropertyFileFactory;

/**
 * yml文件类型工厂
 * 
 * @author zhang weiwei
 * @since 2022年8月4日,下午3:32:55
 */
class YmlFactory implements PropertyFileFactory {

	@Override
	public PropertyFile createPropertyFile(String id) {
		if (Yml.ID.equals(id)) {
			return new Yml();
		}
		return null;
	}

}
