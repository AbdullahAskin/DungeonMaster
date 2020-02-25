
public class Tile {

    public static Tile water = new Water(Sprite.water);
    public static Tile wall = new Wall(Sprite.wall);
    public static Tile barricade = new Barricade(Sprite.barricade);
    public static Tile floor = new Grass(Sprite.grass);
    public static Tile fire = new Fire(Sprite.fire1);

    public int x, y;
    public Sprite sprite;

    public Tile(Sprite sprite) {
        this.sprite = sprite;
    }

    public void renderTile(int x, int y, Screen screen) {

    }

    public void update(double angle) {

    }

    public boolean solid() {
        return false;
    }
}
