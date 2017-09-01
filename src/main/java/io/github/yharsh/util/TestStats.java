package io.github.yharsh.util;

/**
 * Created by YHarsh on 8/23/2017.
 */
public class TestStats {
    private int passed;
    private int failed;
    private int ignored;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getIgnored() {
        return ignored;
    }

    public void setIgnored(int ignored) {
        this.ignored = ignored;
    }

    @Override
    public String toString() {
        return "TestStats{" +
                "passed=" + passed +
                ", failed=" + failed +
                ", ignored=" + ignored +
                ", total=" + total +
                '}';
    }
}
