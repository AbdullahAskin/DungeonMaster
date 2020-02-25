
public class Sprite {

    public final int SIZE;
    public int height, width;
    private int x, y;
    public int[] pixels;
    private SpriteSheet sheet;

    public static Sprite water = new Sprite(5, 2, 16, SpriteSheet.tiles);
    public static Sprite wall = new Sprite(2, 3, 16, SpriteSheet.tiles);
    public static Sprite grass = new Sprite(0, 4, 16, SpriteSheet.tiles);
    public static Sprite barricade = new Sprite(2, 6, 16, SpriteSheet.tiles);
    public static Sprite fire1 = new Sprite(4, 2, 16, SpriteSheet.tiles);

    public static Sprite player_forward = new Sprite(3, 5, 32, SpriteSheet.tiles);
    public static Sprite player_forward_1 = new Sprite(3, 6, 32, SpriteSheet.tiles);
    public static Sprite player_forward_2 = new Sprite(3, 7, 32, SpriteSheet.tiles);

    public static Sprite player_back = new Sprite(4, 5, 32, SpriteSheet.tiles);
    public static Sprite player_back_1 = new Sprite(4, 6, 32, SpriteSheet.tiles);
    public static Sprite player_back_2 = new Sprite(4, 7, 32, SpriteSheet.tiles);

    public static Sprite player_side = new Sprite(5, 5, 32, SpriteSheet.tiles);
    public static Sprite player_side_1 = new Sprite(5, 6, 32, SpriteSheet.tiles);
    public static Sprite player_side_2 = new Sprite(5, 7, 32, SpriteSheet.tiles);

    public static Sprite zombie_forward = new Sprite(3, 2, 32, SpriteSheet.tiles);
    public static Sprite zombie_forward_1 = new Sprite(3, 3, 32, SpriteSheet.tiles);
    public static Sprite zombie_forward_2 = new Sprite(3, 4, 32, SpriteSheet.tiles);

    public static Sprite zombie_back = new Sprite(4, 2, 32, SpriteSheet.tiles);
    public static Sprite zombie_back_1 = new Sprite(4, 3, 32, SpriteSheet.tiles);
    public static Sprite zombie_back_2 = new Sprite(4, 4, 32, SpriteSheet.tiles);

    public static Sprite zombie_side = new Sprite(5, 2, 32, SpriteSheet.tiles);
    public static Sprite zombie_side_1 = new Sprite(5, 3, 32, SpriteSheet.tiles);
    public static Sprite zombie_side_2 = new Sprite(5, 4, 32, SpriteSheet.tiles);

    public static Sprite machine_left = new Sprite(7, 5, 32, SpriteSheet.tiles);
    public static Sprite machine_up = new Sprite(7, 6, 32, SpriteSheet.tiles);

    //Mermi
    public static Sprite projectile_defaultGun = new Sprite(0, 2, 16, SpriteSheet.wizard);
    public static Sprite projectile_bouncingGun = new Sprite(1, 2, 16, SpriteSheet.wizard);
    public static Sprite projectile_machine = new Sprite(2, 0, 16, SpriteSheet.wizard);
    public static Sprite projectile_magicGun = new Sprite(0, 0, 32, SpriteSheet.wizard);

    //Can barları
    public static Sprite machine_healthbar = new Sprite(32, 3, 0xFF76EE00);

    // Partikül
    public static Sprite wizardParticle = new Sprite(2, 0XFFFFFF);
    public static Sprite wizardParticle2 = new Sprite(2, 0xff0b1457);
    public static Sprite machineParticle = new Sprite(3, 0XFF58024b);
    public static Sprite magicParticle = new Sprite(2, 0xffff4466);
    public static Sprite deathParticle = new Sprite(3, 0xff00fcff);
    public static Sprite disappearParticle = new Sprite(1, 0xff2BFFA3);
    public static Sprite gunThreeParticleBlue = new Sprite(2, 0x213BFF);
    public static Sprite gunThreeParticleOrange = new Sprite(2,0xA416F7);

    public Sprite(int x, int y, int SIZE, SpriteSheet sheet) {
        this.SIZE = SIZE;
        pixels = new int[SIZE * SIZE];
        this.width = SIZE;
        this.height = SIZE;
        this.x = x * SIZE;
        this.y = y * SIZE;
        this.sheet = sheet;
        load();
    }

    public Sprite(int SIZE, int color) {
        this.SIZE = SIZE;
        this.width = SIZE;
        this.height = SIZE;
        pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    public Sprite(int width, int height, int color) {
        this.SIZE = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        setColor(color);
    }

    public Sprite(int[] pixels, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];

        for (int i = 0; i < pixels.length; i++) {
            this.pixels[i] = pixels[i];
        }
    }

    private void load() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
            }
        }
    }

    private void setColor(int color) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[x + y * width] = color;
            }
        }
    }

    public static Sprite rotate(Sprite sprite, double angle) {
        return new Sprite(toRotate(angle, sprite.pixels, sprite.width, sprite.height), sprite.width, sprite.height);
    }

    protected static int[] toRotate(double angle, int[] pixel, int width, int height) {
        int[] result = new int[width * height];

        double nx_x = rot_x(-angle, 1.0, 0);
        double nx_y = rot_y(-angle, 1.0, 0);
        double ny_x = rot_x(-angle, 0, 1.0);
        double ny_y = rot_y(-angle, 0, 1.0);

        double x0 = rot_x(-angle, -width / 2, -height / 2) + width / 2;
        double y0 = rot_y(-angle, -width / 2, -height / 2) + height / 2;

        for (int y = 0; y < height; y++) {
            double x1 = x0;
            double y1 = y0;
            for (int x = 0; x < width; x++) {
                int xx = (int) x1;
                int yy = (int) y1;
                int col = 0;
                if (xx < 0 || xx >= width || yy < 0 || yy >= height) {
                    col = Screen.Alpha_col;
                } else {
                    col = pixel[xx + yy * width];
                }
                result[x + y * width] = col;
                x1 += nx_x;
                y1 += nx_y;
            }
            x0 += ny_x;
            y0 += ny_y;
        }
        return result;
    }

    private static double rot_x(double angle, double x, double y) {
        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);
        return x * cos + y * -sin;
    }

    private static double rot_y(double angle, double x, double y) {
        double cos = Math.cos(angle - Math.PI / 2);
        double sin = Math.sin(angle - Math.PI / 2);
        return x * sin + y * cos;
    }
}
