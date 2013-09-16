package com.arthur.delicious.bloomfilter;

import javax.swing.text.Position.Bias;

public class BloomFilter {
	
	private int capacity;
	private BitArray bitArray;
	private int total_counter = 0;
	private int set_counter = 0;
	private int HASH_FUNCTION_NUM;
	private int HASH_FUNCTION_NUM_MAX = 11;
	private int[] hashPostion;
	
	public BloomFilter(int bit_capacity){	//参数应该改成data_set_num，然后换算成bit_capacity.
		this.capacity = bit_capacity;
		this.bitArray = new BitArray(capacity); 
		HASH_FUNCTION_NUM = HASH_FUNCTION_NUM_MAX;
		
		setBitCapacity();
		setHashFunctionNumber();
	}

	public void setBitCapacity(){
		
	}
	
	public void setHashFunctionNumber(){
		hashPostion = new int[HASH_FUNCTION_NUM];
	}
	
	/**
	 * Add the string into bloom filter.
	 * Return false if already in; return true if new to bloomfilter.
	 * @param string
	 * @return
	 */
	public boolean addString(String string) {
		// TODO Auto-generated method stub
		total_counter++;
		GeneralHashFunctionLibrary hm = new GeneralHashFunctionLibrary();

		hashPostion[0] = (int)(Math.abs(hm.SDBMHash(string)) % capacity);
		hashPostion[1] = (int)(Math.abs(hm.APHash(string)) % capacity);
		hashPostion[2] = (int)(Math.abs(hm.BKDRHash(string)) % capacity);
		hashPostion[3] = (int)(Math.abs(hm.BPHash(string)) % capacity);
		hashPostion[4] = (int)(Math.abs(hm.DEKHash(string)) % capacity);
		hashPostion[5] = (int)(Math.abs(hm.DJBHash(string)) % capacity);
		hashPostion[6] = (int)(Math.abs(hm.ELFHash(string)) % capacity);
		hashPostion[7] = (int)(Math.abs(hm.FNVHash(string)) % capacity);
		hashPostion[8] = (int)(Math.abs(hm.JSHash(string)) % capacity);
		hashPostion[9] = (int)(Math.abs(hm.PJWHash(string)) % capacity);
		hashPostion[10] = (int)(Math.abs(hm.RSHash(string)) % capacity);
		
		boolean alreadyIn = true;
		for(int i=0;i<HASH_FUNCTION_NUM;i++){
			if(bitArray.getBitValue(hashPostion[i]) == 0){
				bitArray.setBitValue(hashPostion[i]);
				alreadyIn = false;
			}
		}
		
		if(alreadyIn){
			System.out.println(">>Failed! String "+string+" Already in Bloom Filter. ");
			return false;
		}else{
			set_counter ++;
			System.out.println("Success! Add String "+string+" Into Bloom Filter.");
			return true;
		}
		
	}
	
	public String getCounterResult(){
		return "Bloom Filter Total Counter:"+total_counter+". Set Counter:"+set_counter;
	}

}
