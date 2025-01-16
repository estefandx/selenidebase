package selenidebase.utils;

import com.codeborne.selenide.Selenide;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtil {


    private static final String SCREENSHOTS_FOLDER = "test-screenshots";
    private static final String DATE_FORMAT = "yyyy-MM-dd_HH-mm-ss";

    public static void takeScreenshot(String screenshotPath) {

        String filePath = Selenide.screenshot(screenshotPath);
        System.out.println("Screenshot saved to: " + filePath);
    }

    public static String generateScreenshotFilename(String testName, String resultStatus) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String timestamp = LocalDateTime.now().format(formatter);


        String fileName = SCREENSHOTS_FOLDER + "/" + testName + "_" + resultStatus + "_" + timestamp;

        try {
            Files.createDirectories(Paths.get(SCREENSHOTS_FOLDER));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }
}