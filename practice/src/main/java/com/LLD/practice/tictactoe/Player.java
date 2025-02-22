package com.LLD.practice.tictactoe;

public class Player {

	private String name;
	private PlayPiece piece;

	public Player(String name, PlayPiece piece) {
		this.name = name;
		this.piece = piece;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayPiece getPiece() {
		return piece;
	}

	public void setPiece(PlayPiece piece) {
		this.piece = piece;
	}

}
