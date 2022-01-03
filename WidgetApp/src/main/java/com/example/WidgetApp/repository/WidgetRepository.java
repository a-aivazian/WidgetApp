package com.example.WidgetApp.repository;

import com.example.WidgetApp.model.Widget;

import java.util.List;

public interface WidgetRepository {
    void saveAndUpdate(Widget widget);
    Widget getById(Integer id);
    List<Widget> getAll();
    void delete(Integer id);
}
