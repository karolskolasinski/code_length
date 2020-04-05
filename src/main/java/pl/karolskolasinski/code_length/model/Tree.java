package pl.karolskolasinski.code_length.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

@Getter
public class Tree {

    @SerializedName("path")
    private String path;

    @SerializedName("mode")
    private String mode;

    @SerializedName("type")
    private String type;

    @SerializedName("sha")
    private String sha;

    @SerializedName("size")
    private int size;

    @SerializedName("url")
    private String URL;

}
