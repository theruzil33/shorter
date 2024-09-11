package com.theruzil.shorter.repository;

import com.theruzil.shorter.entity.Url;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UrlRepository extends CrudRepository<Url, Long> {
    Url findById(long id);
    List<Url> findByFullUrl(String fullUrl);

    List<Url> findAll();
}
