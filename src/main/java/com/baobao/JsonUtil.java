package com.baobao;

import com.google.gson.Gson;

public class JsonUtil {


    public static String toJson(Object o){
        return new Gson().toJson(o);
    }

}
