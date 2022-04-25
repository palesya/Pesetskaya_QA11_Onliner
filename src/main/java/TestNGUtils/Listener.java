package TestNGUtils;

import Properties.PropertyReader;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import static BaseObjects.DriverCreation.createDriver;
import static Properties.PropertyReader.getProperties;

public class Listener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.setProperty("logger_time",getSimpleDate());
        new PropertyReader(context.getSuite().getParameter("config") == null ? System.getProperty("config") : context.getSuite().getParameter("config"));
        Properties properties = getProperties();
        createDriver(properties.getProperty("browser") == null ? "chrome" : properties.getProperty("browser"));
    }

    private String getSimpleDate() {
        return new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
    }

}
