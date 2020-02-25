
public class MagicGun extends Projectile {

    public static int damage = 15;
    private static int speed = 1;
    public static int fireRate = 10;
    protected int range = 200;

    protected double nx, ny;

    public MagicGun(int x, int y, double dir) {
        super(x, y);
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.sprite = Sprite.projectile_magicGun;
        nx = speed * Math.cos(dir);
        ny = speed * Math.sin(dir);
    }

    public void update() {
        if (level.tileCollision(x, y, 16, 8, 8)) {
            remove();
            LvlLevel.map_1.add(new Particle((int) x + 5, (int) y + 7,70, 50, Sprite.gunThreeParticleBlue));
            LvlLevel.map_1.add(new Particle((int) x + 5, (int) y + 7, 60, 50, Sprite.gunThreeParticleOrange));
        }
        if (level.machineCollision(x, y, 10, 3, 4)) {
            remove();
            LvlLevel.map_1.add(new Particle((int) x + 13, (int) y + 20, 75, 50, Sprite.gunThreeParticleBlue));
        }
        move();
    }

    public void move() {
//        LvlLevel.map_1.add(new Particle((int) x + 16, (int) y + 16, 15, 20, Sprite.disappearParticle));
        if (distance() > range) {
            remove();
            return;
        }
        x += nx;
        y += ny;
    }

    public void render(Screen screen) {
        screen.renderDisappearProjectile((int) x, (int) y, 3, this);
    }
}
