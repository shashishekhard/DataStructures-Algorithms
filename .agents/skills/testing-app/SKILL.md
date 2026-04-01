# Testing DSA Algorithms

## Build
No Maven/Gradle. Compile directly with javac:
```bash
javac -d /tmp/classes src/main/java/com/practice/dsalgo/sorting/*.java
javac -d /tmp/classes src/main/java/com/practice/dsalgo/searching/*.java
```

## Run
Each algorithm has a `main()` method with demo data:
```bash
java -cp /tmp/classes com.practice.dsalgo.sorting.MergeSort
java -cp /tmp/classes com.practice.dsalgo.searching.BinarySearch
```

## Testing approach
- No unit test framework. Each file uses `main()` for demonstration.
- Write edge case harnesses as standalone Java files importing the sort/search classes.
- Standard edge cases: empty array, single element, already sorted, reverse sorted, all duplicates.
- QuickSort uses interactive `Scanner` input — pipe input or skip in automated tests.
- CountingSort returns a new array (not in-place) — test with return value.
- CountingSort and RadixSort do not support negative integers.

## Java version
- Java 17 (`javac 17.0.13`)
