package com.mk.junit;

import java.util.Objects;
import java.util.stream.IntStream;

import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * @author manki.kim
 */
public class RepeatRunner extends BlockJUnit4ClassRunner {

	public RepeatRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public Description describeChild(FrameworkMethod method) {
		if (isSkipRepeat(method)) {
			return super.describeChild(method);
		}

		int totalRepetitions = method.getAnnotation(Repeat.class).value();
		if (totalRepetitions < 0) {
			throw new IllegalArgumentException("Repeat must be declared with a positive 'value'");
		}

		Description description = Description.createSuiteDescription(method.getName(), method.getAnnotations());
		IntStream.range(0, totalRepetitions)
			.mapToObj(currentRepetition -> generateChildDescription(method, currentRepetition))
			.forEach(description::addChild);

		return description;
	}

	private boolean isSkipRepeat(FrameworkMethod method) {
		return isIgnored(method) || Objects.isNull(method.getAnnotation(Repeat.class));
	}

	private Description generateChildDescription(FrameworkMethod method, int currentRepetition) {
		return Description.createTestDescription(getTestClass().getJavaClass(),
			makeChildDisplayName(method, currentRepetition));
	}

	private String makeChildDisplayName(FrameworkMethod method, int currentRepetition) {
		return method.getName() + "[" + currentRepetition + "]";
	}

	@Override
	protected void runChild(final FrameworkMethod method, RunNotifier notifier) {
		if (isSkipRepeat(method)) {
			super.runChild(method, notifier);
			return;
		}

		describeChild(method).getChildren()
			.forEach(childDesc -> runLeaf(methodBlock(method), childDesc, notifier));
	}
}
