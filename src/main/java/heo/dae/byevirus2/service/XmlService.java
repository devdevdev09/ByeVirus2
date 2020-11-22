package heo.dae.byevirus2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.stereotype.Service;

import heo.dae.byevirus2.vo.Item;
import heo.dae.byevirus2.vo.Response;

@Service
public class XmlService {
    public Response parser(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        Response response = null;
        try {
            response = xmlMapper.readValue(xml, Response.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return response;
    }

    public String getSlackMsg(Response response){
        Item item1 = response.getBody().items.get(0);
        Item item2 = response.getBody().items.get(1);

        int plusCnt = item1.decideCnt - item2.decideCnt;
        String date = item1.createDt;
        double percent = Math.round(item1.accDefRate*100)/100.0;

        String msg = "[" + date + "] : 전체 확진 : " + item1.decideCnt 
        + ", 격리 해제 : " + item1.clearCnt + ", 사망 :" + item1.deathCnt 
        + ", 증감 : " + plusCnt + ", 검사 진행 : " + item1.examCnt 
        + ", 확진률 : " + percent;
        
        return msg;
    }

}
