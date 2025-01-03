package cn.shaojiel.junit5.annotation.parameterized;

import cn.shaojiel.junit5.domain.Person;
import cn.shaojiel.junit5.util.StringUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.junit.jupiter.params.provider.EnumSource.Mode.MATCH_ALL;

class ParameterizedTestDemo {

    /**
     * The following types of literal values are supported by @ValueSource.
     * • short
     * • byte
     * • int
     * • long
     * • float
     * • double
     * • char
     * • boolean
     * • java.lang.String
     * • java.lang.Class
     *
     */

    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    void palindromes(String candidate) {
        assertTrue(StringUtils.isPalindrome(candidate));
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", " ", "\t", "\n" })
    void nullEmptyAndBlankStrings(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { " ", " ", "\t", "\n" })
    void nullEmptyAndBlankStrings_(String text) {
        assertTrue(text == null || text.trim().isEmpty());
    }

    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithEnumSource(TemporalUnit unit) {
        assertNotNull(unit);
    }

    @ParameterizedTest
    @EnumSource
    void testWithEnumSourceWithAutoDetection(ChronoUnit unit) {
        assertNotNull(unit);
    }

    @ParameterizedTest
    @EnumSource(names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(ChronoUnit unit) {
        assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit));
    }

    @ParameterizedTest
    @EnumSource(mode = EXCLUDE, names = { "ERAS", "FOREVER" })
    void testWithEnumSourceExclude(ChronoUnit unit) {
        assertFalse(EnumSet.of(ChronoUnit.ERAS, ChronoUnit.FOREVER).contains(unit));
    }

    @ParameterizedTest
    @EnumSource(mode = MATCH_ALL, names = "^.*DAYS$")
    void testWithEnumSourceRegex(ChronoUnit unit) {
        assertTrue(unit.name().endsWith("DAYS"));
    }

    @ParameterizedTest
    @MethodSource("stringProvider")
    void testWithExplicitLocalMethodSource(String argument) {
        assertNotNull(argument);
    }
    static Stream<String> stringProvider() {
        return Stream.of("apple", "banana");
    }

    @ParameterizedTest
    @MethodSource
    void testWithDefaultLocalMethodSource(String argument) {
        assertNotNull(argument);
    }
    static Stream<String> testWithDefaultLocalMethodSource() {
        return Stream.of("apple", "banana");
    }


