
package com.recipewelldone.parser.jsonCocktailIngrdient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Binding {

    @SerializedName("isPreparedWith")
    @Expose
    private IsPreparedWith isPreparedWith;

    public IsPreparedWith getIsPreparedWith() {
        return isPreparedWith;
    }

    public void setIsPreparedWith(IsPreparedWith isPreparedWith) {
        this.isPreparedWith = isPreparedWith;
    }

}
