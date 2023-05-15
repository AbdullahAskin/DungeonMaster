
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LvlLevel {

    public static Random rand = new Random();
    public static LvlLevel map_1 = new StartLevel("/images/map1.png");
    public static LvlLevel map_2 = new NormalLevel("/images/map2.png");

    public int[] tiles;
    public int width, height;

    protected List<Projectile> projectiles = new ArrayList<Projectile>();
    protected List<Particle> particles = new ArrayList<Particle>();
    protected List<Entity> entities = new ArrayList<Entity>();
    protected List<Player> players = new ArrayList<Player>();
    protected List<Machine> machines = new ArrayList<Machine>();
    protected List<DummyZombie> zombies = new ArrayList<DummyZombie>();

    public LvlLevel(String path) {
        loadLevel(path);
    }

    public void render(int xScroll, int yScroll, Screen screen) {
//        System.out.println(" xScroll : " + xScroll + " yScroll : " + yScroll);
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                getTile(x, y).renderTile(x, y, screen);
            }
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
//            System.out.println("x : " + projectiles.get(i).x + "   y : " + projectiles.get(i).y);
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(screen);
        }

        for (int i = 0; i < machines.size(); i++) {
            machines.get(i).render(screen);
        }

        for (int i = 0; i < zombies.size(); i++) {
            zombies.get(i).render(screen);
        }
        for (int i = 0; i < players.size(); i++) {
            players.get(i).render(screen);
        }
    }

    public Tile getTile(int x, int y) {
        if (x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.water;
        }
        if (tiles[x + y * width] == 0xFF000000) {
            return Tile.wall;
        }
        if (tiles[x + y * width] == 0xFF808080) {
            return Tile.floor;
        }
        if (tiles[x + y * width] == 0xFFFFD800) {
            return Tile.barricade;
        }
        if (tiles[x + y * width] == 0xFFFF0000) {
            return Tile.fire;
        }
        return Tile.water; //Şimdilik void kum
    }

    public void update() {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).update();
        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();

        }
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }
        for (int i = 0; i < machines.size(); i++) {
            machines.get(i).update();
        }
        for (int i = 0; i < zombies.size(); i++) {
            zombies.get(i).update();
        }
        remove();
    }

    public void remove() {
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) {
                projectiles.remove(i);
            }
        }
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) {
                particles.remove(i);
            }
        }
        for (int i = 0; i < machines.size(); i++) {
            if (machines.get(i).isRemoved()) {
                machines.remove(i);
            }
        }
    }

    public void add(Entity e) {
        e.init(this);
        if (e instanceof DefaultGun) {
            projectiles.add((DefaultGun) e);
        } else if (e instanceof MachineProjectile) {
            projectiles.add((MachineProjectile) e);
        } else if (e instanceof BouncingGun) {
            projectiles.add((BouncingGun) e);
        } else if (e instanceof MagicGun) {
            projectiles.add((MagicGun) e);
        } else if (e instanceof Particle) {
            particles.add((Particle) e);
        } else if (e instanceof Player) {
            players.add((Player) e);
        } else if (e instanceof Machine) {
            machines.add((Machine) e);
        } else if (e instanceof DummyZombie) {
            zombies.add((DummyZombie) e);
        } else {
            entities.add(e);
        }
    }

    public boolean tileCollision(double x, double y, int size, int xOffset, int yOffset) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((int) x - c % 2 * size + xOffset + 10) >> 4;
            int yt = ((int) y - c / 2 * size + yOffset + 10) >> 4;

            if (getTile(xt, yt).solid()) {
//                System.out.println(" xt : " + (xt << 4) + " x : " + x);
//                System.out.println(" yt : " + (yt << 4) + " y : " + y);
                solid = true;
                return solid;
            }
        }
        return solid;
    }
////                System.out.println(" xt : " + (xt << 4) + "  x : " + x);
    //                System.out.println(" yt : " + (yt << 4) + "  y : " + y);

    public boolean tileCollisionBouncing(double x, double y, int size, int xOffset, int yOffset, BouncingGun b) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((int) x - c % 2 * size + xOffset + 10) >> 4;
            int yt = ((int) y - c / 2 * size + yOffset + 10) >> 4;
            if (getTile(xt, yt).solid()) {
                solid = true;
                if ((c == 2 || c == 0) && (xt << 4) > x + 9 && !getTile(xt - 1, yt).solid()) {
                    b.bouncingDirection(1);
                } else if ((c == 3 || c == 1) && (xt << 4) + 9 <= x && !getTile(xt + 1, yt).solid()) {
                    b.bouncingDirection(3);
                } else if ((c == 3 || c == 2) && (yt << 4) <= y - 5 && !getTile(xt, yt + 1).solid()) {
                    b.bouncingDirection(0);
                } else if ((c == 1 || c == 0) && (yt << 4) > y && !getTile(xt, yt - 1).solid()) {
                    b.bouncingDirection(2);
                }
                return solid;
            }
        }
        return solid;
    }

    public boolean machineCollision(double x, double y, int size, int xOffset, int yOffset) {
        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((int) x - c % 2 * size + xOffset - 10) / 32;
            int yt = ((int) y - c / 2 * size + yOffset + 6) / 32;
            if (getMachine(xt, yt)) {
                solid = true;
                return solid;
            }
        }
        return solid;
    }

    public List<Player> getPlayers(Entity e, int radius) {
        List<Player> result = new ArrayList<Player>();
        int ex = (int) e.getX();
        int ey = (int) e.getY();
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            int x = (int) player.getX();
            int y = (int) player.getY();
            int dx = Math.abs(x - ex);
            int dy = Math.abs(y - ey);
            double distance = Math.sqrt((dx * dx) + (dy * dy));
            if (distance <= radius) {
                result.add(player);
            }
        }
        return result;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public boolean getMachine(int x, int y) {
        boolean solid = false;

        for (int i = 0; i < machines.size(); i++) {
            int ex = (int) machines.get(i).x / 32;
            int ey = (int) machines.get(i).y / 32;

            if ((ex == x) && ey == y) {
                solid = true;
                machines.get(i).takingDamage();
                return solid;
            }
        }
        return solid;
    }

    public void loadLevel(String path) {
    }

}
