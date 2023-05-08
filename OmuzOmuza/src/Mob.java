
public class Mob extends Entity {

    protected Sprite sprite;
    protected int dir = 2;
    public boolean walking = false;

    public void move(double xa, double ya) {
        if (xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if (xa > 0) {
            dir = 3;
        }
        if (xa < 0) {
            dir = 1;
        }
        if (ya > 0) {
            dir = 0;
        }
        if (ya < 0) {
            dir = 2;
        }

        if (!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    public void shoot(int x, int y, double angle, int xa, int ya) {
        if (Keyboard.WeaponNumber == 1) {
            level.add(new DefaultGun(x - 8, y - 5, angle, xa, ya));
        } else if (Keyboard.WeaponNumber == 2) {
            level.add(new BouncingGun(x - 8, y - 5, angle));
        } else if (Keyboard.WeaponNumber == 3) {
            level.add(new MagicGun(x - 16, y - 12, angle));
        }
    }

    public boolean collision(double xa, double ya) {
        boolean solid = false;

        for (int c = 0; c < 4; c++) {
//            int xt = ((xa + x) + c / 2 * 16 - 15) / 16; // Burda yapılan çıkarma karakterimizi daha içerde göstericek ve soldan çarpışmayı daha erkene alıcak.
//            int yt = ((ya + y) + c / 2 * 15) / 16;
            double xt = ((x + xa) + c % 2 * 12 - 6) / 16; // Eğer burda çıkarma yaparsak kendimizi olduğumuz yerden daha solda gösteririz buda tuğlanın bizi durdurmasına yol açar. Basic mantık bro.
            double yt = ((y + ya) + c / 2 * 12 + 3) / 16; // c = 0,1,2,3 olabilir buda şu anlama geliyor her bir köşe için 1 kontrol sağlanıyor yani c=0 üstten gelebilicegği kontrol ederken c=1  soldan gelmesini kontrol ediyor.
//            System.out.println(" xt : " + xt + " c : " + c);
//            System.out.println(" yt : " + yt + " c : " + c);
            if (LvlLevel.map_1.getTile((int) xt, (int) yt).solid()) {
//            System.out.println(" solid : " + LvlLevel.spawn_level.getTile(x + xa, y + ya).solid() + " nesne :" + LvlLevel.spawn_level.getTile(x + xa, y + ya));
                solid = true;
            }
        }
        return solid;
    }

    @Override
    public void render(Screen screen) {
    }

}
