package example.org.seasar.buri.dao;

import java.util.List;

import example.org.seasar.buri.dto.ItemDto;

public interface ItemDao {
    public Class BEAN = ItemDto.class;

    public List getAllItem();

    public String getItem_ARGS = "itemID";
    public ItemDto getItem(long itemID);

    public String getItemByIds_ARGS = "itemIDs";
    public String getItemByIds_QUERY = "itemID in /*itemIDs*/(1)";
    public List getItemByIds(List itemIDs);
    
    public void insert(ItemDto dto);
    
    public void update(ItemDto dto);
    
    public void delete(ItemDto dto);
}

