// this bot is mainly to provide support
package bots;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;

import arena.BattleBotArena;
import arena.BotInfo;
import arena.Bullet;

public class MofeBot extends Bot {
    BotHelper helper = new BotHelper();
    private double nearest_botY = 0;
    private double nearest_botX = 0;
     private double nearest_bulletY;
    private double nearest_bulletX;
    double min_dist = 100;
   // Bullet[] bull = new Bullet[4];
    @Override
    public void newRound() {
        
        
    }

    @Override
    public int getMove(BotInfo me, boolean shotOK, BotInfo[] liveBots, BotInfo[] deadBots, Bullet[] bullets) {
        
        //adding a comment 
        //find ClosestBot to me
        nearest_botY = (helper.findClosestBot(me, liveBots).getY());
        nearest_botX = (helper.findClosestBot(me, liveBots).getX());
        double X = me.getX(); // bot's x coordinate
        double Y = me.getY(); // bot's y - coordinate
        double DiffX = Math.abs(X - nearest_botX);
        double DiffY = Math.abs(Y - nearest_botX);
        if(X<nearest_botX && DiffX<min_dist)
        {
          X-=1;
        }
        else if (X>nearest_botX && DiffX>min_dist)
        {
          X+=1;
        }
        else if (Y>nearest_botY && DiffY<min_dist)
        {
          Y-=1;
        }
        else if (Y>nearest_botY && DiffY>min_dist)
        {
          Y+=1;
        }
        // move away from bullets
        nearest_bulletY = (helper.findClosestBullet(me, liveBots).getY());
        nearest_bulletX = (helper.findClosestBullet(me, liveBots).getX());
        double bullet_diffX = Math.abs(X-nearest_bulletX);
        double bullet_diffY = Math.abs(Y-nearest_bulletY);
        if(X < nearest_bulletX && bullet_diffX<10)
        {

         X-=1;
        }
        else if (X>nearest_bulletX && bullet_diffX>10)
        {
          X+=1;
        }
        else if (Y>nearest_bulletY && bullet_diffY>10)
        {
          Y+=1;
        }
        if(Y < nearest_bulletX && bullet_diffY<10)
        {

         Y-=1;
        }
        
        
       
        
        return BattleBotArena.UP;
        
    }

    @Override
    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.pink);
		g.fillRect(x, y, RADIUS*2, RADIUS*2);  

        
    }

    @Override
    public String getName() {
        
        return "robot";
    }

    @Override
    public String getTeamName() {
        // 
        return null;
    }

    @Override
    public String outgoingMessage() {
       
        return null;
    }

    @Override
    public void incomingMessage(int botNum, String msg) {
        
        
    }

    @Override
    public String[] imageNames() {
       
        return null;
    }

    @Override
    public void loadedImages(Image[] images) {
        
        
    }
    
}

    

