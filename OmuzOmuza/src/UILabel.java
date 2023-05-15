
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class UILabel extends UIComponent {

    private Vector2i offset;
    private Font font;
    private String text;

    public UILabel(Vector2i position, Vector2i offset, Font font, Color color, String text) {
        super(position, color);
        this.offset = offset;
        this.font = font;
        this.text = text;
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(text, position.x + offset.x, position.y + offset.y);
    }
}
