# Junit4-Repeat-Runner

## Directory Layout
```
├── config                  # Configurations for checkstyle, etc.
├── gradle                  # Gradle wrapper
├── src
│   │   ├── main
│   │   └── test
├── build                   # Build artifacts (e.g. compiled files, test reports)
├── .gitignore
├── build.gradle
├── README.md
└── settings.gradle
```

## Prerequisites
- Java 11


## @Repeat

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

## RepeatRunner

- @Repeat 동작을 제어 하는 Custom Junit Test Runner입니다.
- @Repeat 동작 진행을 위해서는 `@RunWith(RepeatRunner.class)` Test Class 상단에 위와 같이 RepeatRunner를 명시해주어야 합니다.

## Example

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

## How to contribute

### Branching
[Gitflow의 workflow](https://nvie.com/posts/a-successful-git-branching-model/) 에 기반합니다. (참고하세요)

### Pull Request Process
1. PR은 이슈 단위로 생성
2. 리뷰어를 최소 한 명 이상 지정하여 진행
3. 가급적 최신 base branch에서 분기한 상태로 생성

### Github Action status check
Github Action을 이용해 checkstyle 검증이 매 PR 커밋마다 돌고 있습니다.
해당 체크가 통과하지 않는 다면 checkstyle 확인을 부탁드립니다.

### Merge

하나 이상의 approve를 얻었다면 리뷰 요청자 판단에 따라 base branch로 merge할 수 있습니다.

추후 이력을 확인하기 쉽도록 아래 방식을 사용하여 병합해주세요.

1. 최신 develop 브랜치를 base로 재설정

```
$ git checkout feature/{your branch name}
$ git pull --rebase origin develop
```

2. 원격 저장소로 force push
```
$ git push -f origin feature/{your branch name}
```

3. GitHub Action status check 통과하는지 확인 후 merge
```
$ git checkout develop 
$ git pull
$ git merge --no-ff feature/{your branch name}
$ git push origin develop
```