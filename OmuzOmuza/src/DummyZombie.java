
import java.util.List;

public class DummyZombie extends Mob {

    private int anim = 0;
    private double xa, ya;
    private double speed = 0.5;

    public DummyZombie(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        this.level = LvlLevel.map_1;
    }

    public void render(Screen screen) {
        if (dir == 0) {
            sprite = Sprite.zombie_forward;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.zombie_forward_1;
                } else {
                    sprite = Sprite.zombie_forward_2;
                }
            }
        }
        if (dir == 1 || dir == 3) {
            sprite = Sprite.zombie_side;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.zombie_side_1;
                } else {
                    sprite = Sprite.zombie_side_2;
                }
            }
        }
        if (dir == 2) {
            sprite = Sprite.zombie_back;
            if (walking) {
                if (anim % 20 > 10) {
                    sprite = Sprite.zombie_back_1;
                } else {
                    sprite = Sprite.zombie_back_2;
                }
            }
        }
        screen.renderPlayer((int) x - 16, (int) y - 16, sprite, dir);
    }

    public void update() {
        move();
        if (anim < 7500) {
            anim++;
        } else {
            anim = 0;
        }
        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }
    }

    private void move() {
        xa = 0;
        ya = 0;

        List<Player> players = level.getPlayers(this, 50);
        if (players.size() > 0) {
            Player player = players.get(0);
            if (x < player.getX()) {
                xa += speed;
            }
            if (x > player.getX()) {
                xa -= speed;
            }
            if (y < player.getY()) {
                ya += speed;
            }
            if (y > player.getY()) {
                ya -= speed;
            }
        }
    }

}
