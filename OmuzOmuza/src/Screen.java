
import java.util.Random;

public class Screen {

    public int height, width;
    public int[] pixels;
    public static int xOffset, yOffset;
    public static int Alpha_col = 0xFFFF00FF;

    public Screen(int width, int height) {
        this.height = height;
        this.width = width;
        pixels = new int[width * height];

    }

    public void render(int xScroll, int yScroll) {
        for (int y = 0; y < height; y++) {
            int ya = y + yScroll;
            for (int x = 0; x < width; x++) {
                int xa = x + xScroll;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) {
                    continue;
                }
                pixels[xa + ya * width] = Sprite.water.pixels[(x & 15) + (y & 15) * 16];
            }
        }

    }
//        System.out.println("x position : " + xp + " y position : " + yp + "  xoffset : " + xOffset + " yoffset : " + yOffset);

    public void renderTile(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp; // ya = absolute y cordinate
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp; // xa = absolute x cordinate
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) {
                    break;
                }
                if (xa < 0) {
                    xa = 0;
                }
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
            }
        }
    }

    public void renderTile(int xp, int yp, Sprite sprite, double angle) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp; // ya = absolute y cordinate
            for (int x = 0; x < sprite.SIZE; x++) {
                int xa = x + xp; // xa = absolute x cordinate
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) {
                    break;
                }
                if (xa < 0) {
                    xa = 0;
                }
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.SIZE];
            }
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite) {
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < sprite.height; y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.width; x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) {
                    continue;
                }
                pixels[xa + ya * width] = sprite.pixels[x + y * sprite.width];
            }
        }
    }

    public void renderHpSprite(int xp, int yp, Sprite sprite, Entity e) {
        int remainingHp = 0;
        if (e instanceof Machine) {
            remainingHp = ((Machine) e).remainingHp;
            yp -= 6;            //Canın doğru konumda olması için yapılan ayarlama
        }
        xp -= xOffset;
        yp -= yOffset;

        for (int y = 0; y < sprite.height; y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.width; x++) {
                int xa = x + xp;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height) {
                    continue;
                }
                if ((x * 3) <= remainingHp) {
                    pixels[xa + ya * width] = sprite.pixels[x + y * sprite.width];
                } else {
                    pixels[xa + ya * width] = 0xff8a0303;
                }
            }
        }
    }

    public void renderProjectile(int xp, int yp, Projectile p) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < p.getSpriteSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < p.getSpriteSize(); x++) {
                int xa = x + xp;
                if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) {
                    break;
                }
                if (xa < 0) { // Burasının tam olarak ne yaptıgını bul
                    xa = 0;
                }

                int col = p.sprite.pixels[x + y * p.getSpriteSize()];
                if (col != Alpha_col) {
                    pixels[xa + ya * width] = col;
                }
            }
        }
    }

    public void renderDisappearProjectile(int xp, int yp, int disappearRate, Projectile p) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < p.getSpriteSize(); y++) {
            int ya = y + yp;
            for (int x = 0; x < p.getSpriteSize(); x++) {
                int xa = x + xp;
                if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) {
                    break;
                }
                if (xa < 0) { // Burasının tam olarak ne yaptıgını bul
                    xa = 0;
                }
                int disappear = LvlLevel.rand.nextInt(disappearRate);
                int col = p.sprite.pixels[x + y * p.getSpriteSize()];
                if (col != Alpha_col && disappear != 0) {
                    pixels[xa + ya * width] = col;
                }
            }
        }
    }

    public void renderPlayer(int xp, int yp, Sprite sprite, int dir) {
        xp -= xOffset;
        yp -= yOffset;
//        System.out.println(" xp : " + xp + " yp :" + yp);
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            int ys = y;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xs = x;
                int xa = x + xp;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) {
                    continue;
                }
                if (dir == 3) {
                    xs = sprite.SIZE - 1 - x;
                }
                if (xa < 0) {
                    xa = 0;
                }

                int col = sprite.pixels[xs + ys * sprite.SIZE];
                if (col != Alpha_col) {
                    pixels[xa + ya * width] = col;
                }
            }
        }
    }

    public void renderMachine(int xp, int yp, Sprite sprite, int dir) {
        xp -= xOffset;
        yp -= yOffset;
//        System.out.println(" xp : " + xp + " yp :" + yp);
        for (int y = 0; y < sprite.SIZE; y++) {
            int ya = y + yp;
            int ys = y;
            for (int x = 0; x < sprite.SIZE; x++) {
                int xs = x;
                int xa = x + xp;
                if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) {
                    continue;
                }
                if (dir == 3) {
                    xs = sprite.SIZE - 1 - x;
                }
                if (dir == 0) {
                    ys = sprite.SIZE - 1 - y;
                }
                if (xa < 0) {
                    xa = 0;
                }

                int col = sprite.pixels[xs + ys * sprite.SIZE];
                if (col != Alpha_col) {
                    pixels[xa + ya * width] = col;
                }
            }
        }
    }

    public void renderHealthBar(int xp, int yp, int height, int width) {
        xp -= xOffset;
        yp -= yOffset;
        for (int y = 0; y < height; y++) {
            int ya = y + yp;
            for (int x = 0; x < width; x++) {
                int xa = x + xp;
                if (xa < -width || xa >= this.width || ya < 0 || ya >= this.height) {
                    continue;
                }
                pixels[xa + ya * width] = 0xffffff;  // Canın kırmızı ve yeşil kısmını oluştur.

            }
        }
    }

    public void drawRect(int xp, int yp, int w, int h, int col, boolean fixed) {
        if (fixed) {
            xp -= xOffset;
            yp -= yOffset;
        }

        for (int x = 0; x < w; x++) {
            int xa = xp + x;
            if (yp >= height || yp + h < 0 || xa <= 0 || xa >= width) {
                continue;
            }
            if (yp > 0) {
                pixels[xa + yp * width] = col;
            }
            if (yp + h < height) {
                pixels[xa + (yp + h) * width] = col;
            }
        }

        for (int y = 0; y < h; y++) {
            int ya = yp + y;
            if (ya >= height || ya < 0 || xp - w <= 0 || xp >= width) {
                continue;
            }
            if (xp > 0) {
                pixels[xp + ya * width] = col;
            }
            if (xp + w < width) {
                pixels[(xp + w) + ya * width] = col;
            }
        }

    }

    public void render(Vector2i player, Vector2i position, int[] tiles, int size) {
        int xp = position.x;
        int yp = position.y;
        int xm = (player.x >> 4) - size / 8;
        int ym = (player.y >> 4) - size / 8;

        for (int y = 0; y < size / 4; y++) {
            int ya = yp + y;
            int yt = ym + y;
            for (int x = 0; x < size / 4; x++) {
                int xa = xp + x;
                int xt = xm + x;
                int col = 0;
                if (xm < 0 || ym < 0 || xm > size || ym > size) {
                    col = 0xffffff;
                } else {
                    col = tiles[xt + yt * size / 4];
                }

                pixels[xa + ya * width] = col;
            }
        }
        xp += player.x >> 4;
        yp += player.y >> 4;
        pixels[xp + yp * width] = 0xffffff;
    }

    public void renderMiniMap(Vector2i player, Vector2i position, int[] tiles, int size) {
        int xp = position.x;
        int yp = position.y;
        for (int y = 0; y < size; y++) {
            int ya = yp + y;
            for (int x = 0; x < size; x++) {
                int xa = xp + x;
                pixels[xa + ya * width] = tiles[x + y * size];
            }
        }
        xp += player.x >> 4;
        yp += player.y >> 4;
        pixels[xp + yp * width] = 0xffffff;
    }

    public void clear() {
        for (int b = 0; b < width * height; b++) {
            pixels[b] = 0;
        }
    }

    public void setOffset(int xScroll, int yScroll) {
        this.xOffset = xScroll;
        this.yOffset = yScroll;
    }

}
