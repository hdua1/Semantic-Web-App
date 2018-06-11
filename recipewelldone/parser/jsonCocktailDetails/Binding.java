
package com.recipewelldone.parser.jsonCocktailDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Binding {

    @SerializedName("hasDrinkName")
    @Expose
    private HasDrinkName hasDrinkName;
    @SerializedName("belongsToDrinkCategory")
    @Expose
    private BelongsToDrinkCategory belongsToDrinkCategory;
    @SerializedName("drinkHasImage")
    @Expose
    private DrinkHasImage drinkHasImage;
    @SerializedName("isPreparedAs")
    @Expose
    private IsPreparedAs isPreparedAs;

    public HasDrinkName getHasDrinkName() {
        return hasDrinkName;
    }

    public void setHasDrinkName(HasDrinkName hasDrinkName) {
        this.hasDrinkName = hasDrinkName;
    }

    public BelongsToDrinkCategory getBelongsToDrinkCategory() {
        return belongsToDrinkCategory;
    }

    public void setBelongsToDrinkCategory(BelongsToDrinkCategory belongsToDrinkCategory) {
        this.belongsToDrinkCategory = belongsToDrinkCategory;
    }

    public DrinkHasImage getDrinkHasImage() {
        return drinkHasImage;
    }

    public void setDrinkHasImage(DrinkHasImage drinkHasImage) {
        this.drinkHasImage = drinkHasImage;
    }

    public IsPreparedAs getIsPreparedAs() {
        return isPreparedAs;
    }

    public void setIsPreparedAs(IsPreparedAs isPreparedAs) {
        this.isPreparedAs = isPreparedAs;
    }

}
