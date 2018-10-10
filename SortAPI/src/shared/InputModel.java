package shared;

public class InputModel {
	String sortType;
	Integer[] array;
	private Boolean isIncreasing;
	
	/**
	 * @return the sortType
	 */
	public String getSortType() {
		return sortType;
	}
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType) {
		this.sortType = sortType;
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
	/**
	 * @return the isIncreasing
	 */
	public Boolean getIsIncreasing() {
		return isIncreasing;
	}
	/**
	 * @param isIncreasing the isIncreasing to set
	 */
	public void setIsIncreasing(Boolean isIncreasing) {
		this.isIncreasing = isIncreasing;
	}
}
