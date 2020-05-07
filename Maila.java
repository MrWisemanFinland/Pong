
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mikko
 */
public class Maila {

    private Polygon maila;
    private Point2D liike;
    private boolean AI;

    public Maila(int x, int y) {
        this.maila = new Polygon(0, 0, 0, 70, 10, 70, 10, 0, 0, 0);
        this.maila.setTranslateX(x);
        this.maila.setTranslateY(y);
        this.liike = new Point2D(0, 0);
        this.AI = false;
    }

    public boolean isAI() {
        return AI;
    }

    public void setAI(boolean AI) {
        this.AI = AI;
    }

    public void liikuYlos() {
        if (this.maila.getTranslateY() > (0 - 70)) {
            this.maila.setTranslateY(this.maila.getTranslateY() - 5);
        }
    }

    public void liikuAlas() {
        if (this.maila.getTranslateY() < (Pong.KORKEUS + 70)) {
            this.maila.setTranslateY(this.maila.getTranslateY() + 5);
        }
    }

    public Polygon getMaila() {
        return maila;
    }

    public Point2D getLiike() {
        return liike;
    }

    public void setLiike(Point2D liike) {
        this.liike = liike;
    }

    public double reaktioFaktori() {
        int i = new Random().nextInt(50);
        double f = i / 100 + 0.50;
        return f;
    }

    public double getMailaPosX() {
        return this.maila.getTranslateX();
    }

    public double getMailaPosY() {
        return this.maila.getTranslateY();
    }
}
