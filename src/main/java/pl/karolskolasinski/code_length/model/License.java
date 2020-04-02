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
    private String spdx_id;

    @SerializedName("url")
    private String url;

    @SerializedName("node_id")
    private String node_id;
}
