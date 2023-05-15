
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class NormalLevel extends LvlLevel {

    public NormalLevel(String path) {
        super(path);
    }

    public void loadLevel(String path) {
        try {
            BufferedImage image = ImageIO.read(StartLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);
        } catch (IOException ex) {
            Logger.getLogger(StartLevel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
