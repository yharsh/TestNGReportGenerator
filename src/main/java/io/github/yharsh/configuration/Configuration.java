package io.github.yharsh.configuration;

import io.github.yharsh.Const;

/**
 * Created by YHarsh on 8/22/2017.
 */
public class Configuration {
    protected Const.REPORT_MODE mode;
    protected String reportsDir;
    protected String reportFileName;

    public void setMode(Const.REPORT_MODE mode) {
        this.mode = mode;
    }

    public void setReportsDir(String reportsDir) {
        this.reportsDir = reportsDir;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public Const.REPORT_MODE getReportMode() {
        return mode;
    }

    public String getReportsDir() {
        return this.reportsDir;
    }

    public String getReportFileName() {
        return this.reportFileName;
    }

    public Configuration(Const.REPORT_MODE mode, String reportsDir, String reportFileName) {
        this.reportFileName = reportFileName;
        this.mode = mode;
        this.reportsDir = reportsDir;
    }

    public Configuration(String mode, String reportsDir, String reportFileName) {
        this.reportsDir = reportsDir;
        this.reportFileName = reportFileName;
        this.mode = Const.REPORT_MODE.valueOf(mode);
    }
}
