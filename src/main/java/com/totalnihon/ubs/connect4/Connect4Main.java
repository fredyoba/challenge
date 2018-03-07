package com.totalnihon.ubs.connect4;

import com.totalnihon.ubs.connect4.infrastructure.exception.ColumnIsFullException;
import com.totalnihon.ubs.connect4.infrastructure.exception.ColumnOutOfBoudariesException;
import com.totalnihon.ubs.connect4.infrastructure.exception.GameTerminatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Connect4Main {
	final public static String WRONG_INPUT = "Wrong Input";
	
	public static void main(String args[])  {
		GameEngine ge = new GameEngine();
		int column;
		Scanner scanner = new Scanner(System.in);
		boolean success = false;

		ge.startNewGame();
		ge.printBoard();
		
		do {
		    //  prompt for the user's name
		    System.out.print((ge.getTurn()? GameEngine.PLAYERA:GameEngine.PLAYERB) +  "[" + (ge.getTurn()? GameEngine.PLAYERA_COLOR:GameEngine.PLAYERB_COLOR) + "] - choose column (1-7): ");
	
		    // get their input as a String
		    try {
				success = false;
		    	column = scanner.nextInt();

				ge.play(ge.getTurn(), column);
				success = true;
			} catch(InputMismatchException ime) {
				 if("u".equalsIgnoreCase(scanner.next())) {
				 	ge.undo();
					 success = true;
				 } else{
					 System.out.println(WRONG_INPUT);
				}
		    } catch (ColumnIsFullException | ColumnOutOfBoudariesException | GameTerminatedException  e) {
				System.out.println(e.getMessage());
			} finally {
		    	if(success) {
					ge.printBoard();
					ge.toggleTurn();
				}
			}
		} while(!ge.isGameFinished());
	
		scanner.close();
		System.out.println(ge.displayWinner());
		System.out.println("````");
		System.out.println(ge.getPlayHistory());
	}
}
