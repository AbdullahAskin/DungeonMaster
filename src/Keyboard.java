
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    Game game = null;

    private boolean[] keys = new boolean[120];
    public boolean up, down, left, right;
    public static int WeaponNumber = 1;

    public Keyboard(Game game) {
        this.game = game;
    }

    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        if (e.getKeyCode() == KeyEvent.VK_1) {
            WeaponNumber = 1;
        } else if (e.getKeyCode() == KeyEvent.VK_2) {
            WeaponNumber = 2;
        } else if (e.getKeyCode() == KeyEvent.VK_3) {
            WeaponNumber = 3;
        } else if (e.getKeyCode() == KeyEvent.VK_4) {
            WeaponNumber = 4;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

}
