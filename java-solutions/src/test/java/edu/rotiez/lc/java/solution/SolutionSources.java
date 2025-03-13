package edu.rotiez.lc.java.solution;

import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import static org.junit.jupiter.params.provider.Arguments.of;

public class SolutionSources {

    public static Stream<Arguments> equalPairs() {
        return Stream.of(
            of(new int[][]{{1, 2}, {3, 4}}, 2),
            of(new int[][]{{1, 3, 2}, {3, 2, 1}}, 3)
        );
    }

    public static Stream<Arguments> twoSum() {
        return Stream.of(
            of(new int[]{2, 7, 11, 15}, 9, new int[]{0, 1}),
            of(new int[]{3, 2, 4}, 6, new int[]{1, 2}),
            of(new int[]{3, 3}, 6, new int[]{0, 1})
        );
    }
}
