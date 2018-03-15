package com.example.demo.ch5.dao;

import java.util.Map;

import javax.validation.metadata.ValidateUnwrappedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.ch5.entity.User;

/*
 * JdbcTemplate的使用和加强版NamedParameterJdbcTemplate的使用Demo
 */
@Repository
public class UserDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public int queryUserNum(){
		return this.jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
	}
	public int queryUserNum1(){
		String sql="select count(*) from user where age=:age";
		MapSqlParameterSource np=new MapSqlParameterSource();
		np.addValue("age", 2);
		return this.namedParameterJdbcTemplate.queryForObject(sql,np,Integer.class);
	}
	/*
	 * jdbcTemplate允许查询结果返回一个Map,这里如果数据结果只有一条才会显示为map
	 * 这种方式非常不推荐使用，因为不利于项目后期的维护
	 */
	public Map getUserMap(){
		String sql="select * from user where age=?";
		return jdbcTemplate.queryForMap(sql,2);
	}
	public Map getUserMap1(){
		String sql="select * from user where age=?";
		return jdbcTemplate.queryForMap(sql,2);
	}
	/*
	 * 修改
	 */
	public void updateUser(){
		String sql="update user set name=? where age=?";
		jdbcTemplate.update(sql, "李四",2);
		String sql1="select * from user where age=?";
		System.out.println(jdbcTemplate.queryForMap(sql1,2));
	}
	/*
	 * 使用MapSqlParameterSource()来进行参数设置
	 */
	public void updateUser1(){
		String sql="update user set name=:name where age=:age";
		MapSqlParameterSource np=new MapSqlParameterSource();
		np.addValue("name", "王五");
		np.addValue("age", 2);
		namedParameterJdbcTemplate.update(sql,np);
		String sql1="select * from user where age=?";
		System.out.println(jdbcTemplate.queryForMap(sql1,2));
	}
	/*
	 * 使用JavaBean来进行参数设置()来进行参数设置
	 */
	public void updateUser2(){
		String sql="update user set name=:name where age=:age";
		User user=new User("赵六", "女", 2);
		SqlParameterSource sps=new BeanPropertySqlParameterSource(user);
		namedParameterJdbcTemplate.update(sql,sps);
		String sql1="select * from user where age=?";
		System.out.println(jdbcTemplate.queryForMap(sql1,2));
	}
	
}
