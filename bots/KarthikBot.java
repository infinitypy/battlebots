//Things that went well
//
// Things that don't go as planned

//TODO: 
package bots;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;


import arena.BattleBotArena;
import java.awt.event.KeyEvent;

import arena.BotInfo;
import arena.Bullet;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;



public class KarthikBot extends Bot {
    private Image image = null;
	private int counter=0;
    double bulletX;
    double bulletY;
    double myX;
    double myY;
    double peerBotX;
    double peerBotY;
    double bulletDistanceX;
    double bulletDistanceY;
    double peerBotDistanceX;
    double peerBotDistanceY;
    int num;
    int moveIndicator = 9; //default dont do any actions just stay

    BotHelper helper = new BotHelper();

    @Override
    public void newRound() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getMove(BotInfo me, boolean shotOK, BotInfo[] liveBots, BotInfo[] deadBots, Bullet[] bullets) {
        // TODO Auto-generated method stub
        //helper.findClosestBot(_me, _bots)
		moveIndicator = 9;
		//Logic for bullet detection and moving away

        ///////////////////////////////////////////////////////////////////////
        //Gather information on bot, bullet and enemy
        //Finding location of the our bot
        myX = me.getX();
		myY = me.getY();
        //finding the nearest bullet
        bulletX = helper.findClosestBullet(me, bullets).getX();
		bulletY = helper.findClosestBullet(me, bullets).getY();
        //finding the nearest bot
        peerBotX =	helper.findClosestBot(me, liveBots).getX();
		peerBotY =	helper.findClosestBot(me, liveBots).getY();
        //////////////////////////////////////////////////////////////////////
        // calculate the distances

        //finding the distance between the bullet and bot
        bulletDistanceX = myX - bulletX; //postive means bullet is on left of bot and vice versa
        bulletDistanceY = myY - bulletY; // postive means bullet is down of bot and vice versa

        //find the distance between the bot and enemy
        peerBotDistanceX = myX-peerBotX; // positive means peer is on left of bot and vice versa
        peerBotDistanceY = myY-peerBotY; // positive means peer is on right of bot and vice versa

        
        //Condt 1 - check if the bullet is in the same direction of BOT, defaults to right or up
        if((bulletDistanceX == 0) && (Math.abs(bulletDistanceY) <= 50)) {
            moveIndicator = BattleBotArena.UP;
        }

        if ((bulletDistanceY == 0) && (Math.abs(bulletDistanceX) <=50)) {
            moveIndicator = BattleBotArena.RIGHT;
        }
        
        //Condt 2 - checking the peerBOT decision
        if (moveIndicator == 9){
            if((peerBotDistanceX == 0) && (peerBotDistanceY < 0)){
                moveIndicator = BattleBotArena.FIRERIGHT;
            } else if ((peerBotDistanceX == 0) && (peerBotDistanceY > 0)){
                moveIndicator = BattleBotArena.FIRELEFT;
            } else if ((peerBotDistanceY == 0) && (peerBotDistanceX < 0)){
                moveIndicator = BattleBotArena.FIREUP;
            } else if ((peerBotDistanceY == 0) && (peerBotDistanceX > 0)){
                moveIndicator = BattleBotArena.FIREDOWN;
            }else {
                moveIndicator = (int)(Math.random()*8);
            }

        }


        

        return moveIndicator;
      /*
        //Loop condt 2
        /// checking if its closer to the threshold of 50
        
        if (Math.abs(bulletDistanceY)<= Math.abs(bulletDistanceX)){
            if (bulletDistanceY <= 50){
                num = 3;
                if (bulletDistanceX <0){
                    return BattleBotArena.RIGHT;
                }
                else{
                    return BattleBotArena.LEFT;
                }
                
            }
            else if (bulletDistanceY >= -50){
            //else{
                num = 2;
                if (bulletDistanceX <0){
                    return BattleBotArena.RIGHT;
                }
                else{
                    return BattleBotArena.LEFT;
                }
                
            }

        }
        else if (Math.abs(bulletDistanceX)<= Math.abs(bulletDistanceY)){
            if (bulletDistanceX <= 50){
                num = 0;
                if (bulletDistanceY <0){
                    return BattleBotArena.UP;
                }
                else{
                    return BattleBotArena.DOWN;
                }
                
            }
            else if (bulletDistanceX >= -50){
                num = 1;
                if (bulletDistanceX <0){
                    return BattleBotArena.UP;
                }
                else{
                    return BattleBotArena.DOWN;
                }
                
            }

        }

    

        

		if (--counter <= 0 && shotOK)
		{
			
			counter = 15;
			//int num = (int)(Math.random()*4);
			if (num == 0)
				return BattleBotArena.FIRERIGHT;
			else if (num == 1)
				return BattleBotArena.FIRELEFT;
			else if (num == 2)
				return BattleBotArena.FIREDOWN;
			else if (num == 3)
				return BattleBotArena.FIREUP;
		}
		else
			return BattleBotArena.UP;
            */
  
    }
    

/* for (int i=0; i<liveBots.length; i++)
		{
			if (!alert[liveBots[i].getBotNumber()]) 
			{
				//Use Manhattan Distance to recognize other live bots
				double d = Math.abs(me.getX()-liveBots[i].getX())+Math.abs(me.getY()-liveBots[i].getY());
				if (d < 20) 
				{
					return BattleBotArena.LEFT;
				}
			}
		}*/

      
    

    @Override
    public void draw(Graphics g, int x, int y) {
        // TODO Auto-generated method stub
		if (image != null)
			g.drawImage(image, x,y,Bot.RADIUS*1, Bot.RADIUS*1, null);
		else
		{
			g.setColor(Color.pink);
			g.fillOval(x, y, Bot.RADIUS*1, Bot.RADIUS*1);
		}
		
        
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return "KarthikBot";
    }

    @Override
    public String getTeamName() {
        // TODO Auto-generated method stub
        return "TeamSapper";
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
        return null;
    }

    @Override
    public void loadedImages(Image[] images) {
        // TODO Auto-generated method stub
        
    }


	

    
    
}
