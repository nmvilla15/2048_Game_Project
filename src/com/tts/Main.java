package com.tts;

package com.codegym.games.game2048;
import com.codegym.engine.cell.*;

public class Game2048 extends Game{
    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];


    @Override
    public void initialize(){
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
        moveLeft();
        drawScene();
    }

    private void createGame(){
        createNewNumber();
        createNewNumber();
    };

    private void drawScene(){
        for(int x = 0; x < SIDE; x++){
            for(int y = 0; y < SIDE; y++){
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }

    private void createNewNumber(){
        int x = getRandomNumber(SIDE);
        int y = getRandomNumber(SIDE);

        if(gameField[x][y] == 0){
            int num = getRandomNumber(10);
            if (num == 9){
                gameField[x][y] = 4;
            } else {
                gameField[x][y] = 2;
            }
        } else {
            createNewNumber();
        }
    }

    private Color getColorByValue(int value){
        // It must return a cell color based on its value.
        switch(value){
            case 2:
                return Color.VIOLET;
            case 4:
                return Color.PURPLE;
            case 8:
                return Color.BLUE;
            case 16:
                return Color.AQUA;
            case 32:
                return Color.GREEN;
            case 64:
                return Color.YELLOW;
            case 128:
                return Color.ORANGE;
            case 256:
                return Color.LIGHTPINK;
            case 512:
                return Color.RED;
            case 1024:
                return Color.MAGENTA;
            case 2048:
                return Color.GOLD;
            default:
                return Color.WHITE;
        }
    }

    private void setCellColoredNumber(int x, int y, int value){
        if (value == 0) {
            setCellValueEx(x, y, getColorByValue(value), "");
        } else {
            setCellValueEx(x, y, getColorByValue(value), String.valueOf(value));
        }
    }

    private boolean compressRow(int[] row){
        int position =0;
        boolean result = false;
        for (int x = 0; x<row.length; x++){
            if(row[x]> 0){
                if (x!= position){
                    row[position]=row[x];
                    row[x]=0;
                    result=true;
                }
                position++;
            }
        }
        return result;
    }

    private boolean mergeRow(int[] row){
        boolean changed = false;
        for (int i = 1; i < SIDE; i++){
            if (row[i-1] == row[i] && row[i] !=0){
                row[i-1] = row[i] * 2;
                row[i] = 0;
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void onKeyPress(Key key){
        switch(key){
            case LEFT:
                moveLeft();
                drawScene();
                break;
            case RIGHT:
                moveRight();
                drawScene();
                break;
            case UP:
                moveUp();
                drawScene();
                break;
            case DOWN:
                moveDown();
                drawScene();
                break;
            default:
                return;
        }

    }

    private void moveLeft(){
        boolean ifCompress = false;
        boolean ifMerge = false;
        boolean needNewNumber = false;

        for (int[] row: gameField){
            ifCompress = compressRow(row);
            ifMerge = mergeRow(row);
            if(ifMerge){
                compressRow(row);
            }
            if(ifCompress || ifMerge){
                needNewNumber = true;
            }
        }
        if(needNewNumber){
            createNewNumber();
        }
    }

    private void moveRight(){

    }

    private void moveUp(){

    }

    private void moveDown(){

    }



}//only completed through step 10

