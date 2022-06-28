# Tailormap persistence

Tailormap persistence provides the persistence API for Tailormap admin and Tailormap API.

## Development

Basic procedure:

1. do your thing, if possible use `aosp` styling
2. run `mvn clean install` to make sure all required formatting is applied and all tests pass
3. commit and push your branch to create a pull request
4. wait for code review to pass, possibly amend your PR and merge your PR

Make sure you have useful commit messages, any PR with `WIP` commits will need 
to be squashed before merge.

see also [QA](#QA)

### Tips and Tricks

* You can skip CI execution[^1] by specifying `[skip ci]` as part of your commit.
* You can use `mvn -U org.codehaus.mojo:versions-maven-plugin:display-plugin-updates` to search for plugin updates
* You can use `mvn -U org.codehaus.mojo:versions-maven-plugin:display-dependency-updates` to search for dependency updates

### Profiles

Some Maven profiles are defined:

* **release**
    - Release specific configuration
    - Activated automagically
* **developing**
    - Sets specific properties for use in the IDE such as disabling docker build and QA
    - Activated using the flag `-Pdeveloping` or checking the option in your IDE
* **qa-skip**
    - Will skip any "QA" Maven plugins defined with a defined `skip` element
    - Activated using flag `-DskipQA=true`

## QA

Some quick points of attention:

* AOSP formatting is enforced
* PMD checks are enforced
* The ErrorProne compiler is used
* A current release of Maven is required
* Java 11
* jUnit 4 is forbidden
* code is reviewed before merge to the main branch
* Javadoc must be valid

## Releasing

Use the regular Maven release cycle of `mvn release: prepare` followed by `mvn release:perform`. Please make sure that
you use `tailormap-persistence-<VERSION>` as a tag so that the release notes are automatically created. 
For example:

```shell
mvn clean release:prepare -DreleaseVersion=0.1 -Dtag=tailormap-persistence-0.1 -DdevelopmentVersion=0.2-SNAPSHOT -T1
mvn release:perform -T1
```

[^1]: https://github.blog/changelog/2021-02-08-github-actions-skip-pull-request-and-push-workflows-with-skip-ci/