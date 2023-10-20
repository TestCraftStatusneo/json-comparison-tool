package com.bt.helper;

import com.avis.core.Configuration;
import com.avis.core.TestBase;
import com.avis.listeners.report.ExtentListener;
import com.avis.listeners.report.ExtentManager;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

public class TestBaseExtn extends TestBase {

    @BeforeTest(alwaysRun = true)
    public void startTest(XmlTest xmlTest) {
        Configuration.setTestNGParameters(xmlTest);
        Configuration.setURL();
        ExtentListener.extent = ExtentManager.createInstance();
        OktaTokenHelper.storeOktaToken();
    }
}
