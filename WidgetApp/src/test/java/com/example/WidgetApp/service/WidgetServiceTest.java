package com.example.WidgetApp.service;

import com.example.WidgetApp.model.CreateRequestBody;
import com.example.WidgetApp.model.UpdateRequestBody;
import com.example.WidgetApp.model.Widget;
import com.example.WidgetApp.repository.WidgetRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class WidgetServiceTest {

    @Mock
    private WidgetRepositoryImpl widgetRepository;

    @InjectMocks
    private WidgetService widgetService;

    @Test
    void saveWidgetShouldBeSuccess() {
        CreateRequestBody requestBody = new CreateRequestBody(1, 2, 2, 3, 4);
        Widget widget = widgetService.saveWidget(requestBody);

        assertThat(widget.getId()).isEqualTo(1);
        assertThat(widget.getZ()).isEqualTo(requestBody.getZ());
        assertThat(widget.getX()).isEqualTo(requestBody.getX());
        assertThat(widget.getY()).isEqualTo(requestBody.getY());
        assertThat(widget.getWidth()).isEqualTo(requestBody.getWidth());
        assertThat(widget.getHeight()).isEqualTo(requestBody.getHeight());
        assertThat(widget.getLastModificationDate()).isNotNull();
    }

    @Test
    void saveWidgetInWidgetListShouldBeSuccess() {
        List<Widget> widgets = List.of(
                new Widget(1, 1, 1, 0, 1, 1, LocalDateTime.now()),
                new Widget(2, 1, 1, 1, 1, 1, LocalDateTime.now()),
                new Widget(3, 1, 1, 2, 1, 1, LocalDateTime.now()));
        lenient().when(widgetRepository.getAll()).thenReturn(widgets);

        CreateRequestBody requestBody = new CreateRequestBody(1, 2, 1, 3, 4);
        Widget widget = widgetService.saveWidget(requestBody);

        assertThat(widget.getId()).isEqualTo(4);
        assertThat(widget.getZ()).isEqualTo(requestBody.getZ());
    }

    @Test
    void saveWidgetWithoutZInWidgetListShouldBeSuccess() {
        List<Widget> widgets = List.of(
                new Widget(1, 1, 1, 0, 1, 1, LocalDateTime.now()),
                new Widget(2, 1, 1, 1, 1, 1, LocalDateTime.now()),
                new Widget(3, 1, 1, 2, 1, 1, LocalDateTime.now()));
        lenient().when(widgetRepository.getAll()).thenReturn(widgets);

        CreateRequestBody requestBody = new CreateRequestBody(1, 2, null, 3, 4);
        Widget widget = widgetService.saveWidget(requestBody);

        assertThat(widget.getId()).isEqualTo(4);
        assertThat(widget.getZ()).isEqualTo(3);
    }

    @Test
    void saveWidgetWithNullZShouldBeSuccess() {
        CreateRequestBody requestBody = new CreateRequestBody(1, 2, null, 3, 4);
        Widget widget = widgetService.saveWidget(requestBody);

        assertThat(widget.getId()).isEqualTo(1);
        assertThat(widget.getZ()).isEqualTo(0);
        assertThat(widget.getX()).isEqualTo(requestBody.getX());
        assertThat(widget.getY()).isEqualTo(requestBody.getY());
        assertThat(widget.getWidth()).isEqualTo(requestBody.getWidth());
        assertThat(widget.getHeight()).isEqualTo(requestBody.getHeight());
    }

    @Test
    void updateWidgetSuccess() {
        lenient().when(widgetRepository.getById(1)).thenReturn(new Widget(1, 1, 1, 0, 1, 1, LocalDateTime.now()));

        UpdateRequestBody requestBody = new UpdateRequestBody(1, 2, 3, 4, 5, 6);
        Widget widget = widgetService.updateWidget(requestBody);

        assertThat(widget.getId()).isEqualTo(requestBody.getId());
        assertThat(widget.getZ()).isEqualTo(requestBody.getZ());
        assertThat(widget.getX()).isEqualTo(requestBody.getX());
        assertThat(widget.getY()).isEqualTo(requestBody.getY());
        assertThat(widget.getWidth()).isEqualTo(requestBody.getWidth());
        assertThat(widget.getHeight()).isEqualTo(requestBody.getHeight());
    }

    @Test
    void updateWidgetFail() {
        UpdateRequestBody requestBody = new UpdateRequestBody(1, 2, 3, 4, 5, 6);

        assertThrows(IllegalArgumentException.class, () -> widgetService.updateWidget(requestBody));
    }
}