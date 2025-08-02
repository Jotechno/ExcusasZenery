package com.excusaszenery.service;

import  com.excusaszenery.model.Excuse;
import com.excusaszenery.dto.ExcuseRequestDto;

import java.util.List;
import java.util.Optional;

public interface ExcuseService {
    Excuse createExcuse(ExcuseRequestDto dto);
    Excuse updateExcuse(Integer id, Excuse excuse);
    boolean deleteExcuse(Integer id);
    Optional<Excuse> getExcuseById(Integer id);
    List<Excuse> getAllExcuses();
    boolean toggleExcuseStatus(Integer id);
}
