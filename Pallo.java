

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mikko
 */
public class Pallo {

    private Polygon pallo;
    private Point2D liike;
    private int liikesuuntaX;
    private int liikesuuntaY;
    private int yKulma;
    private int nopeus;

    public Pallo(int x, int y) {
        this.pallo = new Polygon(0, 0, 0, 15, 15, 15, 15, 0, 0, 0);
        
        this.pallo.setFill(Color.BLUE);
        this.pallo.setTranslateX(x);
        this.pallo.setTranslateY(y);
        this.liike = new Point2D(3, 8);
        this.liikesuuntaX = 1;
        this.liikesuuntaY = 1;
        this.yKulma = 8;
        this.nopeus = 3;

    }

    public int getLiikesuuntaX() {
        return liikesuuntaX;
    }
    
    
    
    public void resetoi(int x, int y) {
        this.pallo.setTranslateX(x);
        this.pallo.setTranslateY(y);
        arvoNopeus();
        arvoYKulma();
        
    }
    
    public void vaihdaLiikesuuntaX() {
        this.liikesuuntaX = this.liikesuuntaX * (-1);
        liiku();

    }
    
    public void arvoNopeus() {
        this.nopeus = new Random().nextInt(5) + 3;
        this.liike = new Point2D(this.nopeus, this.yKulma);
    }
    
    public void arvoYKulma() {
        this.yKulma = new Random().nextInt(10);
        this.liike = new Point2D(this.nopeus, this.yKulma);
    }

    public void vaihdaLiikesuuntaY() {
        this.liikesuuntaY = this.liikesuuntaY * (-1);
    }

    public void liiku() {

        if (this.pallo.getTranslateY() + this.liike.getY() * this.liikesuuntaY <= 0 || this.pallo.getTranslateY() + this.liike.getY() * this.liikesuuntaY >= Pong.KORKEUS) {
            this.vaihdaLiikesuuntaY();
        }

        this.pallo.setTranslateX(this.pallo.getTranslateX() + (this.liike.getX() * this.liikesuuntaX));
        this.pallo.setTranslateY(this.pallo.getTranslateY() + (this.liike.getY() * this.liikesuuntaY));
    }

    public Polygon getPallo() {
        return pallo;
    }
    
    public double getPalloPosX() {
        return this.pallo.getTranslateX();
    }
    
    public double getPalloPosY() {
        return this.pallo.getTranslateY();
    }
    
    public int getLiikesuuntaY() {
        return this.liikesuuntaY;
    }

    public Point2D getLiike() {
        return liike;
    }

    public void setLiike(Point2D liike) {
        this.liike = liike;
    }

    public void setLiikesuuntaX(int liikesuuntaX) {
        this.liikesuuntaX = liikesuuntaX;
    }

    public void setLiikesuuntaY(int liikesuuntaY) {
        this.liikesuuntaY = liikesuuntaY;
    }

    
    public boolean tormaa(Maila maila) {
        Shape tormaysalue = Shape.intersect(this.pallo, maila.getMaila());
        return tormaysalue.getBoundsInLocal().getWidth() != -1;
    }
    
    public int maali() {
        if(pallo.getTranslateX() < 0) {
            return 2;
        }
        
        if(pallo.getTranslateX() > Pong.LEVEYS) {
            return 1;
        }
        
        return 0;
    }
}
