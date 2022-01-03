package com.example.WidgetApp.repository;

import com.example.WidgetApp.model.Widget;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataSource {
    private static Map<Integer, Widget> source = Collections.synchronizedMap(new HashMap<>());

    public void put(Widget widget) {
        source.put(widget.getId(), widget);
    }

    public Widget get(Integer id) {
        return source.get(id);
    }

    public List<Widget> getAll() {
        return source.values().stream().toList();
    }

    public void delete(Integer id) {
        source.remove(id);
    }

}
