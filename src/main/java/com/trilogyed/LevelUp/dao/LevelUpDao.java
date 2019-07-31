package com.trilogyed.LevelUp.dao;

import com.trilogyed.LevelUp.model.LevelUp;

import java.util.List;

public interface LevelUpDao {

    //-------------- Basic CRUD Methods ------------//

    LevelUp createLevelUp(LevelUp levelUp);
    LevelUp getLevelUp(int id);
    List<LevelUp> getAllLevelUps();
    void updateLevelUp(LevelUp levelUp);
    void deleteLevelUp(int id);
    // -------------------------------------------//

    LevelUp getAllLevelUpByCustomerId(int id);

}
