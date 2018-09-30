package com.mybatis.dao.fruit;

import java.util.List;

import com.mybatis.model.fruit.Fruit;

public interface IFruit {
	public Fruit findFruitById(String id);  
    public void addFruit(Fruit fruit);  
    public void updateFruit(Fruit fruit);  
    public void deleteFruit(String id); 
    public List<Fruit> selectLike(String id); 

}
