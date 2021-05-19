package com.yunmai;

import com.fubang.util.WordUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class FuBangApplicationTests {

    @Test
    void contextLoads() throws IOException {
        String template = "C:\\Users\\g1121\\Desktop\\导出Word表格样式.docx";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("${gradeS}", "乌龟");
        paramMap.put("${gradeE}", "男");
        paramMap.put("${studentName}", "1998年10月22日");
        paramMap.put("${studentNumber}", "汉族");
        paramMap.put("${studentSex}", "广东深圳");
        paramMap.put("${major}", "广东汕头");
        paramMap.put("${grade}", "团员");
        paramMap.put("${studentClass}", "2020年3月16日");
        paramMap.put("${resultClass0}", "良好");
        paramMap.put("${resultClass1}", "软件开发1");
        paramMap.put("${resultClass2}", "Java开发2");
        paramMap.put("${resultClass3}", "Java开发3");
        paramMap.put("${resultName0}", "Java开发4");
        paramMap.put("${resultName1}", "Java开发5");
        paramMap.put("${resultName2}", "Java开发6");
        paramMap.put("${resultName3}", "Java开发7");
        paramMap.put("${resultTime0}", "Java开发8");
        paramMap.put("${resultTime1}", "Java开发9");
        paramMap.put("${resultTime2}", "Java开发0");
        paramMap.put("${resultTime3}", "Java开发11");
        paramMap.put("${rank0}", "Java开发111");
        paramMap.put("${rank1}", "Java开发1111");
        paramMap.put("${rank2}", "Java开发11111");
        paramMap.put("${rank3}", "Java开发222");
        paramMap.put("${score0}", "Java开发22");
        paramMap.put("${score1}", "Java开发333");
        paramMap.put("${score2}", "Java开发444");
        paramMap.put("${score3}", "Java开发55");
        paramMap.put("${scoreAll}", "Java开发555");

        // 照片路径以及大小
//        Map<String, Object> phomap = new HashMap<>(8);
//        phomap.put("width", 100);
//        phomap.put("height", 130);
//        phomap.put("type", "png");
//        phomap.put("content", "E:\\Photo\\头像.jpg");
//        paramMap.put("${upho}", phomap);
////
//        //查询员工家庭信息
//        List<EpPmenber> menberlist = new ArrayList<>();
//        for (int i = 1; i < 3; i++) {
//            EpPmenber pmenber = new EpPmenber();
//            pmenber.setUname("小王");
//            pmenber.setUconnection("父亲");
//            pmenber.setUbirthday("1962年10月2日");
//            pmenber.setUploity("群众");
//            pmenber.setUworkunit("广东xxx公司");
//            pmenber.setUstatus("无");
//            menberlist.add(pmenber);
//        }
//        paramMap.put("menberlist", menberlist);
//
//        //查询员工奖励情况
//        List<EpRewandpun> andpunlist = new ArrayList<>();
//        for (int i = 1; i < 3; i++) {
//            EpRewandpun rewandpun = new EpRewandpun();
//            rewandpun.setUrewdate("2020年5月1日");
//            rewandpun.setUrewunit("深圳xxx有限公司");
//            rewandpun.setUrewdesc("无");
//            andpunlist.add(rewandpun);
//        }
//        paramMap.put("andpunlist", andpunlist);

        // 模板填充
        XWPFDocument doc = WordUtil.generateWord(paramMap, template);
        FileOutputStream fopts = new FileOutputStream("C:\\Users\\g1121\\Desktop\\导出Word表格样式2.docx");
        doc.write(fopts);
        fopts.close();

    }


}
