package com.arthur.delicious.bloomfilter;

import javax.swing.text.Position.Bias;

public class BloomFilter {
	
	private int capacity;
	private BitArray bitArray;
	private int total_counter = 0;
	private int set_counter = 0;
	
	public BloomFilter(int bit_capacity){	//参数应该改成data_set_num，然后换算成bit_capacity.
		this.capacity = bit_capacity;
		this.bitArray = new BitArray(capacity); 
		
		setBitCapacity();
		setHashFunctionNumber();
	}

	public void setBitCapacity(){
		
	}
	
	public void setHashFunctionNumber(){
		
	}
	
	/**
	 * Add the string into bloom filter.
	 * Return true if already in; false if not.
	 * @param string
	 * @return
	 */
	public boolean addString(String string) {
		// TODO Auto-generated method stub
		total_counter++;
		GeneralHashFunctionLibrary hm = new GeneralHashFunctionLibrary();
		int position1 = (int)(Math.abs(hm.APHash(string)) % capacity);
		int position2 = (int)(Math.abs(hm.BKDRHash(string)) % capacity);
		int position3 = (int)(Math.abs(hm.BPHash(string)) % capacity);
		if((bitArray.getBitValue(position1) & bitArray.getBitValue(position2) & 
				bitArray.getBitValue(position3)) == 1){
			System.out.println(">>String "+string+" Already in Bloom Filter.");
			return true;
		}else{
			System.out.println("Add String "+string+" Into Bloom Filter.");
			set_counter ++;
			bitArray.setBitValue(position1);
			bitArray.setBitValue(position2);
			bitArray.setBitValue(position3);
			return false;
		}
	}
	
	public String getCounterResult(){
		return "Bloom Filter Total Counter:"+total_counter+". Set Counter:"+set_counter;
	}

}
