
public class Particle extends Entity {

    private Sprite sprite;
    private double xx, yy, zz;
    private double xa, ya, za;
    private int life;
    private int time = 0;

    public Particle(int x, int y, int life, Sprite sprite) {
        this.sprite = sprite;
        this.xx = x;
        this.yy = y;
        this.life = life + random.nextInt(75);
        xa = random.nextGaussian();
        ya = random.nextGaussian();
        zz = random.nextFloat() + 2;
        LvlLevel.map_1.add(this);
    }

    public Particle(int x, int y, int amount, int life, Sprite sprite) {
        this(x, y, life, sprite);
        for (int i = 0; i < amount; i++) {
            LvlLevel.map_1.add(new Particle(x, y, life, sprite));
        }
    }

    public void render(Screen screen) {
        screen.renderSprite((int) (xx + xa), (int) ((yy + ya) - (zz + za)), sprite);
    }

    public void update() {
        if (time < life) {

            time++;
        } else {
            this.remove();
        }
        za -= 0.1;
        if (za < 0) {
            if (zz < 0) {
                zz = 0;
                za *= -0.55;
                xa *= 0.6;
                ya *= 0.6;
            }
        }
        move();
    }

    public void move() {
        xx += xa;
        yy += ya;
        zz += za;
    }
}
