package networkPKG;

import android.util.Log;

import model.ProductsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 * Class: ProductsClient
 * Purpose: This class acts as a singleton client for managing network requests related to products.
 *          It initializes Retrofit and defines methods for making network calls to fetch product data.
 */
public class ProductClient {
    /*
     * Field: TAG
     * Purpose: Used for logging purposes to identify log messages from ProductsClient.
     */
    public static final String TAG = "AllProductsActivity";

    /*
     * Field: BASE_URL
     * Purpose: The base URL for the API from which product data is fetched.
     */
    private static final String BASE_URL = "https://dummyjson.com/";

    /*
     * Field: client
     * Purpose: Static variable holding the single instance of ProductsClient (singleton pattern).
     */
    public static ProductClient client = null;

    /*
     * Field: retrofit
     * Purpose: Retrofit instance used to make network requests.
     */
    Retrofit retrofit;

    /*
     * Constructor: ProductsClient
     * Purpose: Initializes the ProductsClient object, setting up Retrofit with the base URL and JSON converter.
     *
     * @return: No return value (constructor).
     */
    private ProductClient() {
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()) // Adds a converter for JSON parsing
                .baseUrl(BASE_URL) // Sets the base URL for network requests
                .build(); // Builds the Retrofit instance

        /*
         * Retrofit.create() is a method provided by the Retrofit library used to create an implementation
         * of the API service interface. When you call Retrofit.create(),
         * it takes an interface (annotated with Retrofit annotations like @GET, @POST, etc.)
         * and generates the necessary code at runtime to handle network requests.
         */
        ProductService service = retrofit.create(ProductService.class);
    }

    /*
     * Method: getInstance
     * Purpose: Returns the single instance of ProductsClient, creating it if it does not exist.
     *
     * @return: ProductsClient: The singleton instance of ProductsClient.
     */
    public static ProductClient getInstance() {
        if (client == null) {
            client = new ProductClient(); // Create the instance if it doesn't exist
        }
        return client; // Return the existing or newly created instance
    }

    /*
     * Method: makeNetworkCall
     * Purpose: Initiates a network call to fetch product data from the server.
     *          It takes a callback interface to handle success and failure responses.
     *
     * @param NetworkCallBack: An interface for handling the results of the network call (success or failure).
     *
     * @return: No return value.
     */
    public void makeNetworkCall(networkCallBackInterface NetworkCallBack) {
        /*
         * Creates an instance of the ProductService interface.
         * Retrofit generates an implementation at runtime
         * that maps the interface methods (like getProducts()) to actual HTTP requests.
         */

        ProductService productService = retrofit.create(ProductService.class); // Create the service instance
        Call<ProductsResponse> call = productService.getProducts(); // Prepare the network call (HTTP Request) to fetch products

        /*
         * Enqueue the network call (HTTP Request) to execute it asynchronously.
         * The response will be handled in the provided Callback methods.
         */
        call.enqueue(new Callback<ProductsResponse>() {


            @Override
            public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
                if (response.isSuccessful()) { // Check if the response was successful
                    Log.i(TAG, "onResponse: CallBack" + response.raw() + response.body());
                    NetworkCallBack.onSuccessResult(response.body().getProducts()); // Pass the list of products to the callback
                }
            }

            @Override
            public void onFailure(Call<ProductsResponse> call, Throwable t) {
                Log.i(TAG, "onFailure: Callback");
                NetworkCallBack.onFailureResult(t.getMessage()); // Pass the error message to the callback
                t.printStackTrace(); // Print the stack trace for debugging
            }
        });
    }
}
