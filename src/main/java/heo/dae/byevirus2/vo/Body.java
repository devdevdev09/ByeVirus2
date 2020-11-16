package heo.dae.byevirus2.vo;

import java.util.List;

import lombok.Data;

@Data
public class Body {
    public List<Item> items;
    public int numOfRows;
    public int pageNo;
    public int totalCount;
}
