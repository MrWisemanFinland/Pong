

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.*;
import java.lang.System.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mikko
 */
public class Pong extends Application {

    public static int LEVEYS = 600;
    public static int KORKEUS = 400;

    @Override
    public void start(Stage stage) {

        Pane ruutu = new Pane();
        ruutu.setPrefSize(LEVEYS, KORKEUS);

        Maila maila1 = new Maila(50 - 15, 50);
        Maila maila2 = new Maila(550, 50);
        Pallo pallo = new Pallo(75, 60);
        Line verkko = new Line(LEVEYS / 2, 0, LEVEYS / 2, KORKEUS);
        verkko.getStrokeDashArray().addAll(10d);
        verkko.setFill(Color.GRAY);

        if (Valinta.valinta == 1) {
            maila1.setAI(false);
            maila2.setAI(false);
        }
        if (Valinta.valinta == 2) {
            maila1.setAI(false);
            maila2.setAI(true);
        }
        if (Valinta.valinta == 3) {
            maila1.setAI(true);
            maila2.setAI(true);
        }

        ruutu.getChildren().add(maila1.getMaila());
        ruutu.getChildren().add(maila2.getMaila());
        ruutu.getChildren().add(pallo.getPallo());
        ruutu.getChildren().add(verkko);
        Scene scene = new Scene(ruutu);

        Map<KeyCode, Boolean> painetutNapit = new HashMap<>();

        scene.setOnKeyPressed(event -> {
            painetutNapit.put(event.getCode(), Boolean.TRUE);
        });

        scene.setOnKeyReleased(event -> {
            painetutNapit.put(event.getCode(), Boolean.FALSE);
        });

        AtomicInteger p1 = new AtomicInteger();
        AtomicInteger p2 = new AtomicInteger();

        Text pisteet1 = new Text(10, 20, "P1: 0");
        Text pisteet2 = new Text(Pong.LEVEYS - 50, 20, "P2: 0");

        ruutu.getChildren().add(pisteet1);
        ruutu.getChildren().add(pisteet2);

        new AnimationTimer() {
            long edellinen = 0;

            @Override
            public void handle(long nykyhetki) {
                //Animaatio
                if (nykyhetki < edellinen) {
                    return;
                }

                this.edellinen = nykyhetki;

                if (maila1.isAI() == false) {
                    if (painetutNapit.getOrDefault(KeyCode.Q, Boolean.FALSE)) {
                        maila1.liikuYlos();
                    }

                    if (painetutNapit.getOrDefault(KeyCode.A, Boolean.FALSE)) {
                        maila1.liikuAlas();
                    }
                }

                if (maila2.isAI() == false) {
                    if (painetutNapit.getOrDefault(KeyCode.UP, Boolean.FALSE)) {
                        maila2.liikuYlos();
                    }

                    if (painetutNapit.getOrDefault(KeyCode.DOWN, Boolean.FALSE)) {
                        maila2.liikuAlas();
                    }
                }

                if (maila1.isAI() == true && pallo.getPalloPosX() < (Pong.LEVEYS * maila1.reaktioFaktori()) && pallo.getLiikesuuntaX() < 0) {

                    if (pallo.getPalloPosY() > maila1.getMailaPosY()) {
                        maila1.liikuAlas();
                    }

                    if (pallo.getPalloPosY() < maila1.getMailaPosY()) {
                        maila1.liikuYlos();
                    }
                }

                if (maila2.isAI() == true && pallo.getPalloPosX() > (Pong.LEVEYS * maila2.reaktioFaktori()) && pallo.getLiikesuuntaX() > 0) {
                    if (pallo.getPalloPosY() > maila2.getMailaPosY()) {
                        maila2.liikuAlas();
                    }

                    if (pallo.getPalloPosY() < maila2.getMailaPosY()) {
                        maila2.liikuYlos();
                    }
                }
                pallo.liiku();

                if (pallo.maali() == 1) {
                    pisteet1.setText("P1: " + p1.addAndGet(1));
                    pallo.vaihdaLiikesuuntaX();
                    pallo.resetoi(Pong.LEVEYS - 75, 60);
                    edellinen += 990000000;

                }

                if (pallo.maali() == 2) {
                    pisteet2.setText("P2: " + p2.addAndGet(1));
                    pallo.vaihdaLiikesuuntaX();
                    pallo.resetoi(75, 60);
                    edellinen += 990000000;
                }

                if (pallo.tormaa(maila1) || pallo.tormaa(maila2)) {
                    pallo.vaihdaLiikesuuntaX();
                    pallo.arvoYKulma();
                    pallo.arvoNopeus();
                }
            }

        }.start();

        stage.setTitle("Pong!");
        stage.setScene(scene);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                System.exit(0);
            }
        });
        
        stage.show();
    }

}
