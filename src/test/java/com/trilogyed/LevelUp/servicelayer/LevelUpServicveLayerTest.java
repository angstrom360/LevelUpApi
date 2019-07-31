package com.trilogyed.LevelUp.servicelayer;

import com.trilogyed.LevelUp.dao.LevelUpDao;
import com.trilogyed.LevelUp.dao.LevelUpDaoJdbcTemplate;
import com.trilogyed.LevelUp.model.LevelUp;
import com.trilogyed.LevelUp.viewmodel.LevelUpViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class LevelUpServicveLayerTest {

    @Autowired
    LevelUpDao levelUpDao;

    @Autowired
    LevelUpServiceLayer levelUpServiceLayer;

    private void setUpLevelUpDaoMock(){
        levelUpDao = mock(LevelUpDaoJdbcTemplate.class);

        LevelUp levelUp = new LevelUp();
        levelUp.setId(1);
        levelUp.setCustomerId(1);
        levelUp.setPoints(150);
        levelUp.setMemberDate(LocalDate.of(2020,12,12));

        LevelUp levelUp1 = new LevelUp();
        levelUp1.setCustomerId(1);
        levelUp1.setPoints(150);
        levelUp1.setMemberDate(LocalDate.of(2020,12,12));

        List<LevelUp> cList = new ArrayList<>();
        cList.add(levelUp);

        doReturn(levelUp).when(levelUpDao).createLevelUp(levelUp1);
        doReturn(levelUp).when(levelUpDao).getLevelUp(1);
        doReturn(cList).when(levelUpDao).getAllLevelUps();
        doReturn(levelUp).when(levelUpDao).getAllLevelUpByCustomerId(1);

    }

    @Before
    public void setUp()throws Exception{
        setUpLevelUpDaoMock();
        levelUpServiceLayer = new LevelUpServiceLayer(levelUpDao);
    }

    @Test
    public void createLevelUp(){
        LevelUpViewModel levelUp = new LevelUpViewModel();
        levelUp.setId(1);
        levelUp.setCustomerId(1);
        levelUp.setPoints(150);
        levelUp.setMemberDate(LocalDate.of(2020,12,12));
        levelUp = levelUpServiceLayer.createLevelUp(levelUp);

        LevelUpViewModel levelUp1 = new LevelUpViewModel();
        levelUp1.setId(1);
        levelUp1.setCustomerId(1);
        levelUp1.setPoints(150);
        levelUp1.setMemberDate(LocalDate.of(2020,12,12));

        LevelUpViewModel fromService = levelUpServiceLayer.createLevelUp(levelUp1);
        assertEquals(levelUp,fromService);

    }

    @Test
    public void getLevelUp(){
        LevelUpViewModel levelUp = new LevelUpViewModel();
        levelUp.setId(1);
        levelUp.setCustomerId(1);
        levelUp.setPoints(150);
        levelUp.setMemberDate(LocalDate.of(2020,12,12));
        levelUp = levelUpServiceLayer.createLevelUp(levelUp);
        LevelUpViewModel fromService = levelUpServiceLayer.getLevelUp(1);
        assertEquals(levelUp,fromService);
    }

    @Test
    public void getLevelUpByCustomerId(){
        LevelUpViewModel levelUp = new LevelUpViewModel();
        levelUp.setId(1);
        levelUp.setCustomerId(1);
        levelUp.setPoints(150);
        levelUp.setMemberDate(LocalDate.of(2020,12,12));
        levelUp = levelUpServiceLayer.createLevelUp(levelUp);

        LevelUpViewModel fromService = levelUpServiceLayer.getLevelUpByCustomerId(1);
        assertEquals(levelUp,fromService);
    }

    @Test
    public void getAllLevelUp(){
        LevelUpViewModel levelUp = new LevelUpViewModel();
        levelUp.setId(1);
        levelUp.setCustomerId(1);
        levelUp.setPoints(150);
        levelUp.setMemberDate(LocalDate.of(2020,12,12));
        levelUp = levelUpServiceLayer.createLevelUp(levelUp);

        List<LevelUpViewModel> fromService = levelUpServiceLayer.getAllLevelUp();
        assertEquals(1,fromService.size());
        assertEquals(levelUp,fromService.get(0));
    }

}
