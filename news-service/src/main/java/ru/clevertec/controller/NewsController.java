package ru.clevertec.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.clevertec.dto.NewsCreateUpdateDto;
import ru.clevertec.dto.NewsFilter;
import ru.clevertec.dto.NewsReadDto;
import ru.clevertec.dto.PageResponse;
import ru.clevertec.service.impl.NewsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<NewsReadDto> findAll(NewsFilter filter, Pageable pageable) {
        Page<NewsReadDto> page = newsService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public NewsReadDto findById(@PathVariable("id") Long id) {
        return newsService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public NewsReadDto create(@RequestBody NewsCreateUpdateDto news) {
        return newsService.save(news);
    }

    @PutMapping("/{id}")
    public NewsReadDto update(@PathVariable("id") Long id, @RequestBody NewsCreateUpdateDto news) {
        return newsService.update(id, news).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!newsService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}