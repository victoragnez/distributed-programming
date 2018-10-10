package shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * 
 * A web service to sort an array of integers.
 * The Client may send the array using a JSON file or inside the URI.
 * The sorted array is returned with the time (in microseconds) in a JSON file.
 * 
 * @author Artur Curinga and Victor Agnez
 *
 */

@Path("/api")
public interface Compute {
	
	/**
	 * Home page
	 * @return the HTML message.
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHelloHTML();
	
	/**
	 * Get method to sort an array inside the URI.
	 * Example: http://(IP_Address)/SortAPI/api/sortArray?sort=Bubble&list=3,1,2&isIncreasing=true
	 * @param sortType the desired sorting algorithm. Possible values: 
	 *  BinaryTree, Bubble, Counting, Heap, Insertion, 
		Merge, Quick, Radix, Random, Selection, Stooge.
	 * @param list the array of integers to sort
	 * @param isIncreasing a boolean to tell if the array should be sorted 
	 * either in increasing or decreasing order.
	 * @return the JSON file with the elapsed time to sort and the sorted array.
	 */
	@GET
	@Path("/sortArray")
	@Produces(MediaType.APPLICATION_JSON)
	public OutputModelInterface sortArray(@QueryParam("sortType") final String sortType,
			@QueryParam("list") final String list,
			@QueryParam("isIncreasing") final Boolean isIncreasing);
	
	/**
	 * Post method to sort an array in a JSON file.
	 * @param in the JSON file with values "sortType", "list" and "isIncreasing",
	 * which have the same meaning as in the function above.
	 * @return the JSON file with the elapsed time to sort and the sorted array.
	 */
	@POST
	@Path("/sortArrayJSON")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public OutputModelInterface sortArrayJSON(InputModelInterface in);
	
}
