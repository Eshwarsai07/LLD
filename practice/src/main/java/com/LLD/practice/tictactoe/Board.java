package com.LLD.practice.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class Board {

	int size;
	PlayPiece[][] boardBox;

	public Board(int size) {
		this.size = size;
		boardBox = new PlayPiece[size][size];
	}

	public boolean addPiece(int row, int col, PlayPiece piece) {
		if (boardBox[row][col] != null) {
			return false;
		}
		boardBox[row][col] = piece;
		return true;
	}

	public List<CellLoc> getFreeCells() {
		List<CellLoc> freeCells = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				CellLoc rowCol = new CellLoc(i, j);
				if (boardBox[i][j] == null)
					freeCells.add(rowCol);
			}
		}

		return freeCells;
	}

	public void printBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if(boardBox[i][j] != null)
				System.out.print(boardBox[i][j].piece + "|");
				else
					System.out.print(" "+"|");
			}
			System.out.println();

		}
	}

}
