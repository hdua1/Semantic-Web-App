
package com.recipewelldone.parser.jsonReceipeIngredient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Binding {

    @SerializedName("hasIngredients")
    @Expose
    private HasIngredients hasIngredients;

    public HasIngredients getHasIngredients() {
        return hasIngredients;
    }

    public void setHasIngredients(HasIngredients hasIngredients) {
        this.hasIngredients = hasIngredients;
    }

}
