package Lesson_1;

import java.awt.*;

import static javafx.scene.paint.Color.color;

public class Background extends  Sprite{
    Color colorBack = new Color(0,0,0);
    float [] colorArray = {(float)(Math.random() * 255),(float)(Math.random() * 255),(float)(Math.random() * 255)};
    int x;
    int y;
    float speed = 1;
    float [] speedArray = {speed, speed, speed};

    Background (int height, int width) {
       x = width;
       y = height;

    }
    @Override
    public void update(MainCanvas canvas, float deltaTime){
        for (int i = 0; i<colorArray.length; i++){
            if (colorArray[i] >= 255f){
                speedArray[i] = -1;
            } else if (colorArray[i] <= 0f ){
                speedArray[i]= 1;
            } else {
                speedArray[i] = speedArray[i];
            }
            colorArray[i] += speedArray[i];
        }
        Color colorRand = new Color((int)colorArray[0],(int)colorArray[1],(int)colorArray[2]);
        colorBack = colorRand;

    }
    @Override
    public void render(MainCanvas canvas, Graphics g) {
        g.setColor(colorBack);
        g.fillRect(0,0,x,y);
    }
}
