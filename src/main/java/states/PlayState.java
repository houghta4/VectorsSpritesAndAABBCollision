package states;

import entity.Player;
import graphics.Font;
import graphics.Sprite;
import util.KeyHandler;
import util.MouseHandler;
import util.Vector2f;

import java.awt.*;

public class PlayState extends GameState{

    private Font font;
    private Player player;

    public PlayState(GameStateManager gsm){
        super(gsm);
        font = new Font("font/ZeldaFont.png",  16, 16);
        player = new Player(new Sprite("entity/rockTest.png"), new Vector2f(300,300), 32);
    }

    public void update() {
        player.update();


    }

    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse, key);

    }

    public void render(Graphics2D g) {
        Sprite.drawArray(g, font, " this is a test THIS IS A TEST", new Vector2f(100,100), 32,32,12, 0);
        player.render(g);

    }


}
