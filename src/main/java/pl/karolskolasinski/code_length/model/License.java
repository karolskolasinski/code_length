package pl.karolskolasinski.code_length.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class License {

    @SerializedName("key")
    private String key;


    @SerializedName("name")
    private String name;


    @SerializedName("spdx_id")
    private String spdxId;


    @SerializedName("url")
    private String URL;


    @SerializedName("node_id")
    private String nodeId;

}
