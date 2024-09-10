package com.theruzil.shorter.repository;

import com.theruzil.shorter.entity.Url;
import org.springframework.data.repository.CrudRepository;

public interface UrlRepository extends CrudRepository<Url, Long> {
    Url findById(long id);
}
