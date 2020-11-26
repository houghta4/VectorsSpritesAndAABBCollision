package util;
import GameStuff.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class KeyHandler implements KeyListener {

    public static List<Key> keys = new ArrayList<>();

    public KeyHandler(GamePanel game) {
        game.addKeyListener(this);

    }


    //each key will have its own class
    public class Key {
        public int presses;
        public int absorbs;
        public boolean down, clicked;

        public Key() {
            keys.add(this);
        }

        public void toggle(boolean pressed) {
            if (pressed != down) {
                down = pressed;
            }
            if (pressed) {
                presses++;
                //System.out.println(presses);
            }
        }


        public void tick() {
            if (absorbs < presses) {
                absorbs++;
                clicked = true;
            } else {
                clicked = false;
            }
        }
    }

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key attack = new Key();
    public Key menu = new Key();
    public Key enter = new Key();
    public Key escape = new Key();


    public void releaseAll() {
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).down = false;
        }
    }

    public void tick() {
        for (int i = 0; i < keys.size(); i++) {
            keys.get(i).tick();
        }
    }

    public void toggle(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) up.toggle(pressed);
            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                down.toggle(pressed);
                tick();
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) left.toggle(pressed);
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) right.toggle(pressed);
            if (e.getKeyCode() == KeyEvent.VK_SPACE) attack.toggle(pressed);
            if (e.getKeyCode() == KeyEvent.VK_E) menu.toggle(pressed);
            if (e.getKeyCode() == KeyEvent.VK_ENTER) enter.toggle(pressed);
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) escape.toggle(pressed);
        }


        public void keyTyped (KeyEvent e){

        }

        public void keyPressed (KeyEvent e){
            toggle(e, true);

        }

        public void keyReleased (KeyEvent e){
            toggle(e, false);
        }
    }

