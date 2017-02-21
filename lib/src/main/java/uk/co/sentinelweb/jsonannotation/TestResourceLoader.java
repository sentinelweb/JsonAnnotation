package uk.co.sentinelweb.jsonannotation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * Loads Java resources from JSON for testing (web responses).
 */
public class TestResourceLoader<T> {
    private final Class<T> type;

    public TestResourceLoader(Class<T> type) {
        this.type = type;
    }

    public Gson getGson() {
        Gson gson = new GsonBuilder()
                .create();
        return gson;
    }

    /**
     * Gets the class/resource combination as a string.
     * @param clazz
     * @param resourceName
     * @return string
     */
    public String getString(final Class clazz, final String resourceName) {

        try {
            InputStream io = clazz.getResourceAsStream(resourceName);
            Assert.assertNotNull("File " + resourceName
                    + " not found in the classpath of the class " + clazz, io);
            return toString(io);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets an input stream from a class/resource combination.
     * @param clazz
     * @param resourceName
     * @return inputstream
     */
    public InputStream getInputStream(final Class clazz, final String resourceName) {

        try {
            InputStream io = clazz.getResourceAsStream(resourceName);
            Assert.assertNotNull("File " + resourceName
                    + " not found in the classpath of the class " + clazz, io);
            return io;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get the inputstream contents as a string.
     * @param stream
     * @return
     */
    public String toString(final InputStream stream) {
        StringBuilder stringBuilder = new StringBuilder();
        int b = 0;
        try {
            b = stream.read();
            while (b != -1) {
                stringBuilder.append((char) b);
                b = stream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }

    public T loadJsonResource(final Class pathclazz, final String resourceName) {
        return loadJsonResource( pathclazz,  resourceName, this.type);
    }

    public T loadJsonResource(final Class pathclazz, final String resourceName, final Class<T> type) {
        return getGson().fromJson(getString(pathclazz, resourceName), type);
    }
}