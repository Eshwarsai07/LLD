package com.LLD.practice.tictactoe;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {

	public Deque<Player> players;

	public Board board;

	public Game() {
		initializeGame();
	}

	private void initializeGame() {

		board = new Board(3);
		players = new LinkedList();

		PlayPiece xPiece = new PlayPieceX();
		Player p1 = new Player("Eshwar", xPiece);

		PlayPiece yPiece = new PlayPieceY();
		Player p2 = new Player("sai", yPiece);

		players.add(p1);
		players.add(p2);

	}

	public String startGame() {
		boolean noWinner = true;
		while (noWinner) {
			Player playerTurn = players.removeFirst();
			board.printBoard();
			List<CellLoc> freeCells = board.getFreeCells();
			if (freeCells.isEmpty()) {
				noWinner = false;
				continue;
			}

			System.out.println(playerTurn.getName() + " Enter position -- ");
			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();
			String[] values = input.split(",");
			boolean pieceAdded = board.addPiece(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
					playerTurn.getPiece());
			if (!pieceAdded) {
				System.out.println("Entered position is worng please enter the correct position to proceed further");
				players.addFirst(playerTurn);
				continue;
			}

			boolean isThereWinner = isThereWinner(Integer.parseInt(values[0]), Integer.parseInt(values[1]),
					playerTurn.getPiece().piece);

			if (isThereWinner) {
				return playerTurn.getName();
			}
			
			players.addLast(playerTurn);

		}
		return "Tie";
	}

	private boolean isThereWinner(int int1, int int2, PieceType piece) {
		boolean rowMatch = true;
		boolean colMatch = true;
		boolean diagnalMatch = true;
		boolean crossDiagnalMatch = true;

		// row match
		for (int i = 0; i < board.size; i++) {
			if (board.boardBox[int1][i] == null || board.boardBox[int1][i].piece != piece) {
				rowMatch = false;
				break;
			}
		}

		// col match
		for (int i = 0; i < board.size; i++) {
			if (board.boardBox[i][int2] == null || board.boardBox[i][int2].piece != piece) {
				colMatch = false;
				break;
			}
		}

		// diagnaol match
		for (int i = 0, j = 0; i < board.size; i++, j++) {
			if (board.boardBox[i][j] == null || board.boardBox[i][j].piece != piece) {
				diagnalMatch = false;
				break;
			}
		}

		// crossDiagnal match
		for (int i = 0, j = board.size-1; i < board.size; i++, j--) {
			if (board.boardBox[i][j] == null || board.boardBox[i][j].piece != piece) {
				crossDiagnalMatch = false;
				break;
			}
		}

		return rowMatch || colMatch || diagnalMatch || crossDiagnalMatch;
	}

}
