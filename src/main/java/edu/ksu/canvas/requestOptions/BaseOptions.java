package edu.ksu.canvas.requestOptions;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class BaseOptions {

    protected Map<String, List<String>> optionsMap = new HashMap<>();

    public Map<String, List<String>> getOptionsMap() {
        return optionsMap;
    }

    public JsonObject getJson() {
        JsonObject jsonObject = new JsonObject();
        optionsMap.entrySet().stream().forEach(stringListEntry -> {
            List<String> value = stringListEntry.getValue();
            if (value.size() == 1) {
                jsonObject.addProperty(stringListEntry.getKey(), value.get(0));
            }
        });
        return jsonObject;
    }

    /**
     * Add a list of enums to the options map
     * @param key The key for the options map (ex: "include[]")
     * @param list A list of enums
     */
    protected void addEnumList(String key, List<? extends Enum> list) {
        optionsMap.put(key, list.stream().map(i -> i.toString()).collect(Collectors.toList()));
    }

    protected void addSingleItem(String key, String value) {
        optionsMap.put(key, Collections.singletonList(value));
    }

    protected void addNumberList(String key, List<? extends Number> list) {
        optionsMap.put(key, list.stream().map(i->i.toString()).collect(Collectors.toList()));
    }

    protected void addStringList(String key, List<String> list) {
        optionsMap.put(key, new ArrayList<String>(list));
    }
}
