
package com.recipewelldone.parser.jsonRestuarantInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsLocatedAtPhysical {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
