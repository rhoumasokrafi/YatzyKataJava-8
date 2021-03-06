package com.yatzy.kata.refactoring;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Yatzy {

	private final static int NB_OF_DICES = 5;
	private final static int MAX_VALUE_OF_DICE =6;
	
	private Integer[] valuesDices ;
	
	public Yatzy() {
	}
	
	public Yatzy(Integer ...dices){
		this.valuesDices = dices;
		try {
			validate(dices);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void validate(Integer ... dices) throws InvalidDicesException,NullPointerException {
		
		 if (dices == null){
	            throw new NullPointerException(String.format("le tableau de dices n'est pas initialiser"));
	      }
		 
        if (dices.length !=5){
            throw new InvalidDicesException(String.format("le nombre de dés de Rolling Dice est différent de %d",NB_OF_DICES));
        }

        for (int diceSide: dices) {
        if (diceSide > 6 )
            throw new InvalidDicesException(String.format("le valeur maximal du face du dés est %d",MAX_VALUE_OF_DICE));
        }
    }


	public static int chance(int... dices) {
		int total = Arrays.stream(dices).sum();
		return total;
	}

	public static int yatzy(Integer ... dices) {
		return Arrays.stream(dices).distinct().count() == 1 ? 50 :0;
	}

	public static int ones(Integer ... dices) {
		return getOccurencesOfValue(1,dices) * 1;
	}

	public static int twos(Integer ... dices) {
		return getOccurencesOfValue(2,dices) * 2;
	}

	public static int threes(Integer ... dices) {
		return getOccurencesOfValue(3,dices) * 3;
	}

	public int fours() {
		return getOccurencesOfValue(4,this.valuesDices) * 4;
	}

	public int fives() {
		return getOccurencesOfValue(5,this.valuesDices) * 5;
	}

	public int sixes() {
		return getOccurencesOfValue(6,this.valuesDices) * 6;
	}

	public static int score_pair(Integer ... dices) {
		return scoreOfPair(1,dices);
	}
	
    public static int two_pair(Integer ... dices) {
		return scoreOfPair(2,dices);
	}

	public static int three_of_a_kind(Integer ... dices) {
		return numberOfKind(3,dices);
	}

	public static int four_of_a_kind(Integer ... dices) {
		return numberOfKind(4,dices);
	}

	public static int smallStraight(Integer ... dices) {
		return checkStraight(15,dices)? 15 : 0;
	}

	
	public static int largeStraight(Integer ... dices) {
		return checkStraight(20,dices)? 20 : 0;
	}
	
	public static int fullHouse(int ... dices) {
		if(Arrays.stream(dices).distinct().count() != 2){
			return 0;
		}
		return Arrays.stream(dices).sum();
	}
	
    private static int scoreOfPair(int expectedPair,Integer ... dices) {
        Map<Integer,Long> mapGroupping = Stream.of(dices).collect(Collectors.groupingBy(Integer::valueOf,Collectors.counting()));
        if(expectedPair ==1){
        	Optional<Entry<Integer, Long>> getMax= mapGroupping.entrySet().stream().max( (e1,e2) -> {
            	if(e1.getValue()>=2  &&  e2.getValue() >=2){
            		return e1.getKey().compareTo(e2.getKey());
            	}
            	return e1.getValue().compareTo(e2.getValue());
            }
        	);
            return getMax.get().getKey() * 2;
        }

        return (int) mapGroupping.entrySet().stream().filter(v -> v.getValue() >= expectedPair).collect(Collectors.toList())
        		.stream().mapToLong(v -> v.getKey() * expectedPair).sum();
    }
    
	private  static int numberOfKind(int kind,Integer ... dices) {
		Map<Integer,Long> mapGroupping = Stream.of(dices).collect(Collectors.groupingBy(Integer::valueOf,Collectors.counting()));
		  return (int) mapGroupping.entrySet().stream().filter(v -> v.getValue() >= kind).collect(Collectors.toList())
	        		.stream().mapToLong(v -> v.getKey() * kind).sum();
	}

	private static boolean checkStraight(int totalSum,Integer[] dices){
        return Arrays.stream(dices).distinct().count() == NB_OF_DICES && Arrays.stream(dices).mapToInt( i->i*1).sum() == totalSum;
	}
	
	private static int getOccurencesOfValue(int value,Integer ... dices){
		List<Integer>listDices = Arrays.asList(dices);
		long countOccurencesOfValue = listDices
		    .stream()
		    .filter(i -> i.intValue() == value)
		    .count();
		return (int) countOccurencesOfValue;
	}
}
