package io.spring.initializr.generator.condition;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

/**
 * Conditional On Property File Type
 * 
 * @author zhang weiwei
 * @since 2022年8月4日,下午3:44:00
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Conditional(OnPropertyFileCondition.class)
public @interface ConditionalOnPropertyFile {

	String value();

}
