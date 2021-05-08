package com.aj.news.api.api;

import com.aj.news.api.domain.News;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/")
public interface NewsApi {

    @PostMapping(value = "/addNews")
    void addNews(@RequestBody News news);

}
