package model;

import java.util.List;

/*
 * Class: rootJSON
 * Purpose: This class represents the root JSON structure returned by the server when fetching the products.
 *          It contains a list of `Products` objects, which is the main data structure for the response.
 *
 * The rootJSON class is used to encapsulate the entire response from the server,
 * including the list of products. When you make a network call using Retrofit,
 * the server response is mapped to an instance of rootJSON, which contains the list of products.
 */
public class ProductsResponse {

    /*
     * Field: products
     * Purpose: This is the list of `Products` objects that represents the product data received from the server.
     */
    private List<Product> products;

    /*
     * Constructor: rootJSON
     * Purpose: Initializes the `rootJSON` object with a list of products.
     *
     * @param products: List of `Products` objects representing the data fetched from the server.
     *
     * @return: No return value (constructor).
     */
    public ProductsResponse(List<Product> products) {
        this.products = products;
    }

    /*
     * Method: getProducts
     * Purpose: Retrieves the list of `Products` objects from the JSON response.
     *
     * @return: List<Products>: Returns the list of products fetched from the server.
     */
    public List<Product> getProducts() {
        return products;
    }

    /*
     * Method: setProducts
     * Purpose: Sets the list of `Products` objects in the `rootJSON`.
     *
     * @param products: List of `Products` objects representing the product data to be set.
     *
     * @return: No return value.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
