package entity;

import graphics.Animation;
import graphics.Sprite;
import util.AABB;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    //these are teh rows of sprite sheet!
    private final int UP = 1;
    private final int DOWN = 3;
    private final int RIGHT = 0;
    private final int LEFT = 2;
    protected int currentAnimation;


    //any class that extends this can use
    protected Sprite sprite;
    protected Vector2f pos;
    protected Animation ani;
    protected int size;

    protected boolean up;
    protected boolean down;
    protected boolean right;
    protected boolean left;
    protected boolean attack;
    protected int attackSpeed;
    protected int attackDuration;

    //wont need
    protected float dx;
    protected float dy;
    protected float maxSpeed = 4f;
    protected float acc = 3f;
    protected float deacc = .5f;
    protected float distanceTravelled;

    protected AABB hitBounds;
    protected AABB bounds;


    public Entity(Sprite sprite, Vector2f origin, int size){
        this.sprite = sprite;
        pos = origin;
        this.size = size;

        bounds = new AABB(origin, size, size);
        hitBounds = new AABB(new Vector2f(origin.x + (size / 2), origin.y), size, size);

        ani = new Animation();
        setAnimation(RIGHT, sprite.getSpriteArray(RIGHT), 10);
    }

    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    public void setSize(int i){
        size = i;
    }
    public void setMaxSpeed(float f){ maxSpeed = f;}


    public int getSize(){ return size;}

    public Animation getAnimation(){ return ani;}

    public void setAnimation(int i, BufferedImage[] frames, int delay){
        currentAnimation = i;
        ani.setFrames(frames);
        ani.setDelay(delay);
    }

    //
    public void animate(){
        if(up){
            if(currentAnimation != UP || ani.getDelay() == -1){
                setAnimation(UP,sprite.getSpriteArray(UP), 5);
            }
        }else if(down){
            if(currentAnimation != DOWN || ani.getDelay() == -1){
                setAnimation(DOWN,sprite.getSpriteArray(DOWN), 5);
            }
        }else if(left){
            if(currentAnimation != LEFT || ani.getDelay() == -1){
                setAnimation(LEFT,sprite.getSpriteArray(LEFT), 5);
            }
        }else if(right){
            if(currentAnimation != RIGHT || ani.getDelay() == -1){
                setAnimation(RIGHT,sprite.getSpriteArray(RIGHT), 5);
            }
        }else{
            setAnimation(currentAnimation, sprite.getSpriteArray(currentAnimation), -1);
        }
    }

    private void setHitBoxDirection(){
        if(up){
            hitBounds.setyOffset(-size / 2);
            hitBounds.setxOffset(-size / 2);
        }else if(down){
            hitBounds.setyOffset(size / 2);
            hitBounds.setxOffset(-size / 2);

        }else if(left){
            hitBounds.setxOffset(-size);
            hitBounds.setyOffset(0);

        }else if(right){
            hitBounds.setxOffset(0);
            hitBounds.setyOffset(0);

        }
    }

    public void update(){
        animate();
        setHitBoxDirection();
        ani.update();
    }

    public abstract void render(Graphics2D g);
    public void input(KeyHandler key, MouseHandler mouse){};


}
