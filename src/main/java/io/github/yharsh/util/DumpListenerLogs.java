package io.github.yharsh.util;

import io.github.yharsh.Const;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;

/**
 * Created by YHarsh on 8/15/2017.
 */
public class DumpListenerLogs {

    //ToDo Need to change this with appropriate code
    public static void dumpLogInfo(String caller, Object object) {
        if (System.getenv(Const.JUNIT_LOGGING) != null) {
            File file = new File("junitrun.log");
            OutputStreamWriter writer = null;
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                writer = new OutputStreamWriter(new BufferedOutputStream(fileOutputStream));

                StringBuilder builder = new StringBuilder();

                if (object instanceof Description) {
                    builder.append(getDescrptionAsString((Description) object, caller));
                } else if (object instanceof Failure) {
                    builder.append(getFailureAsString((Failure) object, caller));
                } else if (object instanceof Result) {
                    builder.append(getResultAsString((Result) object, caller));
                }
                else if(object instanceof String)
                {
                    builder.append(object);
                }

                writer.write(builder.toString());
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static StringBuilder getResultAsString(Result result, String caller) {
        StringBuilder builder = new StringBuilder("\n\nCALLER: " + caller);
        builder.append("\n-----------------RESULT STARTS----------------\n")
                .append("\nResult statistics")
                .append("\nFailure count: " + result.getFailureCount())
                .append("\nIgnore count: " + result.getIgnoreCount())
                .append("\nRun count: " + result.getRunCount())
                .append("\nRun time: " + result.getRunTime())
                .append("\nWas successful: " + result.wasSuccessful())
                .append("\n-----------------RESULT ENDS----------------\n");

        return builder;
    }

    private static StringBuilder getDescrptionAsString(Description description, String caller) {
        StringBuilder builder = new StringBuilder("\n\nCALLER: " + caller);

        builder.append("\n---------------Description Starts--------------\n")
                .append("\nISTEST: " + description.isTest())
                .append("\nISSUITE: " + description.isSuite())
                .append("\nISEMPTY: " + description.isEmpty())
                .append("\nDISPLAY_NAME: " + description.getDisplayName())
                .append("\nMETHOD_NAME: " + description.getMethodName())
                .append("\nTEST_COUNT: " + description.testCount())
                .append("\nCLASS_NAME: " + description.getClassName())
                .append("\n---------------Description Ends--------------\n");

        return builder;
    }

    private static StringBuilder getFailureAsString(Failure failure, String caller) {
        StringBuilder builder = new StringBuilder("\n\nCALLER: " + caller);
        builder.append("\n---------------FAILURE STARTS----------------\n")
                .append("\nFAILURE DESCRIPTION: " + getDescrptionAsString(failure.getDescription(), caller))
                .append("\nFAILURE EXCEPTION: " + failure.getException())
                .append("\nFAILURE MESSAGE: " + failure.getMessage())
                .append("\nFAILURE TEST HEADER: " + failure.getTestHeader())
                .append("\nFAILURE TRACE: " + failure.getTrace())
                .append("\n----------------FAILURE ENDS--------------------\n");

        return builder;
    }
}
