
public class Projectile extends Entity {

    protected double dir;
    protected double xOrigin, yOrigin;
    protected Sprite sprite;
    protected double x, y;
    protected int range = 50;
//    protected double xFrictionalForce;
//    protected double yFrictionalForce;

    public Projectile(int x, int y) {
        this.xOrigin = x;
        this.yOrigin = y;
    }

    public int getSpriteSize() {
        return sprite.SIZE;
    }

    public double distance() {
        double dist = 0;
        dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
        return dist;
    }

}
