package dominio;

import static org.junit.Assert.*;

import dominio.KalahGame;
import dominio.KalahGameException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KalahGameTest {

    @Before
    public void setUp() {

    }

    @Test
    public void shouldCreateKalahGame() throws KalahGameException {
        KalahGame kalahG = new KalahGame(6,3);
        assertEquals(1,kalahG.player());
    }

    @Test
    public void shouldsPlay()throws KalahGameException{
        KalahGame kalahG = new KalahGame(6,3);
        kalahG.changePlayer();
        kalahG.play(0,0);
        assertEquals(4,kalahG.getSeedsPlayer(1,1));
        kalahG.play(1,1);
        assertEquals(4,kalahG.getSeedsPlayer(5,1));
        kalahG.changePlayer();
        kalahG.play(0,1);
        assertEquals(1,kalahG.getSeedsPlayer(1,1));
        assertEquals(5,kalahG.getSeedsPlayer(4,1));
        kalahG.changePlayer();
        kalahG.play(4,1);
        assertEquals(4,kalahG.getSeedsPlayer(3,0));
    }

    @Test
    public void shouldsDeterminateWhoIsPlayer() throws KalahGameException{
        KalahGame kalahG = new KalahGame(6, 1);
        kalahG.play(5, 1);
        assertEquals(1, kalahG.player());
        assertEquals(1, kalahG.getSeedsInStore(1));
    }
    @Test
    public void shouldsDeterminateWhoIsPlayer2()throws KalahGameException {
        KalahGame kalahG = new KalahGame(6, 2);
        kalahG.play(5, 1);
        assertEquals(0, kalahG.player());
        assertEquals(1, kalahG.getSeedsInStore(1));
    }

    @Test
    public void shouldsPlay2()throws KalahGameException{
        KalahGame kalahG = new KalahGame(6,6);
        kalahG.play(0,1);
        assertEquals(1,kalahG.getSeedsInStore(1));
        assertEquals(7,kalahG.getSeedsPlayer(1,1));
        assertEquals(1,kalahG.player());
        kalahG.play(5,1);
        assertEquals(2,kalahG.getSeedsInStore(1));
        assertEquals(0,kalahG.getSeedsPlayer(5,1));
        assertEquals(7,kalahG.getSeedsPlayer(0,0));
        assertEquals(0,kalahG.player());
        kalahG.play(2,0);
        assertEquals(1,kalahG.getSeedsInStore(0));
        assertEquals(1,kalahG.getSeedsPlayer(0,1));
        assertEquals(8,kalahG.getSeedsPlayer(3,1));
        assertEquals(8,kalahG.getSeedsPlayer(1,0));
        assertEquals(1,kalahG.player());
        kalahG.play(2,1);
        assertEquals(3,kalahG.getSeedsInStore(1));
        assertEquals(1,kalahG.getSeedsPlayer(5,1));
        assertEquals(9,kalahG.getSeedsPlayer(3,1));
        assertEquals(1,kalahG.getSeedsPlayer(2,0));
        assertEquals(0,kalahG.player());
        kalahG.play(4,0);
        assertEquals(2,kalahG.getSeedsInStore(0));
        assertEquals(2,kalahG.getSeedsPlayer(0,1));
        assertEquals(9,kalahG.getSeedsPlayer(1,1));
        assertEquals(9,kalahG.getSeedsPlayer(1,0));
        assertEquals(0,kalahG.getSeedsPlayer(4,0));
        assertEquals(1,kalahG.player());
        kalahG.play(5,1);
        assertEquals(1,kalahG.player());
        kalahG.play(2,1);
        assertEquals(0,kalahG.getSeedsPlayer(2,1));
        assertEquals(10,kalahG.getSeedsPlayer(3,1));
        assertEquals(0,kalahG.player());
        kalahG.play(0,0);
        assertEquals(4,kalahG.getSeedsInStore(1));
        assertEquals(13,kalahG.getSeedsInStore(0));
        assertEquals(0,kalahG.getSeedsPlayer(0,0));
        kalahG.play(0,1);
        assertEquals(4,kalahG.getSeedsInStore(1));
        assertEquals(11,kalahG.getSeedsPlayer(1,1));
        assertEquals(2,kalahG.getSeedsPlayer(2,1));
        assertEquals(12,kalahG.getSeedsPlayer(3,1));
        kalahG.play(3,0);
        assertEquals(14,kalahG.getSeedsInStore(0));
        assertEquals(1,kalahG.getSeedsPlayer(0,1));
        assertEquals(13,kalahG.getSeedsPlayer(3,1));
        assertEquals(1,kalahG.getSeedsPlayer(5,1));
        assertEquals(0,kalahG.getSeedsPlayer(4,0));
        kalahG.play(5,1);
        assertEquals(5,kalahG.getSeedsInStore(1));
        kalahG.play(4,1);
        assertEquals(15,kalahG.getSeedsInStore(1));
        assertEquals(0,kalahG.getSeedsPlayer(5,0));
        assertEquals(0,kalahG.getSeedsPlayer(5,1));

    }

    @Test
    public void shouldsPlay3() throws KalahGameException{
        KalahGame kalahG = new KalahGame(2,1);
        kalahG.play(1,1);
        assertEquals(1,kalahG.getSeedsInStore(1));
        assertEquals(1,kalahG.player());
        kalahG.play(0,1);
        assertEquals(3,kalahG.getSeedsInStore(1));
        assertEquals(0,kalahG.getSeedsPlayer(0,1));
        assertEquals(0,kalahG.getSeedsPlayer(0,1));
        assertEquals(0,kalahG.getSeedsPlayer(1,0));
        assertEquals(true,kalahG.isOverGame());
    }

    @Test
    public void shouldsCreateKalahGameException() throws KalahGameException{
        try{
            KalahGame kalahG = new KalahGame(0,1);
        } catch (KalahGameException e){
            assertEquals(e.getMessage(), KalahGameException.RANGE_HOUSES);
        }
    }

    @Test
    public void shouldsPlayException() throws KalahGameException{
        try{
            KalahGame kalahG = new KalahGame(6,1);
            kalahG.play(-1,1);
        } catch (KalahGameException e){
            assertEquals(e.getMessage(), KalahGameException.RANGE_HOUSES);
        }
    }

    @Test
    public void shouldsPlayTurnException() throws KalahGameException{
        try{
            KalahGame kalahG = new KalahGame(6,1);
            kalahG.play(0,0);
        } catch (KalahGameException e){
            assertEquals(e.getMessage(), KalahGameException.PLAYER_TURN);
        }
    }

    @After
    public void tearDown(){
    }
}
