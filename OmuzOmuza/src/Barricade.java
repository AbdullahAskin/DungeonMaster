
public class Barricade extends Tile {

    public Barricade(Sprite sprite) {
        super(sprite);
    }

    public void renderTile(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this.sprite);
    }

    public boolean solid() {
        return true;
    }
}
