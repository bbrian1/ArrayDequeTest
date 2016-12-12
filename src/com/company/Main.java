package com.company;

import java.util.*;


public class Main {

    /**
     * The purpose of this little application is to run some tests and output the results for the
     * LinkedList and ArrayDeque collections. The objects I'll be using to store in the ArrayDeque
     * and LinkedList will be strings.
     *
     * Test length measure in ms
     *
     * @param args
     */

    private static final int TEST_ITERATIONS = 100;
    private static final int STRING_LENGTH = 500;
    private static final int MAX_TEST_PARAM_SIZE = 10000000;

    private enum ResultValues {MIN, MAX, AVG};

    public static void main(String[] args) {
        // Making three lists so I can output comma-seperated values
        List<TestResults> arrayDequeResults = new ArrayList<>();
        List<TestResults> linkedListResults = new ArrayList<>();
        List<String> numberOfParams = new ArrayList<>();

        for(int i = 100000; i < MAX_TEST_PARAM_SIZE; i += 100000){
            System.out.printf("Starting tests with param count %d%n", i);
            String[] testData = generateTestParams(STRING_LENGTH, i);
            DequeTester<String> arrayDequeTest = new DequeTester<>(new ArrayDeque<String>(), testData, TEST_ITERATIONS);
            TestResults arrayDequeTestResults = arrayDequeTest.runTest();
            arrayDequeTestResults.dequeType = "ArrayDeque";
            arrayDequeTestResults.paramCount = i;

            arrayDequeResults.add(arrayDequeTestResults);

            DequeTester<String> linkedListTest = new DequeTester<>(new LinkedList<>(), testData, TEST_ITERATIONS);
            TestResults linkedListTestResults = linkedListTest.runTest();
            linkedListTestResults.dequeType = "LinkedList";
            linkedListTestResults.paramCount = i;

            linkedListResults.add(linkedListTestResults);
            numberOfParams.add(String.valueOf(i));
        }

        String paramCSV = String.join(",", numberOfParams);
        String arrayDequeMinCSV = printCSV(arrayDequeResults, ResultValues.MIN);
        String arrayDequeMaxCSV = printCSV(arrayDequeResults, ResultValues.MAX);
        String arrayDequeAvgCSV = printCSV(arrayDequeResults, ResultValues.AVG);

        String linkedListMinCSV = printCSV(linkedListResults, ResultValues.MIN);
        String linkedListMaxCSV = printCSV(linkedListResults, ResultValues.MAX);
        String linkedListAvgCSV = printCSV(linkedListResults, ResultValues.AVG);

        System.out.println("Params," + paramCSV);
        System.out.println("ArrayDequeMin," + arrayDequeMinCSV);
        System.out.println("ArrayDequeMax," + arrayDequeMaxCSV);
        System.out.println("ArrayDequeAvg," + arrayDequeAvgCSV);
        System.out.println("LinkedListMin," + linkedListMinCSV);
        System.out.println("LinkedListMax," + linkedListMaxCSV);
        System.out.println("LinkedListAvg," + linkedListAvgCSV);
    }

    private static String[] generateTestParams(int strLen, int paramCount){
        char[] chars = new char[strLen];
        Arrays.fill(chars, 'a');

        String[] params = new String[paramCount];
        Arrays.fill(params, new String(chars));

        return params;
    }

    private static String printCSV(List<TestResults> results, ResultValues valueEnum){
        List<String> stringValues = new ArrayList<>();
        for(TestResults result : results){
            switch(valueEnum){
                case AVG:
                    stringValues.add(String.valueOf(result.avg));
                    break;
                case MIN:
                    stringValues.add(String.valueOf(result.min));
                    break;
                case MAX:
                    stringValues.add(String.valueOf(result.max));
                    break;
            }

        }

        return String.join(",", stringValues);
    }
}
