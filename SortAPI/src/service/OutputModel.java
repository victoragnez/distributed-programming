package service;

import shared.OutputModelInterface;

public class OutputModel implements OutputModelInterface {
	Long time;
	Integer[] array;
	
	/**
	 * @return the time
	 */
	public Long getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(Long time) {
		this.time = time;
	}
	/**
	 * @return the array
	 */
	public Integer[] getArray() {
		return array;
	}
	/**
	 * @param array the array to set
	 */
	public void setArray(Integer[] array) {
		this.array = array;
	}
	
}
