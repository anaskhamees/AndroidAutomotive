package model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
 * @Class: Products
 * @Purpose: Represents a single product entity fetched from the server (POJOs). Each product has a title, description, and thumbnail.
 */
@Entity(tableName = "Favproducts_table")
public class Product {

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @PrimaryKey
   private int id;

   /*
    * @Field: title
    * @Purpose: Holds the title of the product.
    */
   private String title;

   private double price;
   //private String brand;
   private double rating;
   /*
    * @Field: description
    * @Purpose: Holds the description of the product.
    */
   private String description;

   /*
    * @Field: thumbnail
    * @Purpose: Holds the thumbnail URL for the product image.
    */
   private String thumbnail;

   /*
    * @Constructor: Products
    * @Purpose: Initializes the `Products` object with the specified title, description, and thumbnail.
    *
    * @param thumbnail: String representing the URL of the product thumbnail.
    * @param description: String representing the description of the product.
    * @param title: String representing the title of the product.
    *
    * @return: No return value (constructor).
    */
   public Product(String thumbnail, String description, String title) {
      this.thumbnail = thumbnail;
      this.description = description;
      this.title = title;
   }

   /*
    * Default constructor
    * Purpose: Initializes a `Products` object without any initial data.
    */
   public Product() {
   }

   /*
    * Method: getTitle
    * Purpose: Retrieves the title of the product.
    *
    * @return: String representing the product title.
    */
   public double getPrice() { return price; }
   public void setPrice(double price) { this.price = price; }


   /*@
    * Method: setTitle
    * Purpose: Sets the title of the product.
    *
    * @param title: String representing the title to be set.
    *
    * @return: No return value.
    */
   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitle() { return title; }
   /*@
    * Method: getDescription
    * Purpose: Retrieves the description of the product.
    *
    * @return: String representing the product description.
    */
   public String getDescription() {
      return description;
   }

   /*@
    * Method: setDescription
    * Purpose: Sets the description of the product.
    *
    * @param description: String representing the description to be set.
    *
    * @return: No return value.
    */
   public void setDescription(String description) {
      this.description = description;
   }

   /*@
    * Method: getThumbnail
    * Purpose: Retrieves the thumbnail URL of the product.
    *
    * @return: String representing the product thumbnail URL.
    */
   public String getThumbnail() {
      return thumbnail;
   }

   /*@
    * Method: setThumbnail
    * Purpose: Sets the thumbnail URL of the product.
    *
    * @param thumbnail: String representing the thumbnail URL to be set.
    *
    * @return: No return value.
    */
   public void setThumbnail(String thumbnail) {
      this.thumbnail = thumbnail;
   }

   public double getRating() {
      return rating;
   }

   public void setRating(double rating) {
      this.rating = rating;
   }
}