    /**
     * Streams for primitive types (DoubleStream, IntStream, and LongStream) are also supported
     */
    @ParameterizedTest
    @MethodSource("range")
    void testWithRangeMethodSource(int argument) {
        assertNotEquals(9, argument);
    }
    static IntStream range() {
        return IntStream.range(0, 20).skip(10);
    }


    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("apple", 1, Arrays.asList("a", "b")),
                arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    @ParameterizedTest
    @CsvSource({
            "apple, 1",
            "banana, 2",
            "'lemon, lime', 0xF1",
            "strawberry, 700_000"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            FRUIT, RANK
            apple, 1
            banana, 2
            'lemon, lime', 0xF1
            strawberry, 700_000
            """)
    void testWithCsvSourceTextBlock(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest
    @CsvSource(delimiter = '|', quoteCharacter = '"', textBlock = """
            #-----------------------------
            #   FRUIT | RANK
            #-----------------------------
                apple | 1
            #-----------------------------
                banana | 2
            #-----------------------------
                "lemon lime" | 0xF1
            #-----------------------------
                strawberry | 700_000
            #-----------------------------
            """)
    void testWithCsvSourceTextBlock2(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/two-column.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromClasspath(String country, int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/two-column.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromFile(String country, int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvFileSource(resources = "/two-column.csv", useHeadersInDisplayName = true)
    void testWithCsvFileSourceAndHeaders(String country, int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

    /*
     @ArgumentsSource can be used to specify a custom, reusable ArgumentsProvider. Note that an
     implementation of ArgumentsProvider must be declared as either a top-level class or as a static
     nested class.

     If you wish to implement a custom ArgumentsProvider that also consumes an annotation
     (like builtin providers such as ValueArgumentsProvider or CsvArgumentsProvider), you have the possibility to
     extend the AnnotationBasedArgumentsProvider class.

     */

    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        assertNotNull(argument);
    }

    static class MyArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of("apple", "banana").map(Arguments::of);
        }
    }

    /*
    Explicit Conversion
    Instead of relying on implicit argument conversion you may explicitly specify an ArgumentConverter
    to use for a certain parameter using the @ConvertWith annotation like in the following example. Note
    that an implementation of ArgumentConverter must be declared as either a top-level class or as a
    static nested class.

    If the converter is only meant to convert one type to another, you can extend
    TypedArgumentConverter to avoid boilerplate type checks.
     */

    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithExplicitArgumentConversion(
            @ConvertWith(ToStringArgumentConverter.class) String argument) {
        assertNotNull(ChronoUnit.valueOf(argument));
    }

    static class ToStringArgumentConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) {
            assertEquals(String.class, targetType, "Can only convert to String");
            if (source instanceof Enum<?>) {
                return ((Enum<?>) source).name();
            }
            return String.valueOf(source);
        }
    }

    static class ToLengthArgumentConverter extends TypedArgumentConverter<String, Integer>
    {
        protected ToLengthArgumentConverter() {
            super(String.class, Integer.class);
        }
        @Override
        protected Integer convert(String source) {
            return (source != null ? source.length() : 0);
        }
    }

    /*
    Explicit argument converters are meant to be implemented by test and extension authors. Thus,
    junit-jupiter-params only provides a single explicit argument converter that may also serve as a
    reference implementation: JavaTimeArgumentConverter. It is used via the composed annotation
    JavaTimeConversionPattern.

    If you wish to implement a custom ArgumentConverter that also consumes an annotation (like
    JavaTimeArgumentConverter), you have the possibility to extend the AnnotationBasedArgumentConverter
    class.
     */

    @ParameterizedTest
    @ValueSource(strings = { "01.01.2017", "31.12.2017" })
    void testWithExplicitJavaTimeConverter(
            @JavaTimeConversionPattern("dd.MM.yyyy") LocalDate argument) {
        assertEquals(2017, argument.getYear());
    }


    /*
    By default, each argument provided to a @ParameterizedTest method corresponds to a single method
    parameter. Consequently, argument sources which are expected to supply a large number of
    arguments can lead to large method signatures.
    In such cases, an ArgumentsAccessor can be used instead of multiple parameters. Using this API, you
    can access the provided arguments through a single argument passed to your test method. In
    addition, type conversion is supported as discussed in Implicit Conversion.
    Besides, you can retrieve the current test invocation index with
    ArgumentsAccessor.getInvocationIndex().
     */

    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAccessor(ArgumentsAccessor arguments) {
        Person person = new Person(arguments.getString(0),
                arguments.getString(1),
                arguments.get(2, Person.Gender.class),
                arguments.get(3, LocalDate.class));
        if (person.getFirstName().equals("Jane")) {
            assertEquals(Person.Gender.F, person.getGender());
        }
        else {
            assertEquals(Person.Gender.M, person.getGender());
        }
        assertEquals("Doe", person.getLastName());
        assertEquals(1990, person.getDateOfBirth().getYear());
    }

    @ParameterizedTest
    @CsvSource({
            "Jane, Doe, F, 1990-05-20",
            "John, Doe, M, 1990-10-22"
    })
    void testWithArgumentsAggregator(@AggregateWith(PersonAggregator.class) Person person)
    {
        // perform assertions against person
    }
    
    static class PersonAggregator implements ArgumentsAggregator {
        @Override
        public Person aggregateArguments(ArgumentsAccessor arguments, ParameterContext
                context) {
            return new Person(arguments.getString(0),
                    arguments.getString(1),
                    arguments.get(2, Person.Gender.class),
                    arguments.get(3, LocalDate.class));
        }
    }



}
