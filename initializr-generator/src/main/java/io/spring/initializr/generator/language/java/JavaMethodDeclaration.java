/*
 * Copyright 2012-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.initializr.generator.language.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.spring.initializr.generator.language.Annotatable;
import io.spring.initializr.generator.language.AnnotationContainer;
import io.spring.initializr.generator.language.CodeBlock;
import io.spring.initializr.generator.language.Parameter;

/**
 * Declaration of a method written in Java.
 *
 * @author Andy Wilkinson
 */
public final class JavaMethodDeclaration implements Annotatable {

	private final AnnotationContainer annotations = new AnnotationContainer();

	private final String name;

	private final String returnType;

	private final int modifiers;

	private final List<Parameter> parameters;

	private final CodeBlock code;

	private JavaMethodDeclaration(Builder builder, CodeBlock code) {
		this.name = builder.name;
		this.returnType = builder.returnType;
		this.modifiers = builder.modifiers;
		this.parameters = List.copyOf(builder.parameters);
		this.code = code;
	}

	public static Builder method(String name) {
		return new Builder(name);
	}

	String getName() {
		return this.name;
	}

	String getReturnType() {
		return this.returnType;
	}

	List<Parameter> getParameters() {
		return this.parameters;
	}

	int getModifiers() {
		return this.modifiers;
	}

	CodeBlock getCode() {
		return this.code;
	}

	@Override
	public AnnotationContainer annotations() {
		return this.annotations;
	}

	/**
	 * Builder for creating a {@link JavaMethodDeclaration}.
	 */
	public static final class Builder {

		private final String name;

		private List<Parameter> parameters = new ArrayList<>();

		private String returnType = "void";

		private int modifiers;

		private Builder(String name) {
			this.name = name;
		}

		public Builder modifiers(int modifiers) {
			this.modifiers = modifiers;
			return this;
		}

		public Builder returning(String returnType) {
			this.returnType = returnType;
			return this;
		}

		public Builder parameters(Parameter... parameters) {
			this.parameters = Arrays.asList(parameters);
			return this;
		}

		public JavaMethodDeclaration body(CodeBlock code) {
			return new JavaMethodDeclaration(this, code);
		}

	}

}
