
public class DefaultGun extends Projectile {

    protected double nx, ny;
    public static int fireRate = 10;
    private int speed = 4;
    public static int damage = 9;
    protected int range = 200;

    public DefaultGun(int x, int y, double dir, int xa, int ya) {
        super(x, y);
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.sprite = Sprite.rotate(Sprite.projectile_defaultGun, dir);
        nx = speed * Math.cos(dir) + xa / 2;
        ny = speed * Math.sin(dir) + ya / 2;
    }

    public void update() {
        if (level.tileCollision(x, y, 10, 3, 4)) {
            remove();
            LvlLevel.map_1.add(new Particle((int) x + 5, (int) y + 7, 50, 50, Sprite.magicParticle));
        }
        if (level.machineCollision(x, y, 10, 3, 4)) {
            remove();
            LvlLevel.map_1.add(new Particle((int) x + 5, (int) y + 7, 75, 50, Sprite.magicParticle));
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

    public void render(Screen screen) {
        screen.renderProjectile((int) x, (int) y, this);
    }

}
