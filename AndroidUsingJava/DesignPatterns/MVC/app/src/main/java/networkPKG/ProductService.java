package networkPKG;

import model.ProductsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/*
 * Interface: ProductService
 * Purpose: Defines the HTTP request to fetch the list of products from the server using Retrofit.
 * Retrofit automatically generates the necessary code to perform network requests based on this interface.
 */
public interface ProductService {

    /*
     * Method: getProducts
     * Purpose: This method is responsible for sending an HTTP GET request to the `/products` endpoint to retrieve product data.
     *
     * @GET: This annotation indicates that it is an HTTP GET request and the relative URL is "products".
     *
     * @return: Call<rootJSON>: This return type represents a Retrofit `Call` object, which will return a `rootJSON` object containing the list of products.
     */
    @GET("products")

    /**
     * Call<rootJSON> getProducts(); is a method that defines an API endpoint for retrieving product data via a GET request.
     * It returns a Call object that, when executed, will send a request to the specified endpoint and
     * expect a JSON response that can be parsed into a rootJSON object.
     *
     * Call<T>: This is a Retrofit interface representing a single HTTP request.
     * It is generic,meaning it can handle any type of response, specified by T.
     * In this case, T is rootJSON, which is expected to be the type of the data that the server responds with.
     * rootJSON: This is a custom class (usually a Java model class) that represents the structure of the JSON response you expect from the server.
     */
    Call<ProductsResponse> getProducts();  // Sends a GET request to retrieve product data.
}
