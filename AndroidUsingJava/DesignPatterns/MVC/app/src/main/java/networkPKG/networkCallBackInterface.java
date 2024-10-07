package networkPKG;

import java.util.List;

import model.Product;

/*
 * @Interface: networkCallBackInterface
 * @Purpose: Acts as a contract between the `ProductsClient` (API Client) and `MainActivity` for handling success and failure callbacks from the network call.
 */
public interface networkCallBackInterface {

    /*
     * @Method: onSuccessResult
     * @Purpose: This method is triggered when the network call successfully fetches the product data.
     *
     * @param products: List of Product objects representing the product data fetched from the server.
     *
     * @return: No return value.
     */
    public void onSuccessResult(List<Product> products);

    /*@
     * Method: onFailureResult
     * Purpose: This method is triggered when the network call fails.
     *
     * @param errorMsg: String representing the error message received from the network call failure.
     *
     * @return: No return value.
     */
    public void onFailureResult(String errorMsg);
}
