package com.excusaszenery.service;

import com.excusaszenery.model.Excuse;
import com.excusaszenery.repository.ExuseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ExcuseService {
    @Autowired
    private ExuseRepository excuseRepository;

    //Metodos importados directamente desde JPARepository

    public List<Excuse> getAllExcuses(){
        return excuseRepository.findAll();
    }
    public Optional<Excuse> getExcuseById(Long id){
        return excuseRepository.findById(id);
    }
    public Excuse saveExcuse (Excuse excuse){
        return excuseRepository.save(excuse);
    }
    public void deleteExcuse(Long id){
        excuseRepository.deleteById(id);
    }

    //Metodos creados por mi para encontrar la excusa por usuario o categoria

    public List<Excuse> getAllExcusesByUser (String username){
        return excuseRepository.findByUsername(username);
    }
    public  List<Excuse> gettAllExcusesByCategory(String categoryName){
        return excuseRepository.findByCategoryName(categoryName);
    }
}
