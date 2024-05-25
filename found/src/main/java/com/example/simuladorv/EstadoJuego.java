package com.example.simuladorv;

import java.io.Serializable;

public class EstadoJuego implements Serializable {
    private static final long serialVersionUID = 1L;
    private int posicionX;
    private int posicionY;
    private int score;
    private int filas;
    private int columnas;

    // Constructor, getters y setters
    public EstadoJuego(int posicionX, int posicionY, int score, int filas, int columnas){
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.score = score;
        this.filas = filas;
        this.columnas = columnas;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public int getScore() {
        return score;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    // Método para obtener el layout del tablero
    public String getBoardLayout() {
        return "Filas: " + filas + ", Columnas: " + columnas;
    }
}