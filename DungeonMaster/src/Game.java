
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {

    public static int width = 300;
    public static int height = 168;
    public static int scale = 3;
    protected static int xScroll, yScroll;

    public int frames = 0, updates = 0;
    public int radius = 75; // For dark mode

    private boolean running = false;
    Thread thread;
    JFrame jframe;
    Screen screen;
    Keyboard keyboard;
    Player player;
    LvlLevel level;
    Mouse mouse;
    public static UIManager uiManager;

    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {
        jframe = new JFrame();

        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);
        screen = new Screen(width, height);
        uiManager = new UIManager();
        keyboard = new Keyboard(this);
        mouse = new Mouse();
        player = new Player(3 * 16, 10 * 16, keyboard);
        level = level.map_1;
        level.add(player);
        player.init(level);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addKeyListener(keyboard);
        requestFocus();
        setFocusable(true);
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Display");
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        final double ns = 1000000000.0 / 60.0;
        long timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double delta = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                lastTime = System.nanoTime();
                update();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                jframe.setTitle("Rain | ups  " + updates + ", fps " + frames);
                timer = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }

        }
        stop();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        screen.clear();
        xScroll = (int) player.x - screen.width / 2;
        yScroll = (int) player.y - screen.height / 2;
        level.render(xScroll, yScroll, screen);
//        for (int i = 0; i < pixels.length; i++) {
//            pixels[i] = screen.pixels[i];
//        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
//                if (!darkMode(x, y)) {
                pixels[x + y * width] = screen.pixels[x + y * width];
//                } else {
//                    pixels[x + y * width] = 0x000000;
//                }
            }
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        uiManager.render(g);
        g.dispose();
        bs.show();
    }

    public void update() {
        keyboard.update();
        level.update();
        uiManager.update();
    }

    public static int getWindowWidth() {
        return width * scale;
    }

    public static int getWindowHeight() {
        return height * scale;
    }

    public static int getxOffset() {
        return xScroll * scale;
    }

    public static int getyOffset() {
        return yScroll * scale;
    }

    public static UIManager getUiManager() {
        return uiManager;
    }

    public boolean darkMode(int x, int y) {
        boolean dark = true;
        if (Math.sqrt((width / 2 - x) * (width / 2 - x) + (height / 2 - y) * (height / 2 - y)) < radius) {
            dark = false;
        }
        return dark;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.jframe.add(game);
        game.jframe.setResizable(false);
        game.jframe.setVisible(true);
        game.jframe.setTitle("Rain");
        game.jframe.pack();
        game.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.jframe.setLocationRelativeTo(null);

        game.start();
    }
}
