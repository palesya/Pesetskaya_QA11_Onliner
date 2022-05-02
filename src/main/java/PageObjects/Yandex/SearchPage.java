package PageObjects.Yandex;

import PageObjects.BasePage;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

@Log4j
public class SearchPage extends BasePage {

    private By searchField = By.cssSelector("[name='text']");
    private By link = By.cssSelector("[href*='forum.onliner.by']");
    private By title = By.cssSelector("[class='CbirItem-Title']");
    private By foundData = By.cssSelector("[class*='name_sites']");


    public SearchPage uploadPicture() {
        Image image = Toolkit.getDefaultToolkit().createImage("onliner.png");
        write(image);
        click(searchField);
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL);
        actions.sendKeys("v");
        actions.keyUp(Keys.CONTROL);
        actions.build().perform();
        wait(title);
        return this;
    }

    public SearchPage checkIfLinkIsFound() throws InterruptedException {
        Thread.sleep(1000);
        scrollDown();
        Thread.sleep(1000);
        scrollDown();
        click(link);
        return this;
    }

    static class ImageTransferable implements Transferable {
        private Image image;
        public ImageTransferable(Image image) {
            this.image = image;
        }
        public Object getTransferData(DataFlavor flavor)
                throws UnsupportedFlavorException {
            if (isDataFlavorSupported(flavor)) {
                return image;
            } else {
                throw new UnsupportedFlavorException(flavor);
            }
        }
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor == DataFlavor.imageFlavor;
        }
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{DataFlavor.imageFlavor};
        }
    }

    public static void write(Image image) {
        if (image == null)
            throw new IllegalArgumentException("Image can't be null");
        ImageTransferable transferable = new ImageTransferable(image);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(transferable, null);
    }

}
