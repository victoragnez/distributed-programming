package shared;

/**
 * Represents the input JSON file.
 * @author Artur Curinga and Victor Agnez
 *
 */
public interface InputModelInterface {
	/**
	 * @return the sortType
	 */
	public String getSortType();
	/**
	 * @param sortType the sortType to set
	 */
	public void setSortType(String sortType);
	/**
	 * @return the array
	 */
	public Integer[] getArray();
	/**
	 * @param array the array to set
	 */
	public void setArray(Integer[] array);
	/**
	 * @return the isIncreasing
	 */
	public Boolean getIsIncreasing();
	/**
	 * @param isIncreasing the isIncreasing to set
	 */
	public void setIsIncreasing(Boolean isIncreasing);
}
