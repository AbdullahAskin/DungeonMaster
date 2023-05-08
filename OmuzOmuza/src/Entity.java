
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Entity {

    public double x, y;
    private boolean removed = false;
    protected LvlLevel level;
    protected Random random = new Random();
    protected Sprite sprite;

    public Entity() {
    }

    public void update() {
    }

    public void render(Screen screen) {
    }

    public void remove() {
        //remove from level
        removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void init(LvlLevel level) {
        this.level = level;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

}
