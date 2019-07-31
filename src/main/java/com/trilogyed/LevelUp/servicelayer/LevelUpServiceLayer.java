package com.trilogyed.LevelUp.servicelayer;


import com.trilogyed.LevelUp.dao.LevelUpDao;
import com.trilogyed.LevelUp.model.LevelUp;
import com.trilogyed.LevelUp.viewmodel.LevelUpViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RefreshScope
public class LevelUpServiceLayer {

    /**------------- Importing all objects and variables that will be used to build the ServiceLayer----------------- */
    @Autowired
    LevelUpDao levelUpDao;

    @Autowired
    public LevelUpServiceLayer(LevelUpDao levelUpDao) {
        this.levelUpDao = levelUpDao;
    }
    private LevelUpViewModel buildLevelUpViewModel (LevelUp levelUp){
        LevelUpViewModel levelUpViewModel = new LevelUpViewModel();
        levelUpViewModel.setId(levelUp.getId());
        levelUpViewModel.setCustomerId(levelUp.getCustomerId());
        levelUpViewModel.setPoints(levelUp.getPoints());
        levelUpViewModel.setMemberDate(levelUp.getMemberDate());

        return levelUpViewModel;
    }
    /**---------------------------------------------------------------------------------------------------------------*/

    /**The Business Logic for the service Layer is CRUD */
    @Transactional
    public LevelUpViewModel createLevelUp(LevelUpViewModel levelUpViewModel){
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(levelUpViewModel.getCustomerId());
        levelUp.setPoints(levelUpViewModel.getPoints());
        levelUp.setMemberDate(levelUpViewModel.getMemberDate());
        levelUp = levelUpDao.createLevelUp(levelUp);
        levelUpViewModel.setId(levelUp.getId());

        return levelUpViewModel;
    }

    public LevelUpViewModel getLevelUp(int id){
        LevelUp levelUp = levelUpDao.getLevelUp(id);
        if(levelUp == null)
            return null;
        else
            return buildLevelUpViewModel(levelUp);

    }

    public List<LevelUpViewModel> getAllLevelUp(){
        List<LevelUp> levelUpList = levelUpDao.getAllLevelUps();
        List<LevelUpViewModel> levelUpViewModelList = new ArrayList<>();

        for(LevelUp cList : levelUpList){
            LevelUpViewModel levelUpViewModel = buildLevelUpViewModel(cList);
            levelUpViewModelList.add(levelUpViewModel);
        }

        return levelUpViewModelList;
    }

    public void updateLevelUp(LevelUpViewModel levelUpViewModel){
        LevelUp levelUp = new LevelUp();
        levelUp.setId(levelUpViewModel.getId());
        levelUp.setCustomerId(levelUpViewModel.getCustomerId());
        levelUp.setPoints(levelUpViewModel.getPoints());
        levelUp.setMemberDate(levelUpViewModel.getMemberDate());
        levelUpDao.updateLevelUp(levelUp);

    }
    public void deleteLevelUp(int id){
        levelUpDao.deleteLevelUp(id);
    }

    public LevelUpViewModel getLevelUpByCustomerId(int id){
        LevelUp levelUpList = levelUpDao.getAllLevelUpByCustomerId(id);

        return buildLevelUpViewModel(levelUpList);
    }

    public String getPointsByCustomerId(int id){
        LevelUp customerAccount = levelUpDao.getAllLevelUpByCustomerId(id);


        return "Your Total points are: " + customerAccount.getPoints();
    }



}
