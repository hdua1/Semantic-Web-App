
package com.recipewelldone.parser.jsonRestuarantInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Binding {

    @SerializedName("hasRestaurantName")
    @Expose
    private HasRestaurantName hasRestaurantName;
    @SerializedName("isLocatedAtWeb")
    @Expose
    private IsLocatedAtWeb isLocatedAtWeb;
    @SerializedName("isLocatedAtPhysical")
    @Expose
    private IsLocatedAtPhysical isLocatedAtPhysical;
    @SerializedName("isLocatedInCity")
    @Expose
    private IsLocatedInCity isLocatedInCity;
    @SerializedName("isLocatedAt")
    @Expose
    private IsLocatedAt isLocatedAt;
    @SerializedName("servesCuisine")
    @Expose
    private ServesCuisine servesCuisine;
    @SerializedName("costRated")
    @Expose
    private CostRated costRated;
    @SerializedName("hasRating")
    @Expose
    private HasRating hasRating;

    public HasRestaurantName getHasRestaurantName() {
        return hasRestaurantName;
    }

    public void setHasRestaurantName(HasRestaurantName hasRestaurantName) {
        this.hasRestaurantName = hasRestaurantName;
    }

    public IsLocatedAtWeb getIsLocatedAtWeb() {
        return isLocatedAtWeb;
    }

    public void setIsLocatedAtWeb(IsLocatedAtWeb isLocatedAtWeb) {
        this.isLocatedAtWeb = isLocatedAtWeb;
    }

    public IsLocatedAtPhysical getIsLocatedAtPhysical() {
        return isLocatedAtPhysical;
    }

    public void setIsLocatedAtPhysical(IsLocatedAtPhysical isLocatedAtPhysical) {
        this.isLocatedAtPhysical = isLocatedAtPhysical;
    }

    public IsLocatedInCity getIsLocatedInCity() {
        return isLocatedInCity;
    }

    public void setIsLocatedInCity(IsLocatedInCity isLocatedInCity) {
        this.isLocatedInCity = isLocatedInCity;
    }

    public IsLocatedAt getIsLocatedAt() {
        return isLocatedAt;
    }

    public void setIsLocatedAt(IsLocatedAt isLocatedAt) {
        this.isLocatedAt = isLocatedAt;
    }

    public ServesCuisine getServesCuisine() {
        return servesCuisine;
    }

    public void setServesCuisine(ServesCuisine servesCuisine) {
        this.servesCuisine = servesCuisine;
    }

    public CostRated getCostRated() {
        return costRated;
    }

    public void setCostRated(CostRated costRated) {
        this.costRated = costRated;
    }

    public HasRating getHasRating() {
        return hasRating;
    }

    public void setHasRating(HasRating hasRating) {
        this.hasRating = hasRating;
    }

}
