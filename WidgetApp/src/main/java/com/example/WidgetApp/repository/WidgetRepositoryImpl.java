package com.example.WidgetApp.repository;

import com.example.WidgetApp.model.Widget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WidgetRepositoryImpl implements WidgetRepository {

    private final DataSource dataSource;

    @Override
    public void saveAndUpdate(Widget widget) {
        dataSource.put(widget);
    }

    @Override
    public Widget getById(Integer id) {
        return dataSource.get(id);
    }

    @Override
    public List<Widget> getAll() {
        return dataSource.getAll();
    }

    @Override
    public void delete(Integer id) {
        dataSource.delete(id);
    }
}
