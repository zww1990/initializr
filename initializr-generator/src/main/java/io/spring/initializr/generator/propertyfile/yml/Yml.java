package io.spring.initializr.generator.propertyfile.yml;

import io.spring.initializr.generator.propertyfile.PropertyFile;

/**
 * yml文件类型
 * 
 * @author zhang weiwei
 * @since 2022年8月4日,下午3:30:04
 */
public final class Yml implements PropertyFile {
	/** yml文件类型ID */
	public static final String ID = "yml";

	@Override
	public String id() {
		return ID;
	}

	@Override
	public String toString() {
		return this.id();
	}
}
