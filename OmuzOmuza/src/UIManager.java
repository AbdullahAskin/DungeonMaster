
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

    List<UIPanel> panels = new ArrayList<UIPanel>();

    public void update() {

    }

    public void render(Graphics g) {
        for (UIPanel panel : panels) {
            panel.render(g);
        }
    }

    public void add(UIPanel panel) {
        panels.add(panel);
    }
}
