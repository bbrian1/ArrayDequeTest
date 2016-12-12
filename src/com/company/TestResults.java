package com.company;

/**
 * Created by brianfbogard on 12/10/16.
 */
public class TestResults {
    public final long max;
    public final long min;
    public final long avg;
    public String dequeType = "N/A";
    public int paramCount = 0;

    public TestResults(long min, long max, long avg){
        this.min = min;
        this.max = max;
        this.avg = avg;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Deque Type: ");
        sb.append(dequeType);
        sb.append("\n");

        sb.append("Number of Objects: ");
        sb.append(String.valueOf(paramCount));
        sb.append("\n");

        sb.append("Avg Time: ");
        sb.append(String.valueOf(avg));
        sb.append("\n");

        sb.append("Min Time: ");
        sb.append(String.valueOf(min));
        sb.append("\n");

        sb.append("Max Time: ");
        sb.append(String.valueOf(max));
        sb.append("\n");

        return sb.toString();
    }

}
