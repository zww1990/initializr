package io.spring.initializr.generator.condition;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import io.spring.initializr.generator.project.ProjectDescription;
import io.spring.initializr.generator.propertyfile.PropertyFile;

/**
 * On Property File Type Condition
 * 
 * @author zhang weiwei
 * @since 2022年8月4日,下午3:39:08
 */
class OnPropertyFileCondition extends ProjectGenerationCondition {

	@Override
	protected boolean matches(ProjectDescription description, ConditionContext context,
			AnnotatedTypeMetadata metadata) {
		if (description.getPropertyFile() == null) {
			return false;
		}
		String propertyFileId = (String) metadata.getAllAnnotationAttributes(ConditionalOnPropertyFile.class.getName())
				.getFirst("value");
		PropertyFile propertyFile = PropertyFile.forId(propertyFileId);
		return description.getPropertyFile().id().equals(propertyFile.id());
	}

}
