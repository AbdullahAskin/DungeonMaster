
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class StartLevel extends LvlLevel {

    public StartLevel(String path) {
        super(path);
//        add(new Machine(9, 14));
//        add(new Machine(15, 14));
//        add(new Machine(25, 14));
//        add(new DummyZombie(9, 9));

    }

    public void loadLevel(String path) {
        try {

            BufferedImage image = ImageIO.read(StartLevel.class.getResource(path));
            this.width = width = image.getWidth();
            this.height = height = image.getHeight();
            tiles = new int[width * height];
            image.getRGB(0, 0, width, height, tiles, 0, width);
        } catch (IOException ex) {
            Logger.getLogger(StartLevel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
