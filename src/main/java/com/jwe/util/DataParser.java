package com.jwe.util;

import com.solidfire.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataParser {
    Gson gson = new Gson();
    JsonSample jsonSample = new JsonSample();
    DataLoader dataLoader;

    private final Map<String, int[]> dataMap = new HashMap<>();
    private String temp = " ";

    public Map<String, int[]> getParsedData() throws IOException {
        dataLoader  = new DataLoader("C:\\Users\\sgrig\\AppData\\Roaming\\.minecraft\\versions\\IIC\\journeymap\\data\\sp\\Новый мир\\waypoints");
        for (String jsonString : dataLoader.getLoadedData()) {
            jsonSample = gson.fromJson(jsonString, JsonSample.class);
            if (jsonSample != null && !Objects.equals(jsonSample.name, temp)) {
                dataMap.put(jsonSample.name, new int[]{jsonSample.r, jsonSample.g, jsonSample.b});
                temp = jsonSample.name;
            }
        }
        return dataMap;
    }
}
