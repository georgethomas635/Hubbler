package com.geo.hubblerfoam.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.geo.hubblerfoam.model.InputFieldModel;

import java.io.IOException;
import java.util.List;

/**
 * Created by george
 * on 06/01/19.
 */
public class AppUtils {

    public static List<InputFieldModel> convertJsonStringToModel(String jsonString) {
        List<InputFieldModel> foamStructureJson = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            foamStructureJson = mapper.readValue(jsonString, new TypeReference<List<InputFieldModel>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foamStructureJson;
    }
}
