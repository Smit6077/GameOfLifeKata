package gameoflife;

import java.util.Random;

public class Grid {
    // Nous avons eu l'aide de Florian LEFEVRE
    private Cell[][] cells;
    private Cell[][] game;
    private int sizeGrid;
    private Random rd;
    private int livingNeighbours;
    private int i;
    private int j;

    public Grid(int sizeGrid) {
        this.rd = new Random();
        this.sizeGrid = sizeGrid;
        generateRandomInitialState();
    }

    Grid(int sizeGrid, Cell[][] cells) {
        this.sizeGrid = sizeGrid;
        this.cells = cells;
    }

    private void generateRandomInitialState() {
        for (i = 0; i < sizeGrid; ++i) {
            for (j = 0; j < sizeGrid; ++j) {
                cells[i][j] = new Cell();
                if (rd.nextDouble() < 0.5) {
                    cells[i][j].setIsAlive(false);
                } else {
                    cells[i][j].setIsAlive(true);
                }
            }
        }
    }

    public void generateNextState() {
        this.game = new Cell[sizeGrid][sizeGrid];

        for(i=0;i<sizeGrid;i++){
            for(j=0;j<sizeGrid;j++){
                livingNeighbours = 0;

                //On met les valeurs du cells dans game qu'on modifie et retourne a la fin
                this.game[i][j] = this.cells[i][j];

                //Verification de la case en haut a gauche
                if(i==0 && j==0){
                    if(verifVoisinDroit(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }

                    if(verifVoisinDiagDB(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinBas(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    voisinVivant(i, j, livingNeighbours);
                }

                //Verification des cases au milieu de la premiere ligne
                if(i==0 && j>0 && j < sizeGrid-1){
                    if(verifVoisinDroit(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagDB(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinBas(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinGauche(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagGB(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }

                    voisinVivant(i, j, livingNeighbours);
                }

                //Verification de la case en haut a droite
                if(i==0 && j==sizeGrid-1 ){
                    if(verifVoisinBas(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinGauche(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagGB(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    voisinVivant(i, j, livingNeighbours);
                }

                //Verification de la case en bas a droite
                if(i==sizeGrid-1 && j==sizeGrid-1 ){
                    if(verifVoisinHaut(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinGauche(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagGH(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    voisinVivant(i, j, livingNeighbours);
                }

                //Verification de la case en bas a gauche
                if(i==sizeGrid-1 && j==0){
                    if(verifVoisinDroit(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagDH(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinHaut(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    voisinVivant(i, j, livingNeighbours);
                }

                //Verification des cases au milieu derniere ligne
                if(i==sizeGrid-1 && j>0 && j<sizeGrid-1){
                    if(verifVoisinDroit(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinGauche(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagDH(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagGH(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinHaut(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    voisinVivant(i, j, livingNeighbours);
                }

                //Verification des cases au milieu
                if(i > 0 && i < sizeGrid-1 && j > 0 && j < sizeGrid-1) {
                    if(verifVoisinDroit(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinHaut(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagDB(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinBas(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinGauche(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagGB(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagDH(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagGH(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    voisinVivant(i, j, livingNeighbours);
                }

                //Verification des cases au milieu de la derniere colonne
                if(i > 0 && j == sizeGrid-1 && i < sizeGrid-1) {
                    if(verifVoisinHaut(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinBas(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinGauche(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagGH(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagGB(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    voisinVivant(i, j, livingNeighbours);
                }

                //Verification des cases au milieu de la premiere colonne
                if(i > 0 && j == 0 && i < sizeGrid-1) {
                    if(verifVoisinHaut(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinBas(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDroit(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagDB(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    if(verifVoisinDiagDH(i,j)){
                        livingNeighbours = livingNeighbours +1;
                    }
                    voisinVivant(i, j, livingNeighbours);
                }
            }
        }
    }

    public boolean verifVoisinDroit(int i, int j){
        if(this.cells[i][j+1].toString().compareToIgnoreCase("X") == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean verifVoisinGauche(int i, int j){
        if(this.cells[i][j-1].toString().compareToIgnoreCase("X") == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean verifVoisinHaut(int i, int j){
        if(this.cells[i-1][j].toString().compareToIgnoreCase("X") == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean verifVoisinBas(int i, int j){
        if(this.cells[i+1][j].toString().compareToIgnoreCase("X") == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean verifVoisinDiagGH(int i, int j){
        if(this.cells[i-1][j-1].toString().compareToIgnoreCase("X") == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean verifVoisinDiagDH(int i, int j){
        if(this.cells[i-1][j+1].toString().compareToIgnoreCase("X") == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean verifVoisinDiagGB(int i, int j){
        if(this.cells[i+1][j-1].toString().compareToIgnoreCase("X") == 0){
            return true;
        } else {
            return false;
        }
    }

    public boolean verifVoisinDiagDB(int i, int j){
        if(this.cells[i+1][j+1].toString().compareToIgnoreCase("X") == 0){
            return true;
        } else {
            return false;
        }
    }

    public void voisinVivant(int i, int j, int livingNeighbours) {
        if(this.cells[i][j].isAlive() == true) {
            if(livingNeighbours == 2 || livingNeighbours == 3) {
                this.game[i][j].setIsAlive(true);
            } else {
                this.game[i][j].setIsAlive(false);
            }
        } else {
            if (livingNeighbours == 3) {
                this.game[i][j].setIsAlive(true);
            } else {
                this.game[i][j].setIsAlive(false);
            }
        }
    }

    public String toString() {
        String tab = "";
        for(i=0;i<sizeGrid;i++){
            for(j=0;j<sizeGrid;j++){
                tab += this.game[i][j];
                if(j!=sizeGrid-1){
                    tab += " ";
                }
            }
            if(i!=sizeGrid-1){
                tab += "\n";
            }
        }
        return tab;
    }
}

