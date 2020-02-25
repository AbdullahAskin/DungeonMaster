
import java.awt.Color;
import java.awt.Graphics;

public class UIComponent {

    protected Vector2i position;
    protected Color color;

    public UIComponent(Vector2i position, Color color) {
        this.position = position;
        this.color = color;
    }

    public void update() {

    }

    public void render(Graphics g) {

    }
}
