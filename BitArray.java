package com.arthur.delicious.bloomfilter;

public class BitArray {
	
	private int capacity;
	private int bit_length;
	private int[] array;
	private int BIT_NUM_PER_BUCKET = 1;
	private int BIT_NUM_PER_INT = 8;
	
	/**
	 * capacity is the num of bit. 
	 * bit index is between [0,capacity-1]. 
	 * bit is saved in int[] array. from left to right. 
	 * if capacity%8 != 0, the rightest bit will not be used.
	 * 
	 * @param capacity
	 */
	public BitArray(int capacity){
		this.capacity = capacity;
		this.bit_length = capacity * BIT_NUM_PER_BUCKET;
		this.array = new int[getIndex(capacity - BIT_NUM_PER_BUCKET)+1];
	}
	
	public int getIndex(int position){
		if(position == 0){
			return 0;
		}
		if(position > this.capacity-1 || position < 0){
			throw new IllegalArgumentException("Error Argument:"+position);
		}
		int index = position % 8 == 0 ? position/8 - 1 : position/8;
		return index;
	}
	
	/**
	 * set bit in position to 1. 
	 * return original value in position.
	 * @param position
	 * @return
	 */
	public int setBitValue(int position){
		int index = getIndex(position);
		int end = 8-1 - position % 8;
		array[index] = array[index] | 1 << end;
		return array[index] >> end;			//return original num in positon.
	}
	
	/**
	 * get original value in position.
	 * @param position
	 * @return
	 */
	public int getBitValue(int position){
		int index = getIndex(position);
		int end = 8-1 - position % 8;
		return (array[index] & (1 << end)) >> end; 
	}

}
