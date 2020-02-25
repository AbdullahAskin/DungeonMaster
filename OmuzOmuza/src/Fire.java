
public class Fire extends Tile {

    public Fire(Sprite sprite) {
        super(sprite);
    }

    public void renderTile(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this.sprite);
    }

}
