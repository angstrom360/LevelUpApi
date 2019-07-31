package com.trilogyed.LevelUp.controller;

import com.sun.jersey.api.NotFoundException;
import com.trilogyed.LevelUp.servicelayer.LevelUpServiceLayer;
import com.trilogyed.LevelUp.viewmodel.LevelUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LevelUpController {

    /**----------------------------------- REST CONTROLLER ----------------------------------------------------------*/
    @Autowired
    LevelUpServiceLayer service;

    @RequestMapping(value = "/levelUp", method = RequestMethod.POST)
    public LevelUpViewModel createLevelUps(@RequestBody @Valid LevelUpViewModel levelUpViewModel){
        return service.createLevelUp(levelUpViewModel);
    }

    @RequestMapping(value = "/levelUp/{id}", method = RequestMethod.GET)
    public LevelUpViewModel getLevelUps(@PathVariable int id){
        LevelUpViewModel levelUpViewModel = service.getLevelUp(id);
        if(levelUpViewModel == null)
            throw new NotFoundException("LevelUp could not be found for id "+id);
        return levelUpViewModel;

    }

    @RequestMapping(value = "/levelUp/all", method = RequestMethod.GET)
    public List<LevelUpViewModel> getAllLevelUp(){
        return service.getAllLevelUp();
    }


    @RequestMapping(value = "/levelUp/customer/{id}", method = RequestMethod.GET)
    public LevelUpViewModel getLevelUpByCustomerId(@PathVariable @Valid int id){
        return service.getLevelUpByCustomerId(id);
    }


    @RequestMapping(value = "/levelUp/{id}", method = RequestMethod.PUT)
    public void updateLevelUps(@RequestBody @Valid LevelUpViewModel levelUpViewModel, @PathVariable int id) {
        if(levelUpViewModel.getId() == 0)
            levelUpViewModel.setId(id);
        if(id != levelUpViewModel.getId()) {

            throw new IllegalArgumentException("Post ID on path must match the ID in the Post Object.");
        }
        service.updateLevelUp(levelUpViewModel);
    }

    @RequestMapping(value = "/levelUp/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable int id) {
        service.deleteLevelUp(id);

    }

/*    @RequestMapping(value = "/levelUp/points/customer/{id}",method = RequestMethod.GET)
    public String getPointsByCustomerId(@PathVariable @Valid int id){
        return service.getPointsByCustomerId(id);
    }*/

    /**----------------------------------------------------------------------------------------------------------------*/


}
