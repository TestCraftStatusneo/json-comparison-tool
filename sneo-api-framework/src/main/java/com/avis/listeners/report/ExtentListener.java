package com.avis.listeners.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.avis.dataprovider.TestmethodData;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

/**
 *  ExtentListener for Extent Reporting
 *
 * @author ikumar
 */
public class ExtentListener implements ITestListener {

	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public void onTestStart(ITestResult result) {
	}

	public void logParameters(ITestResult result){
		if (result.getParameters().length > 0) {
			for (int i = 0; i < result.getParameters().length; i++) {
				if (result.getParameters()[0] instanceof TestmethodData){
					String desc = "Description "+": "+((TestmethodData) result.getParameters()[0]).getDescription();
					test.get().info(desc);
				}
				else
					test.get().info("Parameter "+(i+1)+": " +result.getParameters()[i].toString());

			}
		}
	}

	private String getTestMethodParameters(ITestResult result) {
		if (result.getParameters().length > 0 && !(result.getParameters()[0] instanceof TestmethodData)) {
			return Arrays.toString(result.getParameters());
		} else
			return "";
	}

	public void onTestSuccess(ITestResult result) {
		test.set(extent.createTest( result.getTestClass().getName() + ": " +result.getMethod().getMethodName() + " " + getTestMethodParameters(result)));
		logParameters(result);
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Testcase: " + methodName + " Passed" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.get().pass(m);
	}

	public void onTestFailure(ITestResult result) {
		test.set(extent.createTest( result.getTestClass().getName() + ": " +result.getMethod().getMethodName() + " " + getTestMethodParameters(result)));
		logParameters(result);

		String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
		String throwableString = result.getThrowable().toString();

		test.get().fail(throwableString);
		test.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occurred:Click to see"
				+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Testcase: " + methodName + " Failed" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.get().fail(m);

	}

	public void onTestSkipped(ITestResult result) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {
		if (extent != null)
			extent.flush();
	}

	public static void logCodeBlock(String code){
		Markup m = MarkupHelper.createCodeBlock(code);
		ExtentListener.test.get().info(m);
	}

}
