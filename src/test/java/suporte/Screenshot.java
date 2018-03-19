package suporte;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Screenshot {

    public static void tirarPrint(WebDriver navegador, String arquivo){
        File screenshot = ((TakesScreenshot)navegador).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshot, new File(arquivo));
        } catch (IOException e) {
            System.out.println("Problema ao copiar arquivo para pasta: " + e.getMessage());
        }


    }
}
