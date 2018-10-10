package shared;

/**
 * Represents the output JSON file.
 * @author Artur Curinga and Victor Agnez
 *
 */
public interface OutputModelInterface {
	/**
	 * @return the time
	 */
	public Long getTime();
	/**
	 * @param time the time to set
	 */
	public void setTime(Long time);
	/**
	 * @return the array
	 */
	public Integer[] getArray();
	/**
	 * @param array the array to set
	 */
	public void setArray(Integer[] array);
}
