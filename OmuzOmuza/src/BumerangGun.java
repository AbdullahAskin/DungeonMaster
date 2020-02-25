
public class BumerangGun extends Projectile {

    public static int damage = 6;
    private static int speed = 4;
    public static int fireRate = 1;
    protected int range = 250;
    protected int bouncingNumber = 3;

    protected double nx, ny;

    public BumerangGun(int x, int y, double dir) {
        super(x, y);
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.sprite = Sprite.projectile_bouncingGun;
        nx = speed * Math.cos(dir);
        ny = speed * Math.sin(dir);
    }

    public void update() {
        if (bouncingNumber > 0) {
//            if (level.tileCollisionBouncing(x, y, 10, 3, 4, this)) {
//                bouncingNumber--;
//                LvlLevel.spawn_level.add(new Particle((int) x + 5, (int) y + 7, 50, 50, Sprite.wizardParticle2));
//            }
//            if (level.machineCollision(x, y, 10, 3, 4)) {
//                remove();
//                LvlLevel.spawn_level.add(new Particle((int) x + 5, (int) y + 7, 75, 50, Sprite.magicParticle));
//            }
        } else {
            remove();
        }
        move();
    }

    public void move() {
        if (distance() > range) {
            remove();
            return;
        }
        x += nx;
        y += ny;
    }

    public void bouncingDirection(int i) {
        if (i == 0 || i == 2) {
            ny = -ny;
        } else if (i == 1 || i == 3) {
            nx = -nx;
        }
    }

    public void render(Screen screen) {
        screen.renderProjectile((int) x, (int) y, this);
    }
}
