package PageObjects.Yandex;

import PageObjects.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Log4j
public class HomePage extends BasePage {

    private By searchField = By.cssSelector("input[class$='input']");
    private By lensIcon = By.cssSelector("[class$='button-icon']");
    private By selectFile = By.cssSelector("[class*='Buttons'] button");
    private By textInput = By.cssSelector("input[class*='Textinput']");
    private By submitOnForm = By.cssSelector("form[class*='Panel'] [type='submit']");
    private By uploadedPicture = By.cssSelector("[class$='yes'] [class*='title']");
    private By submitButton = By.cssSelector("[class$='button'] [type='submit']");

    public HomePage uploadPicture(String filepath) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("onliner.png"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        click(lensIcon);
        click(selectFile);

        return this;
    }

}
