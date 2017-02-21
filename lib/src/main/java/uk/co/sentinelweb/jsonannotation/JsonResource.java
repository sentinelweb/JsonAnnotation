package uk.co.sentinelweb.jsonannotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation to mark a field to be populated via JSON java resource.<br/>
 *<br/>
 * Use {@link JsonResourceInitialiser#initAnnotations(Object)} to load the resource.<br/>
 * (Passing a {@link Class} populates static fields)<br/>
 *<br/>
 * The JSON file should be under the same package as the class to load from.<br/>
 *<br/>
 * Typical usage would be for data around the networking layer or objects that use JSON.<br/>
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface JsonResource {

    String fileName();

    /** optionally specify a class that is in the same package as the desired resource */
    Class<?> pathClass() default Void.class;
}