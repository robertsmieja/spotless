/*
 * Copyright 2016 DiffPlug
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.diffplug.gradle.spotless;

import java.util.Objects;

import com.diffplug.spotless.FormatterStep;
import com.diffplug.spotless.antlr4.Antlr4FormatterStep;

public class Antlr4Extension extends FormatExtension {
	static final String NAME = "antlr4";

	public Antlr4Extension(SpotlessExtension rootExtension) {
		super(rootExtension);
	}

	public Antlr4FormatterConfig antlr4Formatter() {
		return antlr4Formatter(Antlr4FormatterStep.defaultVersion());
	}

	public Antlr4FormatterConfig antlr4Formatter(String version) {
		return new Antlr4FormatterConfig(version);
	}

	public class Antlr4FormatterConfig {

		private final String version;

		Antlr4FormatterConfig(String version) {
			this.version = Objects.requireNonNull(version);
			addStep(createStep());
		}

		private FormatterStep createStep() {
			return Antlr4FormatterStep.create(this.version, GradleProvisioner.fromProject(getProject()));
		}
	}

	@Override
	protected void setupTask(SpotlessTask task) {
		// defaults to all antlr4 files
		if (target == null) {
			target = parseTarget("src/main/antlr4/**/*.g4");
		}
		super.setupTask(task);
	}

}
