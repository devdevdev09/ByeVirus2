package heo.dae.byevirus2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import heo.dae.byevirus2.vo.Item;
import heo.dae.byevirus2.vo.Response;

@Service
public class XmlService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public Response parser(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        Response response = null;
        try {
            response = xmlMapper.readValue(xml, Response.class);
        } catch (JsonMappingException e) {
            logger.error(e.toString());
        } catch (JsonProcessingException e) {
            logger.error(e.toString());
        }
        return response;
    }

    public String getSlackMsg(Response response){
        String msg = "";
        
        try{
            if(response.getBody().items.size() < 2){
                throw new Exception("no update");
            }

            Item item1 = response.getBody().items.get(0);
            Item item2 = response.getBody().items.get(1);

            int plusCnt = item1.decideCnt - item2.decideCnt;
            String date = item1.createDt;
            double percent = Math.round(item1.accDefRate*100)/100.0;

            msg = "[" + date + "] : 전체 확진 : " + item1.decideCnt 
            + ", 격리 해제 : " + item1.clearCnt + ", 사망 :" + item1.deathCnt 
            + ", 증감 : " + plusCnt + ", 검사 진행 : " + item1.examCnt 
            + ", 확진률 : " + percent;
        }catch(Exception e){
            logger.info("slack error");
        }
        
        return msg;
    }

}
