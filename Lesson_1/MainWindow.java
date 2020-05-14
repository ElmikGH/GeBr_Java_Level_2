package Lesson_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class MainWindow extends JFrame {

    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;
    int objects = 100;
    Sprite[] sprites = new Sprite[objects];
    Background back;
    int clickCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
    }

    private MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        MainCanvas canvas = new MainCanvas(this);
        initBackground();
        initApplication();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ballClicker(e);
            }
        });
        add(canvas);
        setTitle("Circles");
        setVisible(true);
    }

    private void initBackground() {
        back = new Background(WINDOW_HEIGHT, WINDOW_WIDTH);
    }

    private void initApplication() {
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
    }

    void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (float i = 0; i < deltaTime; i++) {
            back.update(canvas, deltaTime);
        }
        for (int i = 0; i < clickCount; i++) {
            sprites[i].update(canvas, deltaTime);

        }
    }

    private void render(MainCanvas canvas, Graphics g) {
            back.render(canvas,g);
        for (int i = 0; i < clickCount; i++) {
            sprites[i].render(canvas, g);

        }
    }
    private void ballClicker(MouseEvent e){
        if (e.getButton() == MouseEvent.BUTTON1) {
            clickCount++;
        } else if (e.getButton() == MouseEvent.BUTTON3){
            clickCount--;
        }
        int x = e.getX();
        int y = e.getY();
        if (clickCount == sprites.length)
            clickCount--;
        else if (clickCount < 0) {
            clickCount = 0;
        }

    }
}
