
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UIPanel extends UIComponent {

    protected List<UIComponent> components = new ArrayList<UIComponent>();
    Vector2i size;

    public UIPanel(Vector2i position, Vector2i size, Color color) {
        super(position, color);
        this.size = size;
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x, position.y, size.x, size.y);
        for (UIComponent component : components) {
            component.render(g);
        }
    }

    public void add(UIComponent component) {
        components.add(component);
    }

}
