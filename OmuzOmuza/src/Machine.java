
public class Machine extends Entity {

    private int range = 250;
    private int fireRate = 0;
    private double dx, dy;
    private int dir = 0;
    private double dist;
    protected int hp = 96;
    protected int remainingHp = 96;

    public Machine(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.machine_left;
    }

    public void render(Screen screen) {
        direction();
        screen.renderMachine((int) x, (int) y, sprite, dir);
        screen.renderHpSprite((int) x, (int) y, Sprite.machine_healthbar, this);
        screen.drawRect((int) x-1, (int) y-7, 32, 4,0xffffff, true);

    }

    public void update() {
        if (fireRate > 0) {
            fireRate--;
        } else if (dist < range && fireRate <= 0) {
            level.add(new MachineProjectile((int) x + 10, (int) y + 10));
            fireRate = MachineProjectile.fireRate;
        }
    }

    public void takingDamage() {
        remainingHp -= DefaultGun.damage;
        if (remainingHp <= 0) {
            remove();
            LvlLevel.map_1.add(new Particle((int) x + 16, (int) y + 16, 150, 100, Sprite.deathParticle));
        }
    }

    public void direction() {
        dx = LvlLevel.map_1.getPlayer(0).x - x - 8;
        dy = LvlLevel.map_1.getPlayer(0).y - y - 8;
        dist = Math.sqrt((dx * dx) + (dy * dy));
        double degree = Math.toDegrees(Math.atan2(dy, dx));
        if (degree > 135 || degree <= -135) {
            dir = 1;
        } else if (degree > -135 && degree <= -45) {
            dir = 2;
        } else if (degree > -45 && degree <= 45) {
            dir = 3;
        } else if (degree > 45 && degree <= 135) {
            dir = 0;
        }

        if (dir == 1 || dir == 3) {
            sprite = Sprite.machine_left;
        } else {
            sprite = Sprite.machine_up;
        }
    }

}
