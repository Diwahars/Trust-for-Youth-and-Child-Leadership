package com.nkana.app.network.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chokkar on 11/29/2015.
 */
public class UtilizationResponse {

    @SerializedName("utilization_keys")
    private List<String> utilizationKeys = new ArrayList<String>();


    @SerializedName("utilization_values")
    private List<Integer> utilizationValues = new ArrayList<Integer>();

    /**
     *
     * @return
     * The utilizationKeys
     */
    public List<String> getUtilizationKeys() {
        return utilizationKeys;
    }

    /**
     *
     * @param utilizationKeys
     * The utilization_keys
     */
    public void setUtilizationKeys(List<String> utilizationKeys) {
        this.utilizationKeys = utilizationKeys;
    }

    /**
     *
     * @return
     * The utilizationValues
     */
    public List<Integer> getUtilizationValues() {
        return utilizationValues;
    }

    /**
     *
     * @param utilizationValues
     * The utilization_values
     */
    public void setUtilizationValues(List<Integer> utilizationValues) {
        this.utilizationValues = utilizationValues;
    }
}
