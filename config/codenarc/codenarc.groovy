ruleset {
    ruleset 'rulesets/basic.xml'
    ruleset 'rulesets/braces.xml'
    ruleset 'rulesets/concurrency.xml'
    ruleset 'rulesets/convention.xml'
    ruleset 'rulesets/design.xml'
    ruleset('rulesets/dry.xml') {
        DuplicateStringLiteral(enabled: false)
        DuplicateNumberLiteral(enabled: false)
    }
    ruleset 'rulesets/enhanced.xml'
    ruleset('rulesets/formatting.xml') {
        ClassJavadoc(enabled: false)
        SpaceAroundMapEntryColon(enabled: false)
        LineLength(enabled: false)
    }
    ruleset 'rulesets/generic.xml'
    ruleset 'rulesets/groovyism.xml'
    ruleset 'rulesets/exceptions.xml'
    ruleset 'rulesets/imports.xml'
    ruleset 'rulesets/size.xml'
    ruleset('rulesets/unnecessary.xml') {
        UnnecessaryBooleanExpression(enabled: false) // looks like a bug with parametrized tests
    }
    ruleset('rulesets/unused.xml') {
        UnusedObject(enabled: false) // issue with instantiation of object where throwing an exception
    }
    ruleset('rulesets/security.xml') {
        JavaIoPackageAccess(enabled: false)
    }
}