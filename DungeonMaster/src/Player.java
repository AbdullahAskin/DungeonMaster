
import java.awt.Color;

public class Player extends Mob {

    private Keyboard input;
    private UIManager ui;
    private int anim = 0;
    private int fireRate = 0;

    public Player(int x, int y, Keyboard input) {
        this.x = x;
        this.y = y;
        this.input = input;
        this.level = LvlLevel.map_1;
        ui = Game.uiManager;
//        UIPanel panel = new UIPanel(new Vector2i(220 * 3, 0), new Vector2i(80 * 3, 168 * 3), new Color(0xf0fffff, true));
//        UILabel nameLabel = new UILabel(new Vector2i(220 * 3, 0), new Vector2i(40, 200), new java.awt.Font("Verdana", java.awt.Font.PLAIN, 24), new Color(0xbbbbbb), "émilié");
//        panel.add(nameLabel);
//        ui.add(panel);
    }

    public void render(Screen screen) {
        if (dir == 0) {
            sprite = Sprite.player_forward;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_forward_1;
                } else {
                    sprite = Sprite.player_forward_2;
                }
            }
        }
        if (dir == 1 || dir == 3) {
            sprite = Sprite.player_side;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_side_1;
                } else {
                    sprite = Sprite.player_side_2;
                }
            }
        }
        if (dir == 2) {
            sprite = Sprite.player_back;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.player_back_1;
                } else {
                    sprite = Sprite.player_back_2;
                }
            }
        }
        screen.renderPlayer((int) x - 16, (int) y - 16, sprite, dir);
        screen.renderMiniMap(new Vector2i((int) x, (int) y), new Vector2i(220, 0), LvlLevel.map_1.tiles, LvlLevel.map_1.width);
//        screen.render(new Vector2i((int) x, (int) y), new Vector2i(210, 10), LvlLevel.map_1.tiles, 30);
    }

    public void update() {
        int xa = 0, ya = 0;

        if (fireRate > 0) {
            fireRate--;
        }
        if (anim < 7500) {
            anim++;
        } else {
            anim = 0;
        }

        if (input.up) {
            ya--;
        }
        if (input.down) {
            ya++;

        }
        if (input.right) {
            xa++;
        }
        if (input.left) {
            xa--;
        }

        updateShooting(xa, ya);

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }

    }

    public void updateShooting(int xa, int ya) {
        if (Mouse.getButton() == 1 && fireRate <= 0) {
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);
            fireRate = DefaultGun.fireRate;
            shoot((int) x, (int) y, dir, xa, ya);
        }
    }

}
