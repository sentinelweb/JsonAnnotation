package uk.co.sentinelweb.jsonannotation;

import java.lang.reflect.Field;


public class JsonResourceInitialiser {
    /**
     * Populate fields marked with {@link JsonResource}
     *
     * Passing a {@link Class} assumes the field is static.
     *
     * @param o
     */
    public static void initAnnotations(final Object o) {
        if (o instanceof Class) {
            // static field initialiser
            injectFields(null, (Class) o);
        } else {
            injectFields(o, o.getClass());
        }
    }

    private static void injectFields(final Object target, final Class<?> targetClass) {
        for (final Field field : targetClass.getFields()) {
            try {
                final JsonResource annotation = field.getAnnotation(JsonResource.class);
                if (annotation != null) {
                    final TestResourceLoader<? extends Object> testResourceLoader =
                            new TestResourceLoader<>(field.getType());
                    final Class<?> pathClass = annotation.pathClass() != Void.class ? annotation.pathClass() : targetClass;
                    final Object value =
                            testResourceLoader.loadJsonResource(pathClass, annotation.fileName());
                    field.set(target, value);
                    continue;
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Clear fields marked with {@link JsonResource}
     *
     * Passing a {@link Class} assumes the field is static.
     *
     * @param o
     */
    public static void clearAnnotations(final Object o) {
        if (o instanceof Class) {
            // static field clear
            clearFields(null, (Class) o);
        } else {
            clearFields(o, o.getClass());
        }
    }

    private static void clearFields(final Object target, final Class<?> targetClass) {
        for (final Field field : targetClass.getFields()) {
            try {
                final JsonResource annotation = field.getAnnotation(JsonResource.class);
                if (annotation != null) {
                    field.set(target, null);
                    continue;
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}