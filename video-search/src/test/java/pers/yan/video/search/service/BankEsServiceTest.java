package pers.yan.video.search.service;

import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import pers.yan.video.search.SearchApplication;
import pers.yan.video.search.pojo.entity.BankEs;

/**
 * @author likaiyan
 * @date 2020/9/30 4:37 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SearchApplication.class)
public class BankEsServiceTest {

    @Autowired
    private BankEsService bankEsService;

    @Test
    public void esTest(){

        Page<BankEs> result = bankEsService.search("Gatling", 0, 30);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        System.out.println(builder.create().toJson(result));
    }

}
