package com.totalnihon.ubs.connect4;

import static org.junit.jupiter.api.Assertions.*;
import com.totalnihon.ubs.connect4.GameEngine;
import com.totalnihon.ubs.connect4.infrastructure.exception.ColumnIsFullException;
import com.totalnihon.ubs.connect4.infrastructure.exception.ColumnOutOfBoudariesException;
import com.totalnihon.ubs.connect4.infrastructure.exception.GameTerminatedException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
class GameEngineTest {
    GameEngine gameEngine;


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        gameEngine = new GameEngine();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    private void runTheScenario(Integer[] scenario) throws ColumnIsFullException, GameTerminatedException, ColumnOutOfBoudariesException {
        for(Integer i:scenario) {
            gameEngine.play(gameEngine.getTurn(), i);
        }
    }

    @Test
    public void testGameCanDetectWinner() throws ColumnIsFullException, ColumnOutOfBoudariesException, GameTerminatedException {
        Integer[] verticalSequence = {1, 2, 1, 3, 1, 2, 1};
        Integer[] horizontalSequence = {3, 3, 4, 4, 5, 5, 6};
        Integer[] leftToRightBottomToTopDiagonalSequence = {2, 3, 3, 4, 5, 4, 4, 5, 6, 5, 5};
        Integer[] leftToRightTopToBottomDiagonalSequence = {4, 3, 3, 2, 1, 2, 2, 1, 7, 1, 1};

        gameEngine.startNewGame();
        runTheScenario(verticalSequence);
        assertTrue(gameEngine.getWinner() == GameEngine.STATE.PLAYERA);

        gameEngine.startNewGame();
        runTheScenario(horizontalSequence);
        assertTrue(gameEngine.getWinner() == GameEngine.STATE.PLAYERA);

        gameEngine.startNewGame();
        runTheScenario(leftToRightBottomToTopDiagonalSequence);
        assertTrue(gameEngine.getWinner() == GameEngine.STATE.PLAYERA);

        gameEngine.startNewGame();
        runTheScenario(leftToRightTopToBottomDiagonalSequence);
        assertTrue(gameEngine.getWinner() == GameEngine.STATE.PLAYERA);
    }

    @Test
    public void testCannotExceedColumns()  throws ColumnOutOfBoudariesException, GameTerminatedException {
        Integer[] verticalSequence = {2, 2, 2, 2, 2, 2, 2};

        gameEngine.startNewGame();

        try {
            runTheScenario(verticalSequence);
            assertTrue(false); // cannot reach here
        } catch(ColumnIsFullException cfe) {
            assertTrue(true);
        }
    }

    @Test
    public void testCannotPlayOutsideBoundaries() throws ColumnIsFullException, GameTerminatedException {
        Integer[] sequence = {0, -1, 8};
        gameEngine.startNewGame();

        // save the turn to control it did not change despite the play outside boundaries
        boolean turn = gameEngine.getTurn();

        for(Integer i:sequence) {
            try {
                gameEngine.play(gameEngine.getTurn(), i);
                assertFalse(true); // we should never reach here, if so test is failed
            } catch(ColumnOutOfBoudariesException cobe) {
                assertEquals(gameEngine.getTurn(), turn); // turn hasn't changed
                assertEquals(gameEngine.getWinner(), GameEngine.STATE.EMPTY); // still no winner
            }
        }
    }

    @Test
    public void testTurnChangesAfterEachPlay() throws ColumnIsFullException, GameTerminatedException,  ColumnOutOfBoudariesException {
        Integer[] scenario = {1, 2, 3, 5, 4, 7, 7, 6, 7, 6, 5, 4, 3, 2, 1, 1, 1, 2, 2, 3, 4, 7, 6, 5, 4, 3, 1, 5, 6, 6, 7, 2, 3, 1, 6, 3, 4, 4, 2, 5, 5, 7};
        boolean turn;

        gameEngine.startNewGame();

        for(Integer i:scenario) {
            turn = gameEngine.getTurn();
            gameEngine.play(gameEngine.getTurn(), i);
            assertEquals(gameEngine.getTurn(), !turn);
        }
    }

    @Test
    public void testNoWinner() throws ColumnIsFullException, GameTerminatedException,  ColumnOutOfBoudariesException {
        Integer[] scenario = {1, 2, 3, 5, 4, 7, 7, 6, 7, 6, 5, 4, 3, 2, 1, 1, 1, 2, 2, 3, 4, 7, 6, 5, 4, 3, 1, 5, 6, 6, 7, 2, 3, 1, 6, 3, 4, 4, 2, 5, 5, 7};

        gameEngine.startNewGame();
        runTheScenario(scenario);

        assertEquals(gameEngine.getWinner(), GameEngine.STATE.EMPTY);
        assertEquals(gameEngine.isGameFinished(), true);
    }
}