
public class Water extends Tile {

    public Water(Sprite sprite) {
        super(sprite);
    }

    @Override
    public void renderTile(int x, int y, Screen screen) {
        screen.renderTile(x << 4, y << 4, this.sprite);
    }

    @Override
    public void update(double angle) {
    }

    public boolean solid() {
        return true;
    }

}
