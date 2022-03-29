package bots;
 
import java.awt.Graphics;
import java.awt.Image;

import arena.BattleBotArena;
import arena.BotInfo;
import arena.Bullet;
 
 
 
public class KomyBot extends Bot {
 
    BotHelper helper = new BotHelper();
 
   
    int moving = BattleBotArena.STAY;
    int hitBoxSize = 15;
 
    Image current, up, down, right, left;
 
   
 
    private boolean overheat = false;
    private int sleep = (int) (Math.random() * 5 + 1);
    private int targetNum = (int) (Math.random() * BattleBotArena.NUM_BOTS);
 
 
    @Override
    public void newRound() {
        if (botNumber >= targetNum - 3 && botNumber <= targetNum + 3)
            setOverheat(true);
    }
 
    public boolean isOverheat() {
        return overheat;
    }
 
    public void setOverheat(boolean overheat) {
        this.overheat = overheat;
    }
 
    public int getSleep() {
        return sleep;
    }
 
    public void setSleep(int sleep) {
        this.sleep = sleep;
    }
 
    @Override
    public int getMove(BotInfo me, boolean shotOK, BotInfo[] liveBots, BotInfo[] deadBots, Bullet[] bullets) {
       // Arena
       int hitboxRadius = 5;
        Bullet closestBullet = helper.findClosestBullet(me, bullets);
        BotInfo closestBot =  helper.findClosestBot(me, liveBots);
 
        // Get positions of bullets
        double bulletX = closestBullet.getX();
        double bulletY = closestBullet.getY();
       
        // get the positions of my bot
        double myX = me.getX();
        double myY = me.getY();
 
        // Get the enemy bot positions
        double BotsX = closestBot.getX();
        double BotsY = closestBot.getY();
       
        // Get the bot and bullet distances
        double bulletdistance = helper.calcDistance(myX, bulletX, myY, bulletY);
        double botDistance = helper.calcDistance(myX, myY, BotsX, BotsY);
       
        // y and x displacements
        double displacementX = Math.abs(helper.calcDisplacement(myX, bulletX));
        double displacementY = Math.abs(helper.calcDisplacement(myY, bulletY));
       
        // Get the speed/direction of bullets
        double BulletSpeedX = closestBullet.getXSpeed();
        double BulletSpeedY = closestBullet.getYSpeed();
 
        if (bulletdistance < 60){
            if ((bulletX > myX && BulletSpeedX < 0) || (bulletX < myX && BulletSpeedX > 0)){  // Scan bullets coming from the left or right
                if (bulletY > myY + hitBoxSize ) {moving = BattleBotArena.DOWN;}        
                else if (bulletY <= myY - hitBoxSize ) {moving = BattleBotArena.UP;}    
            }
 
 
            if ((bulletY > myY && BulletSpeedY < 0) || (bulletY < myY && BulletSpeedY > 0)){ // Scan any bullets coming from the left or right
                if (bulletX > myX + hitBoxSize ) {moving = BattleBotArena.LEFT;}        
                else if (bulletX <= myY -hitBoxSize) {moving = BattleBotArena.RIGHT;}
            }
        }
 
        // Return shooting
        return moving;    
    }
 
    @Override
    public void draw(Graphics g, int x, int y) {
        // TODO Auto-generated method stub
        g.drawImage(current, x, y, Bot.RADIUS * 2, Bot.RADIUS * 2, null);
    }
 
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "KomyBot";
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
 
    }
 
    @Override
    public void loadedImages(Image[] images) {
        if (images != null) {
            if (images.length > 0)
                up = images[0];
            if (images.length > 1)
                down = images[1];
            if (images.length > 2)
                right = images[2];
            if (images.length > 3)
                left = images[3];
            current = up;
        }
       
    }
   
}
       
   
   
 
 

