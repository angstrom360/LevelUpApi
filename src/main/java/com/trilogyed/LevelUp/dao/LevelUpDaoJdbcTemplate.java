package com.trilogyed.LevelUp.dao;


import com.trilogyed.LevelUp.model.LevelUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LevelUpDaoJdbcTemplate implements LevelUpDao {

    //-------------------------Prepared Statements (CRUD QUERIES) ---------------------//

    private static final String INSERT_LEVELUP_SQL =
            "insert into level_up  (customer_id ,points ,member_date) values (?,?,?)";

    private static final String SELECT_LEVELUP_SQL =
            "select * from level_up  where level_up_id = ?";

    private static final String SELECT_ALL_LEVELUP_SQL =
            "select * from level_up ";

    private static final String UPDATE_LEVELUP_SQL =
            "update level_up  set customer_id = ?, points =?, member_date=? where level_up_id=?";

    private static final String DELETE_LEVELUP_SQL =
            "delete from level_up  where level_up_id = ?";

    private static final String SELECT_ALL_LEVELUP_BY_CUSTOMER_ID_SQL =
            "select * from level_up where customer_id = ?";

    //---------------------------------------------------------------------------------//

    //=============================== Implementing Methods from CommentDao Interface Class ===========================//


    @Autowired
    JdbcTemplate jdbcTemplate;

    public LevelUpDaoJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ------------------ Using the jdbcTemplate variable to run each prepared statement and map to the DTO ----------//

    @Override
    @Transactional
    public LevelUp createLevelUp(LevelUp levelUp){
        jdbcTemplate.update(INSERT_LEVELUP_SQL,
                levelUp.getCustomerId(),
                levelUp.getPoints(),
                levelUp.getMemberDate());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        levelUp.setId(id);

        return levelUp;
    }

    @Override
    public LevelUp getLevelUp(int id){

        try{
            return jdbcTemplate.queryForObject(SELECT_LEVELUP_SQL, this::mapRowToLevelUp,id);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<LevelUp> getAllLevelUps(){
        return jdbcTemplate.query(SELECT_ALL_LEVELUP_SQL, this::mapRowToLevelUp);
    }


    @Override
    public LevelUp getAllLevelUpByCustomerId(int id){
        return jdbcTemplate.queryForObject(SELECT_ALL_LEVELUP_BY_CUSTOMER_ID_SQL,this::mapRowToLevelUp,id);
    }

    @Override
    public void updateLevelUp(LevelUp levelUp){
        jdbcTemplate.update(UPDATE_LEVELUP_SQL,
                levelUp.getCustomerId(),
                levelUp.getPoints(),
                levelUp.getMemberDate(),
                levelUp.getId());
    }


    @Override
    public void deleteLevelUp(int id){

        jdbcTemplate.update(DELETE_LEVELUP_SQL,id);
    }

    private LevelUp mapRowToLevelUp(ResultSet rs, int rowNum) throws SQLException {

        LevelUp levelUp = new LevelUp();
        levelUp.setId(rs.getInt("level_up_id"));
        levelUp.setCustomerId(rs.getInt("customer_id"));
        levelUp.setPoints(rs.getInt("points"));
        levelUp.setMemberDate(rs.getDate("member_date").toLocalDate());
        return levelUp;

    }
    //----------------------------------------------------------------------------------------------------------------//
    //================================================================================================================//



}
