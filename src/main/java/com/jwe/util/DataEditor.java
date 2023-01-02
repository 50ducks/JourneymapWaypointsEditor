package com.jwe.util;

import com.solidfire.gson.Gson;
import com.solidfire.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataEditor {

    public DataEditor(String src, Map<String, int[]> dataMap){
        this.src = src;
        this.dataMap = dataMap;
    }

    private boolean isAlign;
    private String src;
    private final Map<String, int[]> dataMap;

    private final Map<JsonSample, File> jsonMap = new HashMap<>();

    private final File dir = new File(src);
    private final Gson gson = new Gson();
    private final GsonBuilder gsonBuilder = new GsonBuilder();

    public void recolouringAndGridAligning (int[] colourData) throws IOException {
        int x, z;
        String data;
        JsonSample jsonSample;
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isFile()) {
                data = IOUtils.toString(file.toURI(), StandardCharsets.UTF_8);
                jsonSample = gson.fromJson(data, JsonSample.class);
                jsonSample.r = colourData[0];
                jsonSample.g = colourData[1];
                jsonSample.b = colourData[2];
                if (isAlign){
                    x = jsonSample.x;
                    z = jsonSample.z;
                    jsonSample.x = x > 0 ? x / 48 * 48 + 24 : x / 48 * 48 - 24;
                    jsonSample.z = z > 0 ? z / 48 * 48 + 24 : z / 48 * 48 - 24;
                }
                jsonMap.put(jsonSample, file);
            }
        }
    }

    public void saveData () throws IOException {
        gsonBuilder.setPrettyPrinting().serializeNulls();
        for (Map.Entry entry : jsonMap.entrySet()){
            if (entry.getValue() != null){
                FileUtils.writeStringToFile((File) entry.getValue(), gsonBuilder.create().toJson(entry.getKey()));
            }
        }
    }

    public void setAlign(boolean isAlign) {
        this.isAlign = isAlign;
    }
}
