#pragma once
#include <string>


struct InventoryItem
{
	std::string name; // display name for the Item
	unsigned int SKU; // this is the stock keeping Unit that is used internally
	std::string description; // this describes the item, gives context and can help indetify product
	unsigned int price = 0;
	int stock = 0;//unsigned because price shouldn't be less than 0

	InventoryItem(std::string name,unsigned int sku,std::string description,unsigned int price = 0,int stock = 0)
	{
		this->name = name;
		this->SKU = sku;
		this->description = description;
		this->price = price;
		this->stock = stock;
	}
	InventoryItem(){}

	bool operator<( const InventoryItem other) const
	{
		return SKU < other.SKU;
	
	}
};

