package br.edu.ifsc.input;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Felipe
 */

public class Front {

    @SerializedName("front")
    @Expose
    private Front_ front;

    public Front_ getFront() {
        return front;
    }

    public void setFront(Front_ front) {
        this.front = front;
    }

    public static Front convertJsonInputToFrontObject(String pathOfFile) throws FileNotFoundException {
        return new Gson().fromJson(new FileReader(pathOfFile), Front.class);
    }
}
