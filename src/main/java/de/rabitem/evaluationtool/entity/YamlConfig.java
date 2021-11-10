package de.rabitem.evaluationtool.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class YamlConfig<T> {

    private final String configPath;
    private final Class<T> typeParameterClass;
    private T config;

    public YamlConfig(String configPath, Class<T> typeParameterClass) {
        this.configPath = configPath;
        this.typeParameterClass = typeParameterClass;
    }

    public T getConfig() {
        if (Objects.nonNull(config))
            return config;
        // Instantiating a new ObjectMapper as a YAMLFactory
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        try {
            return om.readValue(new File(this.configPath), typeParameterClass);
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Invalid Config!");
        }
        return null;

    }
}
