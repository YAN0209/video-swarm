package pers.yan.video.admin;

import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.model.ApiConfig;
import org.junit.Test;

/**
 * @author likaiyan
 * @date 2020/8/27 6:16 下午
 */
public class GenerateDoc {

    @Test
    public void test(){
        ApiConfig config = new ApiConfig();
        //当把AllInOne设置为true时，Smart-doc将会把所有接口生成到一个Markdown、HHTML或者AsciiDoc中
        config.setAllInOne(true);
        //Set the api document output path.
        config.setOutPath("/Users/likaiyan/IdeaProjects/video-swarm/");
        //生成Markdown文件
        //since 1.8.1版本开始入口方法有变更
        ApiDocBuilder.buildApiDoc(config);
    }
}
