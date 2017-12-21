package com.example.enesakbulut.fitness;

import android.net.Uri;

/**
 * Created by Enes on 14.12.2017.
 */

public class ListData {
    String number;
    String name;
    String uri;

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getUri(){
        return uri;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
