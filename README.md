# @Repeat

```java
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.METHOD})
public @interface Repeat {

	/**
	 * The number of repetitions.
	 *
	 * @return the number of repetitions; must be greater than zero
	 */
	int value();
}
```

- 특정 테스트 메소드를 반복하여 실행할 수 있습니다.
- timeout 혹은 다른 Rule들과 함께 사용하는데 지장이 없습니다.
- 주의! value가 음수 값이 되면 안됩니다.

# RepeatRunner

- @Repeat 동작을 제어 하는 Custom Junit Test Runner입니다.
- @Repeat 동작 진행을 위해서는 `@RunWith(RepeatRunner.class)` Test Class 상단에 위와 같이 RepeatRunner를 명시해주어야 합니다.

# Example

```java

@RunWith(RepeatRunner.class)
public class MyTestClass {

	@Test
	@Repeat(10)
	public void testMyCode10Times() {

	}

	@Test
	@Repeat(5)
	public void testMyCode5Times() {

	}

	@Test
	public void testNotUsingRepeat() {

	}

	@Ignore
	@Test
	@Repeat(10)
	public void testCheckIgnore() {

	}
}

```