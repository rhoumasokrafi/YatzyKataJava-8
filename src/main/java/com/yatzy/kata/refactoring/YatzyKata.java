package com.yatzy.kata.refactoring;

import java.util.Arrays;
import java.util.List;

public class YatzyKata {

	private final static int NB_OF_DICES = 5;
	private final static int MAX_VALUE_OF_DICE =6;
	
	private Integer[] dicesValues;
	
	public YatzyKata() {
	}
	
	public YatzyKata(Integer ...dices) {
		dicesValues = dices;
		validate();
	}

	public void validate() {
		
		 if (this.dicesValues == null){
	            throw new NullPointerException(String.format("le tableau de dices n'est pas initialiser"));
	      }
		 
        if (this.dicesValues.length !=5){
            throw new InvalidDicesException(String.format("le nombre de dés de Rolling Dice est différent de %d",NB_OF_DICES));
        }

        for (int diceSide: this.dicesValues) {
        if (diceSide > 6 )
            throw new InvalidDicesException(String.format("le valeur maximal du face du dés est %d",MAX_VALUE_OF_DICE));
        }
    }

	public int chance(int... dices) {
		int total = Arrays.stream(dices).sum();
		return total;
	}

	public int yatzy(Integer ... dices) {
		return Arrays.stream(dices).distinct().count() == 1 ? 50 :0;
	}

	public int ones() {
		return getOccurencesOfValue(1) * 1;
	}

	public int twos() {
		return getOccurencesOfValue(2) * 2;
	}

	public int threes() {
		return getOccurencesOfValue(3) * 3;
	}

	public int fours() {
		return getOccurencesOfValue(4) * 4;
	}

	public int fives() {
		return getOccurencesOfValue(5) * 5;
	}

	public int sixes() {
		return getOccurencesOfValue(6) * 6;
	}

	public int score_pair() {
		return scoreOfPair(1);
	}
	
    public int two_pair() {
		return scoreOfPair(2);
	}

	public int three_of_a_kind() {
		return numberOfKind(3);
	}

	public int four_of_a_kind() {
		return numberOfKind(4);
	}

	public int smallStraight() {
		return checkStraight(15)? 15 : 0;
	}

	
	public int largeStraight() {
		return checkStraight(20)? 20 : 0;
	}
	
	public int fullHouse(int ... dices) {
		if(Arrays.stream(dices).distinct().count() != 2){
			return 0;
		}
		return Arrays.stream(dices).sum();
	}
	
    private int scoreOfPair(int expectedPair) {
    	int existPairs = 0;
        int totalOfPair = 0;
        for (int i = 0; i <= NB_OF_DICES; i++)
            if (getOccurencesOfValue(6 - i) >= 2) {
            	existPairs++;
            	totalOfPair += (6 - i);
                if(existPairs >= expectedPair)
                    break;
            }
        if (existPairs >= expectedPair)
            return totalOfPair * 2;
        else
            return 0;
    }
    
	private  int numberOfKind(int kind) {
        for (int i = 0; i <= NB_OF_DICES; i++)
            if (getOccurencesOfValue(i + 1) >= kind)
                return (i + 1) * kind;
        return 0;
	}

	private boolean checkStraight(int totalSum){
        return Arrays.stream(dicesValues).distinct().count() == NB_OF_DICES 
        		&& Arrays.stream(dicesValues).mapToInt(i->i).sum() == totalSum;
	}
	
	private int getOccurencesOfValue(int value){
		List<Integer>listDices = Arrays.asList(dicesValues);
		long countOccurencesOfValue = listDices
		    .stream()
		    .filter(i -> i == value)
		    .count();
		return (int) countOccurencesOfValue;
	}
}
