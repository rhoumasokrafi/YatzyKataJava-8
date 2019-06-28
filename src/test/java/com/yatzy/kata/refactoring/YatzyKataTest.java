package com.yatzy.kata.refactoring;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class YatzyKataTest {

		private YatzyKata yatzyKata= null;

		@Test(expected = com.yatzy.kata.refactoring.InvalidDicesException.class)
		public void Validate_number_Of_Dice(){
			//given
	        yatzyKata = new YatzyKata(1,2,3,4,5,6);
	        //when
	        yatzyKata.validate();
	        //then	
		}
		
		@Test(expected = com.yatzy.kata.refactoring.InvalidDicesException.class)
	    public void Validate_Value_Of_Dice(){
	        //given
			yatzyKata = new YatzyKata(1,2,3,21,5);
	        //when
			yatzyKata.validate();
	        //then
	    }
	 @Test
	    public void chance_scores_sum_of_all_dice() {
		 	//given
		    yatzyKata = new YatzyKata();
		    //when
	        int expected = 15;
	        int actual = yatzyKata.chance(2,3,4,5,1);
	        //then
	        assertEquals(expected, actual);
	        assertEquals(16, yatzyKata.chance(3,3,4,5,1));
	    }
	 

	    @Test public void YatzyKata_scores_50() {
	    	//given
		    yatzyKata = new YatzyKata();
		    //when
	        int expected = 50;
	        int actual = yatzyKata.yatzy(4,4,4,4,4);
	        //then
	        assertEquals(expected, actual);
	        assertEquals(50, yatzyKata.yatzy(6,6,6,6,6));
	        assertEquals(0, yatzyKata.yatzy(6,6,6,6,3));
	    }

	    @Test public void test_1s() {
	    	//given
		    yatzyKata = new YatzyKata();
		    //when
		    //then
	        assertTrue(new YatzyKata(1,2,3,4,5).ones() == 1);
	        assertEquals(2, new YatzyKata(1,2,1,4,5).ones());
	        assertEquals(0, new YatzyKata(6,2,2,4,5).ones());
	        assertEquals(4, new YatzyKata(1,2,1,1,1).ones());
	    }

	    @Test
	    public void test_2s() {
	    	//given
		    //when
		    //then
	        assertEquals(4, new YatzyKata(1,2,3,2,6).twos());
	        assertEquals(10, new YatzyKata(2,2,2,2,2).twos());
	    }

	    @Test
	    public void test_threes() {
	    	//given
		    //when
		    //then
	        assertEquals(6, new YatzyKata(1,2,3,2,3).threes());
	        assertEquals(12, new YatzyKata(2,3,3,3,3).threes());
	    }

	    @Test
	    public void fours_test() {
	    	//given
		    //when
		    //then
	        assertEquals(12, new YatzyKata(4,4,4,5,5).fours());
	        assertEquals(8, new YatzyKata(4,4,5,5,5).fours());
	        assertEquals(4, new YatzyKata(4,5,5,5,5).fours());
	    }

	    @Test
	    public void fives() {
	    	//given
		    //when
		    //then
	        assertEquals(10, new YatzyKata(4,4,4,5,5).fives());
	        assertEquals(15, new YatzyKata(4,4,5,5,5).fives());
	        assertEquals(20, new YatzyKata(4,5,5,5,5).fives());
	    }

	    @Test
	    public void sixes_test() {
	    	//given
		    //when
		    //then
	        assertEquals(0, new YatzyKata(4,4,4,5,5).sixes());
	        assertEquals(6, new YatzyKata(4,4,6,5,5).sixes());
	        assertEquals(18, new YatzyKata(6,5,6,6,5).sixes());
	    }

	    @Test
	    public void one_pair() {
	    	//given
		    //when
		    //then
	        assertEquals(6, new YatzyKata(3,4,3,5,6).score_pair());
	        assertEquals(10, new YatzyKata(5,3,3,3,5).score_pair());
	        assertEquals(12, new YatzyKata(5,3,6,6,5).score_pair());
	    }

	    @Test
	    public void two_Pair() {
	    	//given
		    //when
		    //then
	        assertEquals(16, new YatzyKata(3,3,5,4,5).two_pair());
	        assertEquals(16, new YatzyKata(3,3,5,5,5).two_pair());
	    }

	    @Test
	    public void three_of_a_kind(){
	    	//given
		    //when
		    //then
	        assertEquals(9, new YatzyKata(3,3,3,4,5).three_of_a_kind());
	        assertEquals(15, new YatzyKata(5,3,5,4,5).three_of_a_kind());
	        assertEquals(9, new YatzyKata(3,3,3,3,5).three_of_a_kind());
	    }

	    @Test
	    public void four_of_a_knd() {
	    	//given
		    //when
		    //then
	        assertEquals(12, new YatzyKata(3,3,3,3,5).four_of_a_kind());
	        assertEquals(20, new YatzyKata(5,5,5,4,5).four_of_a_kind());
	        assertEquals(12, new YatzyKata(3,3,3,3,3).four_of_a_kind());
	    }

	    @Test
	    public void smallStraight() {
	    	//given
		    //when
		    //then
	        assertEquals(15, new YatzyKata(1,2,3,4,5).smallStraight());
	        assertEquals(15, new YatzyKata(2,3,4,5,1).smallStraight());
	        assertEquals(0, new YatzyKata(1,2,2,4,5).smallStraight());
	    }

	    @Test
	    public void largeStraight() {
	    	//given
		    //when
		    //then
	        assertEquals(20, new YatzyKata(6,2,3,4,5).largeStraight());
	        assertEquals(20, new YatzyKata(2,3,4,5,6).largeStraight());
	        assertEquals(0, new YatzyKata(1,2,2,4,5).largeStraight());
	    }

	    @Test
	    public void fullHouse() {
	    	//given
		    yatzyKata = new YatzyKata();
		    //when
		    //then
	        assertEquals(18, yatzyKata.fullHouse(6,2,2,2,6));
	        assertEquals(0, yatzyKata.fullHouse(2,3,4,5,6));
	    }
}
