package com.example.WidgetApp.service;

import com.example.WidgetApp.model.CreateRequestBody;
import com.example.WidgetApp.model.UpdateRequestBody;
import com.example.WidgetApp.model.Widget;
import com.example.WidgetApp.repository.WidgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WidgetService {
    private final WidgetRepository widgetRepository;

    public synchronized Widget saveWidget(CreateRequestBody requestBody) {
        Widget widget = new Widget();
        widget.setX(requestBody.getX());
        widget.setY(requestBody.getY());
        widget.setZ(requestBody.getZ());
        widget.setWidth(requestBody.getWidth());
        widget.setHeight(requestBody.getHeight());
        widget.setId(getNewWidgetId());
        widget.setLastModificationDate(LocalDateTime.now());

        updateZIndexInAllWidgets(widget);
        widgetRepository.saveAndUpdate(widget);

        return widget;
    }

    public synchronized Widget updateWidget(UpdateRequestBody request) {
        Widget widget = widgetRepository.getById(request.getId());

        if (widget == null)
            throw new IllegalArgumentException("Widget not found");

        widget.setX(request.getX());
        widget.setY(request.getY());
        widget.setZ(request.getZ());
        widget.setWidth(request.getWidth());
        widget.setHeight(request.getHeight());
        widget.setLastModificationDate(LocalDateTime.now());

        updateZIndexInAllWidgets(widget);
        widgetRepository.saveAndUpdate(widget);
        return widget;
    }

    public synchronized Widget getWidget(Integer id) {
        return widgetRepository.getById(id);
    }

    public List<Widget> getWidgets() {
        return widgetRepository.getAll().stream()
                .sorted(Comparator.comparing(Widget::getZ))
                .toList();
    }

    public synchronized void deleteWidget(Integer id) {
        widgetRepository.delete(id);
    }

    private Integer getNewWidgetId() {
        Integer maxId = widgetRepository.getAll().stream()
                .map(Widget::getId)
                .max(Integer::compareTo)
                .orElse(0);

        return ++maxId;
    }

    private void updateZIndexInAllWidgets(Widget widget) {
        if (widget.getZ() == null) {
            Integer maxZ = widgetRepository.getAll().stream()
                    .map(Widget::getZ)
                    .max(Integer::compareTo)
                    .orElse(null);

            widget.setZ(maxZ == null ? 0 : maxZ+1);
        } else {
            for (Widget nextWidget : widgetRepository.getAll()) {
                if (!nextWidget.equals(widget)
                        && widget.getZ() <= nextWidget.getZ())
                    nextWidget.setZ(nextWidget.getZ() + 1);
            }
        }
    }

}
