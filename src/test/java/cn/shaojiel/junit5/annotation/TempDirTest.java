package cn.shaojiel.junit5.annotation;

import cn.shaojiel.junit5.util.ListWriter;
import com.google.common.jimfs.Configuration;
import com.google.common.jimfs.Jimfs;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AnnotatedElementContext;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.io.TempDirFactory;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.io.CleanupMode.ON_SUCCESS;

class TempDirTest {

    @Test
    void writeItemsToFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("test.txt");

        new ListWriter(file).write("a", "b", "c");

        assertEquals(singletonList("a,b,c"), Files.readAllLines(file));
    }

    @Test
    void copyFileFromSourceToTarget(@TempDir Path source, @TempDir Path target) throws IOException {
        Path sourceFile = source.resolve("test.txt");
        new ListWriter(sourceFile).write("a", "b", "c");

        Path targetFile = Files.copy(sourceFile, target.resolve("test.txt"));

        assertNotEquals(sourceFile, targetFile);
        assertEquals(singletonList("a,b,c"), Files.readAllLines(targetFile));
    }


    @SuppressWarnings("JUnitMalformedDeclaration")
    static class SharedTempDirectoryTest {

        @TempDir
        static Path sharedTempDir;

        @Test
        void writeItemsToFile() throws IOException {
            Path file = sharedTempDir.resolve("test.txt");

            new ListWriter(file).write("a", "b", "c");

            assertEquals(singletonList("a,b,c"), Files.readAllLines(file));
        }

        @Test
        void anotherTestThatUsesTheSameTempDir() {
            // use sharedTempDir
        }

    }

    @SuppressWarnings("JUnitMalformedDeclaration")
    static class CleanupModeTest {

        @Test
        void fileTest(@TempDir(cleanup = ON_SUCCESS) Path tempDir) {
            // perform test
        }

    }

    @SuppressWarnings("JUnitMalformedDeclaration")
    static class TempDirFactoryTest {

        @Test
        void factoryTest(@TempDir(factory = Factory.class) Path tempDir) {
            assertTrue(tempDir.getFileName().toString().startsWith("factoryTest"));
        }

        static class Factory implements TempDirFactory {

            @Override
            public Path createTempDirectory(AnnotatedElementContext elementContext, ExtensionContext extensionContext)
                    throws IOException {
                return Files.createTempDirectory(extensionContext.getRequiredTestMethod().getName());
            }

        }

    }

    @SuppressWarnings("JUnitMalformedDeclaration")
    static class InMemoryTempDirTest {

        @Test
        void test(@TempDir(factory = JimfsTempDirFactory.class) Path tempDir) {
            // perform test
        }

        static class JimfsTempDirFactory implements TempDirFactory {

            private final FileSystem fileSystem = Jimfs.newFileSystem(Configuration.unix());

            @Override
            public Path createTempDirectory(AnnotatedElementContext elementContext, ExtensionContext extensionContext)
                    throws IOException {
                return Files.createTempDirectory(fileSystem.getPath("/"), "junit-");
            }

            @Override
            public void close() throws IOException {
                fileSystem.close();
            }

        }

    }

    @Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER })
    @Retention(RetentionPolicy.RUNTIME)
    @TempDir(factory = InMemoryTempDirTest.JimfsTempDirFactory.class)
    @interface JimfsTempDir {
    }
    // end::user_guide_composed_annotation[]

    @SuppressWarnings("JUnitMalformedDeclaration")
    static
            // tag::user_guide_composed_annotation_usage[]
    class JimfsTempDirAnnotationTest {

        @Test
        void test(@JimfsTempDir Path tempDir) {
            // perform test
        }

    }

}
