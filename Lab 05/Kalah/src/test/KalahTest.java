package dominio;

import static org.junit.Assert.*;

import dominio.Kalah;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KalahTest {

    @Before
    public void setUp() {

    }

    @Test
    public void shouldCreateKalah() throws KalahGameException{
        Kalah kalah = new Kalah(6,3);
        assertEquals(3, kalah.getSeeds());
        assertEquals(6,kalah.getHouses());
    }

    @Test
    public void shouldGetBoard()throws KalahGameException{
        Kalah kalah = new Kalah(6,3);
        int[][] board = new int[2][6];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                board[i][j] = 3;
            }
        }
        assertEquals(board, kalah.board());
    }

    @Test
    public void shouldGetSeedsPlayer() throws KalahGameException{
        Kalah kalah = new Kalah(6,3);
        kalah.addSeed(5,0);
        assertEquals(4, kalah.getSeedsPlayer(5,0));
    }

    @Test
    public void shouldAddSeeds() throws KalahGameException{
        Kalah kalah = new Kalah(6,3);
        kalah.addSeeds(1,1, 5);
        assertEquals(8, kalah.getSeedsPlayer(1,1));
    }

    @Test
    public void shouldTakeSeedsOpositive()throws KalahGameException{
        Kalah kalah = new Kalah(6,3);
        kalah.addSeeds(1,1, 4);
        assertEquals(7, kalah.seedsInOpositeHouse(1,0));

    }

    @After
    public void tearDown(){
    }
}
