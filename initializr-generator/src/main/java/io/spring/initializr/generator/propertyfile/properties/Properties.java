package io.spring.initializr.generator.propertyfile.properties;

import io.spring.initializr.generator.propertyfile.PropertyFile;

/**
 * properties文件类型
 * 
 * @author zhang weiwei
 * @since 2022年8月4日,下午3:23:44
 */
public final class Properties implements PropertyFile {
	/** properties文件类型ID */
	public static final String ID = "properties";

	@Override
	public String id() {
		return ID;
	}

	@Override
	public String toString() {
		return this.id();
	}
}
