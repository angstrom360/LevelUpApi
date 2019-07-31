package com.trilogyed.LevelUp.dao;

import com.trilogyed.LevelUp.model.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoTest {

    @Autowired
    LevelUpDao levelUpDao;
    @Before
    public void SetUp() throws Exception{

        List<LevelUp> cList = levelUpDao.getAllLevelUps();

        for(LevelUp c : cList){
            levelUpDao.deleteLevelUp(c.getId());
        }

    }

    @Test
    public void addGetDeleteLevelUp(){
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(150);
        levelUp.setMemberDate(LocalDate.of(2020,12,12));
        levelUp = levelUpDao.createLevelUp(levelUp);

        LevelUp levelUp1 = levelUpDao.getLevelUp(levelUp.getId());

        assertEquals(levelUp1,levelUp);

        levelUpDao.deleteLevelUp(levelUp.getId());

        levelUp1 = levelUpDao.getLevelUp(levelUp.getId());

        assertNull(levelUp1);

    }

    @Test
    public void getAllLevelUps(){

        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(150);
        levelUp.setMemberDate(LocalDate.of(2020,12,12));
        levelUp = levelUpDao.createLevelUp(levelUp);

        levelUp =new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(150);
        levelUp.setMemberDate(LocalDate.of(2020,12,12));
        levelUpDao.createLevelUp(levelUp);

        List<LevelUp> cList = levelUpDao.getAllLevelUps();

        assertEquals(cList.size(),2);
    }

    @Test
    public void updateLevelUp(){
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(150);
        levelUp.setMemberDate(LocalDate.of(2020,12,12));
        levelUp = levelUpDao.createLevelUp(levelUp);

        levelUp.setMemberDate(LocalDate.of(1992,12,10));
        levelUpDao.updateLevelUp(levelUp);

        LevelUp levelUp1 = levelUpDao.getLevelUp(levelUp.getId());

        assertEquals(levelUp1,levelUp);
    }


}
