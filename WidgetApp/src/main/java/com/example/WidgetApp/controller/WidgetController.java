package com.example.WidgetApp.controller;

import com.example.WidgetApp.model.CreateRequestBody;
import com.example.WidgetApp.model.UpdateRequestBody;
import com.example.WidgetApp.model.Widget;
import com.example.WidgetApp.service.WidgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/widgets")
public class WidgetController {
    private final WidgetService widgetService;

    @PostMapping
    public Widget createWidget(@RequestBody @Valid CreateRequestBody requestBody) {
        return widgetService.saveWidget(requestBody);
    }

    @PutMapping
    public Widget updateWidget(@RequestBody @Valid UpdateRequestBody requestBody) {
        return widgetService.updateWidget(requestBody);
    }

    @GetMapping("/{id}")
    public Widget getWidgetById(@PathVariable Integer id) {
        return widgetService.getWidget(id);
    }

    @GetMapping
    public List<Widget> getWidgets() {
        return widgetService.getWidgets();
    }

    @DeleteMapping("/{id}")
    public void removeWidgetById(@PathVariable Integer id) {
        widgetService.deleteWidget(id);
    }
}
