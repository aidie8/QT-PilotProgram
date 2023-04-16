#pragma once
#include <string>


class InventoryItem
{
	std::string name; // display name for the Item
	std::string SKU; // this is the stock keeping Unit that is used internally
	std::string description; // this describes the item, gives context and can help indetify product
	unsigned int price; //unsigned because price shouldn't be less than 0

	
};

