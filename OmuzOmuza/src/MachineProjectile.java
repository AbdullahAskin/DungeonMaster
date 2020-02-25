
public class MachineProjectile extends Projectile {

    protected double nx, ny;
    public static int fireRate = 10;
    private int speed = 2;
    private double dx, dy;

    public MachineProjectile(int x, int y) {
        super(x, y);
        this.x = x;
        this.y = y;
        this.sprite = Sprite.projectile_machine;
        this.range = 500;
        dx = LvlLevel.map_1.getPlayer(0).x - xOrigin - 8;
        dy = LvlLevel.map_1.getPlayer(0).y - yOrigin - 8;
        this.dir = Math.atan2(dy, dx);
        nx = speed * Math.cos(dir);
        ny = speed * Math.sin(dir);
    }

    public void update() {
        if (level.tileCollision(x, y, 10, 3, 4)) {
            remove();
            LvlLevel.map_1.add(new Particle((int) x + 5, (int) y + 7, 50, 50, Sprite.machineParticle));
        } else {
            move();
        }
    }

    public void move() {
        if (distance() > range) {
            remove();
            return;
        }
//        System.out.println("nx : " + nx);
        x += nx;
        y += ny;
    }

    public void render(Screen screen) {
        screen.renderProjectile((int) x, (int) y, this);
    }

}
