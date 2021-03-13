package com.sahana.sportyshoes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sahana.sportyshoes.model.Products;
import com.sahana.sportyshoes.model.Users;
@Repository
public class ProductDao {

	  @Autowired
	  JdbcTemplate jdbcTemplate;
	 
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Products> getAllProducts() {
		String sql = "select * from products";
		
		  List<Products> products = jdbcTemplate.query(sql, new RowMapper<Products>() {

				@Override
				public Products mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					Products product = new Products();
					product.setProductId(rs.getInt("productId"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getFloat("price"));
					return product;
				}
		    	
		    });

		    return products;
		
	}

	public Products getProductById(int productId) {
		String sql ="select * from products where productId='" + productId + "'";
		
		  List<Products> products = jdbcTemplate.query(sql, new RowMapper<Products>() {

				@Override
				public Products mapRow(ResultSet rs, int arg1) throws SQLException {
					// TODO Auto-generated method stub
					Products product = new Products();
					product.setProductId(rs.getInt("productId"));
					product.setName(rs.getString("name"));
					product.setPrice(rs.getFloat("price"));
					return product;
				}
		    	
		    });

		    return  products.size() > 0 ? products.get(0) : null;
	}

	public void deleteProduct(int productId) {
		 String query="delete from products where productId='"+productId+"' "; 
		 jdbcTemplate.update(query);  
	}

	public void addProduct(Products product) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String sql="insert into products (name, price) values (?,?)";
			jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					PreparedStatement ps = conn.prepareStatement(sql, new String[] { "productId" });
						ps.setString(1, product.getName());
						ps.setFloat(2, product.getPrice());
					return ps;
				}
			}, keyHolder);
	}
}
