#include <cpprest/http_client.h>
#include <cpprest/filestream.h>

using namespace utility;					// Common utilities like string conversions
using namespace web;						// Common features like URIs.
using namespace web::http;				  // Common HTTP functionality
using namespace web::http::client;		  // HTTP client features
using namespace concurrency::streams;	   // Asynchronous streams

int main(){
	
	auto fileStream = std::make_shared<ostream>();

	 // Open stream to output file.
	pplx::task<void> requestTask = fstream::open_ostream(U("results.html")).then([=](ostream outFile)
	{
		*fileStream = outFile;

		// Create http_client to send the request.
		http_client client(U("http://localhost:8080/SortAPI/rest/hello"));

		// Build request URI and start the request.
		
		//Query pelo URL com retorno em texto:
		/*
		uri_builder builder(U("/sortArray"));
		builder.append_query(U("sort"), U("Radix"));
		builder.append_query(U("list"), U("2,1,-10,0"));
		
		//std::cout << builder.to_string() << std::endl;
		return client.request(methods::GET, builder.to_string()); */
		
		//Query pelo URL com retorno em JSON:
		/*
		uri_builder builder(U("/sortArrayJSON"));
		builder.append_query(U("sort"), U("Radix"));
		builder.append_query(U("list"), U("2,1,-10,0"));
		
		//std::cout << builder.to_string() << std::endl;
		return client.request(methods::GET, builder.to_string()); */
		
		//Query pelo JSON:
		
		try {
			uri_builder builder(U("/sortArrayJSONPOST"));
			std::string textFile = "";
			string_t fileName("test.json");
		
			ifstream_t	  f(fileName);							  // filestream of working file
			stringstream_t  s;										  // string stream for holding JSON read from file
			json::value	 v;										  // JSON read from input file
			
			if (f) {													
				s << f.rdbuf();										 // stream results of reading from file stream into string stream
				f.close();											  // close the filestream

				v.parse(s);											 // parse the resultant string stream.
			}
			
			//return client.request(methods::POST, builder.to_string(), v); //tentativa 1
			const std::string tmp = s.str();
			
			return client.request(methods::POST, builder.to_string(), 
				utility::conversions::to_utf8string(tmp), _XPLATSTR("application/json")); //tentativa 2
		}
		catch (web::json::json_exception excep) {
			std::cout << "ERROR Parsing JSON: ";
			std::cout << excep.what();
		}
		
	})

	// Handle response headers arriving.
	.then([=](http_response response)
	{
		printf("Received response status code:%u\n", response.status_code());

		// Write response body into the file.
		return response.body().read_to_end(fileStream->streambuf());
	})

	// Close the file stream.
	.then([=](size_t)
	{
		return fileStream->close();
	});

	// Wait for all the outstanding I/O to complete and handle any exceptions
	try
	{
		requestTask.wait();
	}
	catch (const std::exception &e)
	{
		printf("Error exception:%s\n", e.what());
	}

	return 0;
}
