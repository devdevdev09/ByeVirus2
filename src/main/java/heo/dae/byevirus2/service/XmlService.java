package heo.dae.byevirus2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.stereotype.Service;

import heo.dae.byevirus2.vo.Response;

@Service
public class XmlService {
    public Response parser(String xml) {
        ObjectMapper xmlMapper = new XmlMapper();
        Response response = null;
        try {
            response = xmlMapper.readValue("<response><header><resultCode>00</resultCode><resultMsg>NORMAL SERVICE.</resultMsg></header><body><items><item><accDefRate>3.9193080566</accDefRate><accExamCnt>210144</accExamCnt><accExamCompCnt>191692</accExamCompCnt><careCnt>7212</careCnt><clearCnt>247</clearCnt><createDt>2020-03-10 10:20:27.27</createDt><deathCnt>54</deathCnt><decideCnt>7513</decideCnt><examCnt>18452</examCnt><resutlNegCnt>184179</resutlNegCnt><seq>69</seq><stateDt>20200310</stateDt><stateTime>00:00</stateTime><updateDt></updateDt></item></items><numOfRows>10</numOfRows><pageNo>1</pageNo><totalCount>1</totalCount></body></response>", Response.class);
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return response;
    }
}
