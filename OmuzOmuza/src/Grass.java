
public class Grass extends Tile {

    public Grass(Sprite sprite) {
        super(sprite);
    }

    public void renderTile(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this.sprite);
    }

}
