//What went well? It functioned & did something
// What didn't? It functioned poorly

package bots;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import arena.BattleBotArena;
import arena.BotInfo;
import arena.Bullet;
import bots.BotHelper;

public class ShaunBot extends Bot {

    int counter;
    int action;
    int phaseCount;
    int fireCount;

    int bulletDodge;

    double top;
    double bottom;
    double left;
    double right;

    double closestSide;

    BotHelper helper = new BotHelper();

    @Override
    public void newRound() {
        // TODO Auto-generated method stub

        counter = 0;
        action = 0;
        phaseCount = 0;
        fireCount = 0;

        top = BattleBotArena.TOP_EDGE;
        bottom = BattleBotArena.BOTTOM_EDGE;
        left = BattleBotArena.LEFT_EDGE;
        right = BattleBotArena.RIGHT_EDGE;

        closestSide = 0;

    }

    @Override
    public int getMove(BotInfo me, boolean shotOK, BotInfo[] liveBots, BotInfo[] deadBots, Bullet[] bullets) {
        // TODO Auto-generated method stub

        Bullet threat = helper.findClosestBullet(me, bullets);
        // BotInfo botThreat = helper.findClosestBot(me, liveBots);
        // BotInfo grave = helper.findClosestBot(me, deadBots);

        int moveChoice = (int) (Math.random() * 4) + 1;

        int backUpMoveChoice = (int) (Math.random() * 3) + 1;

        if (phaseCount < 1) {
            if (counter < 250) {
                counter++;
            } else {
                phaseCount = 1;
            }
        }

        // System.out.println(phaseCount);

        if ((helper.calcDistance(me.getX(), me.getY(), threat.getX(), threat.getY())) < 40) {
            if (me.getX() < threat.getX()) {
                if (threat.getXSpeed() < 0) {
                    if ((helper.calcDisplacement(me.getY(), threat.getY())) >= 0
                            && (helper.calcDisplacement(me.getY(), threat.getY())) <= 10) {
                        if ((helper.calcDistance(me.getX(), me.getY(), me.getX(), bottom)) <= 10) {
                            action = BattleBotArena.UP;
                        } else {
                            action = BattleBotArena.DOWN;
                        }
                    } else if (helper.calcDisplacement(me.getY(), threat.getY()) > 10
                            && (helper.calcDisplacement(me.getY(), threat.getY())) <= 20) {
                        if ((helper.calcDistance(me.getX(), me.getY(), me.getX(), top)) <= 10) {
                            action = BattleBotArena.DOWN;
                        } else {
                            action = BattleBotArena.UP;
                        }
                    }
                }
            }

            else if (me.getX() > threat.getX()) {
                if (threat.getXSpeed() > 0) {
                    if ((helper.calcDisplacement(me.getY(), threat.getY())) >= 0
                            && (helper.calcDisplacement(me.getY(), threat.getY())) <= 10) {
                        if ((helper.calcDistance(me.getX(), me.getY(), me.getX(), bottom)) <= 10) {
                            action = BattleBotArena.UP;
                        } else {
                            action = BattleBotArena.DOWN;
                        }
                    } else if (helper.calcDisplacement(me.getY(), threat.getY()) > 10
                            && (helper.calcDisplacement(me.getY(), threat.getY())) <= 20) {
                        if ((helper.calcDistance(me.getX(), me.getY(), me.getX(), top)) <= 10) {
                            action = BattleBotArena.DOWN;
                        } else {
                            action = BattleBotArena.UP;
                        }
                    }
                }
            }

            if (me.getY() < threat.getY()) {
                if (threat.getYSpeed() < 0) {
                    if ((helper.calcDisplacement(me.getX(), threat.getX())) >= 0
                            && (helper.calcDisplacement(me.getX(), threat.getX())) <= 10) {
                        if ((helper.calcDistance(me.getX(), me.getY(), left, me.getY())) <= 10) {
                            action = BattleBotArena.RIGHT;
                        } else {
                            action = BattleBotArena.LEFT;
                        }
                    } else if ((helper.calcDisplacement(me.getX(), threat.getX())) > 10
                            && (helper.calcDisplacement(me.getX(), threat.getX())) <= 20) {
                        if ((helper.calcDistance(me.getX(), me.getY(), right, me.getY())) <= 10) {
                            action = BattleBotArena.LEFT;
                        } else {
                            action = BattleBotArena.RIGHT;
                        }
                    }
                }

            }

            else if (me.getY() > threat.getY()) {
                if (threat.getYSpeed() > 0) {
                    if ((helper.calcDisplacement(me.getX(), threat.getX())) >= 0
                            && (helper.calcDisplacement(me.getX(), threat.getX())) <= 10) {
                        if ((helper.calcDistance(me.getX(), me.getY(), left, me.getY())) <= 10) {
                            action = BattleBotArena.RIGHT;
                        } else {
                            action = BattleBotArena.LEFT;
                        }
                    } else if ((helper.calcDisplacement(me.getX(), threat.getX())) > 10
                            && (helper.calcDisplacement(me.getX(), threat.getX())) <= 20) {
                        if ((helper.calcDistance(me.getX(), me.getY(), right, me.getY())) <= 10) {
                            action = BattleBotArena.LEFT;
                        } else {
                            action = BattleBotArena.RIGHT;
                        }
                    }
                }

            }

        }

        else {
            if (phaseCount == 0) {
                action = BattleBotArena.STAY;
            } else {
                if ((helper.calcDistance(me.getX(), me.getY(), right, me.getY())) > 31) {
                    action = BattleBotArena.RIGHT;
                } else {
                    if (fireCount == 0) {
                        action = BattleBotArena.FIREDOWN;
                        fireCount = 1;
                    } else if (fireCount == 1) {
                        action = BattleBotArena.FIREUP;
                        fireCount = 0;
                    }
                }
            }
        }

        // if (phaseCount <= 2) {

        // counter++;

        // if (counter >= 30 + (int) Math.random() * 60) {
        // counter = 0;

        // if (moveChoice == 1) {
        // if ((helper.calcDistance(me.getX(), me.getY(), me.getX(), top)) < 50) {
        // if (backUpMoveChoice == 1) {
        // action = BattleBotArena.DOWN;
        // } else if (backUpMoveChoice == 2) {
        // action = BattleBotArena.RIGHT;
        // } else {
        // action = BattleBotArena.LEFT;
        // }
        // } else {
        // action = BattleBotArena.UP;
        // }
        // } else if (moveChoice == 2) {
        // if ((helper.calcDistance(me.getX(), me.getY(), me.getX(), bottom)) < 50) {
        // if (backUpMoveChoice == 1) {
        // action = BattleBotArena.UP;
        // } else if (backUpMoveChoice == 2) {
        // action = BattleBotArena.RIGHT;
        // } else {
        // action = BattleBotArena.LEFT;
        // }
        // } else {
        // action = BattleBotArena.DOWN;
        // }
        // } else if (moveChoice == 3) {
        // if ((helper.calcDistance(me.getX(), me.getY(), left, me.getY())) < 50) {
        // if (backUpMoveChoice == 1) {
        // action = BattleBotArena.DOWN;
        // } else if (backUpMoveChoice == 2) {
        // action = BattleBotArena.RIGHT;
        // } else {
        // action = BattleBotArena.UP;
        // }
        // } else {
        // action = BattleBotArena.LEFT;
        // }
        // } else if (moveChoice == 4) {
        // if ((helper.calcDistance(me.getX(), me.getY(), right, me.getY())) < 50) {
        // if (backUpMoveChoice == 1) {
        // action = BattleBotArena.DOWN;
        // } else if (backUpMoveChoice == 2) {
        // action = BattleBotArena.UP;
        // } else {
        // action = BattleBotArena.LEFT;
        // }
        // } else {
        // action = BattleBotArena.RIGHT;
        // }
        // }
        // phaseCount++;
        // counter = 0;

        // }

        // }

        // else if (phaseCount == 3) {
        // action = BattleBotArena.STAY;
        // phaseCount++;

        // }

        // else if (phaseCount > 3 && phaseCount <= 7) {
        // if (moveChoice == 1) {
        // action = BattleBotArena.FIREUP;
        // } else if (moveChoice == 2) {
        // action = BattleBotArena.FIREDOWN;
        // } else if (moveChoice == 3) {
        // action = BattleBotArena.FIRELEFT;
        // } else if (moveChoice == 4) {
        // action = BattleBotArena.FIRERIGHT;
        // }
        // phaseCount++;
        // }

        // else if (phaseCount == 8) {

        // if (counter < 50 + (int) Math.random() * 100) {
        // action = BattleBotArena.STAY;
        // counter++;
        // }

        // else {
        // counter = 0;
        // phaseCount++;
        // }

        // }

        // else {
        // phaseCount = 0;
        // }

        return action;

    }

    @Override
    public void draw(Graphics g, int x, int y) {
        // TODO Auto-generated method stub
        g.setColor(Color.green);
        g.fillRect(x + 2, y + 2, RADIUS * 2 - 4, RADIUS * 2 - 4);

    }

    @Override
    public String getName() {
        String name = "Shaun";
        return name;
    }

    @Override
    public String getTeamName() {
        // TODO Auto-generated method stub
        return "FAFNIR";
    }

    @Override
    public String outgoingMessage() {
        // TODO Auto-generated method stub
        String message = "TEST";
        return message;
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
