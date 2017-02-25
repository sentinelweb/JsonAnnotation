# JsonAnnotation
An annotation to load JSON objects for tests - the JOSN file are in stored in resource directories.  

# Usage

See example module
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])

    testCompile 'uk.co.sentinelweb:json-annotation:1.0.2'
}


repositories {
    jcenter()
    // todo still need this - hw to publish to main jcenter repo
    maven {
        url 'https://dl.bintray.com/sentinelweb/generic/'
    }
}
```
You may also have to add 
```
sourceSets { test { output.resourcesDir = output.classesDir } }
```
to get the recources to output to your class directory

 [ ![Download](https://api.bintray.com/packages/sentinelweb/generic/json-annotation/images/download.svg) ](https://bintray.com/sentinelweb/generic/json-annotation/_latestVersion)
