package bots;

import java.awt.Graphics;
import java.awt.Image;

import arena.BattleBotArena;
import arena.BotInfo;
import arena.Bullet;

public class FahadBot extends Bot {

    BotHelper helper = new BotHelper();
    // setting variables for my x location
    private double myX = 0;
    private double myY = 0;

    // bullet locations
    private double bulletX = 0;
    private double bulletY = 0;

    // variable sfor deadbts location
    private double closestDeadBotX = 0;
    private double closestDeadBotY = 0;

    // setting images
    Image current;

    // distance variables
    public int BulletDistance = 0;

    // movement var
    private int move = BattleBotArena.DOWN;
    private int shoot = BattleBotArena.FIRELEFT;

    @Override
    public void newRound() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getMove(BotInfo me, boolean shotOK, BotInfo[] liveBots, BotInfo[] deadBots, Bullet[] bullets) {
        // TODO Auto-generated method stub
        // adding a comment
        // BotInfo liveBots = helper.findClosestBot(_me, _bots)
        double myX = me.getX();
        double myY = me.getY();

        // finding bots and their locations near me
        BotInfo closestBot = helper.findClosestBot(me, liveBots);
        double closestBotX = closestBot.getX();
        double closestBotY = closestBot.getY();

        // finding deadbots near me
        BotInfo closestDeadBot = helper.findClosestBot(me, deadBots);
        double closestDeadBotX = closestDeadBot.getX();
        double closestDeadBotY = closestDeadBot.getY();

        // finding closest bullets and their locations
        Bullet closeBullet = helper.findClosestBullet(me, bullets);
        bulletX = closeBullet.getX();
        bulletY = closeBullet.getY();

        // calculating distance between me and a bullet
        // BulletDistance = calcDistance(myX, myY, bulletX, bulletY);

        // firing at bots close to me
        if (closestBotX == (myX - 5)) {
            move = BattleBotArena.STAY;
            while (move == BattleBotArena.STAY)
                shoot = BattleBotArena.FIRELEFT;
        } else if (closestBotX == (myX + 5)) {
            move = BattleBotArena.STAY;
            shoot = BattleBotArena.FIRERIGHT;
        } else if (closestBotY == (myY - 5)) {
            move = BattleBotArena.STAY;
            shoot = BattleBotArena.FIREDOWN;
        } else if (closestBotY == (myY + 5)) {
            move = BattleBotArena.STAY;
            shoot = BattleBotArena.FIREUP;
        } else
            move = BattleBotArena.LEFT;

        // moving if bullets attack me from up down left right
        if (bulletX == (myX - 5)) {
            move = BattleBotArena.UP;
        } else if (bulletX == (myX + 5)) {
            move = BattleBotArena.UP;
        } else if (bulletY == (myY - 5)) {
            move = BattleBotArena.LEFT;
        } else if (bulletY == (myY + 5)) {
            move = BattleBotArena.RIGHT;
        } else
            move = BattleBotArena.STAY;

        // situation where there are less bots in arena (radius increases to shoot at
        // further bots)
        if (liveBots.length < 10) {
            // firing at bots close to me
            if (closestBotX == (myX - 20)) {
                move = BattleBotArena.STAY;
                while (move == BattleBotArena.STAY)
                    shoot = BattleBotArena.FIRELEFT;
            } else if (closestBotX == (myX + 20)) {
                move = BattleBotArena.STAY;
                shoot = BattleBotArena.FIRERIGHT;
            } else if (closestBotY == (myY - 20)) {
                move = BattleBotArena.STAY;
                shoot = BattleBotArena.FIREDOWN;
            } else if (closestBotY == (myY + 20)) {
                move = BattleBotArena.STAY;
                shoot = BattleBotArena.FIREUP;
            } else
                move = BattleBotArena.LEFT;

        }

        // navigating dead bots

        return BattleBotArena.UP;
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "Nuha";
    }

    @Override
    public String getTeamName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String outgoingMessage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void incomingMessage(int botNum, String msg) {
        // TODO Auto-generated method stub

    }

    @Override
    public String[] imageNames() {
        // TODO Auto-generated method stub
        String[] images = { "UFO.png" };
        return images;
    }

    @Override
    public void loadedImages(Image[] images) {
        if (images != null) {
            current = images[0];
        }
    }

}
