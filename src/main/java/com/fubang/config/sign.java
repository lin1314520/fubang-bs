package com.fubang.config;

import com.fubang.util.WordUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class sign {

    public static void main(String[] args) throws IOException {
//        System.out.println("{\"name\":\"zhangsan\",\"password\":\"123123\"}");
        // https://lbs.amap.com/api/webservice/guide/api/georegeo 逆地址解析
        // amap_api 注册高德地图开发者，创建应用，获取apikey
        //测试key:8f21643950153e066e4bfefc3d244e19

//        String amap_api_key = "8e88075893dfe412e1063501747013f7";
//        String url = "https://restapi.amap.com/v3/geocode/regeo?key="+amap_api_key+"&"+"location="+"113.01905,24.8614";
//        String jsonData =  HttpUtil.get(url);
//        JSONObject jsonLocation = JSON.parseObject(jsonData);
//        String city = "";
//        if("1".equals(jsonLocation.getString("status"))){
//            JSONObject addressComponent =jsonLocation.getJSONObject("regeocode").getJSONObject("addressComponent");
//            Object obj = null;
//            // 如果是非直辖市，city返回数据
//            if((obj = addressComponent.get("city")) instanceof String){
//                city=  (String)obj;
//            }else if ((obj = addressComponent.get("province")) instanceof String){
//                // 如果是直辖市，通过province返回数据
//                city= (String)obj;
//            }
//            city = city.replace("市","");
//        }
//        System.out.println(city);
//        QrConfig config = new QrConfig();
//        // 生成指定url对应的二维码到文件，宽和高都是300像素
//        config.setErrorCorrection(ErrorCorrectionLevel.H);
//        BufferedImage bufferedImage = QrCodeUtil.generate("3333333", config);
//        //创建一个ByteArrayOutputStream
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        //把BufferedImage写入ByteArrayOutputStream
//        ImageIO.write(bufferedImage, "jpg", os);
//        //ByteArrayOutputStream转成InputStream
//        InputStream input = new ByteArrayInputStream(os.toByteArray());
//        //InputStream转成MultipartFile
//        MultipartFile multipartFile =new MockMultipartFile("file", "file.jpg", "text/plain", input);
//
//        BigDecimal bigDecimal1 = new BigDecimal("2.925456").setScale(0, BigDecimal.ROUND_DOWN);
//        System.out.println(bigDecimal1.toString());
//
//        //pc.verifyUrl(null, null, null, null);
//
//String x  ="";
//        System.out.println(x);
        //测试word
//        String template = "C:\\Users\\g1121\\Desktop\\导出Word表格样式.docx";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("${gradeS}", "乌龟");
//        paramMap.put("${gradeE}", "男");
//        paramMap.put("${studentName}", "1998年10月22日");
//        paramMap.put("${studentNumber}", "汉族");
//        paramMap.put("${studentSex}", "广东深圳");
//        paramMap.put("${major}", "广东汕头");
//        paramMap.put("${grade}", "团员");
//        paramMap.put("${studentClass}", "2020年3月16日");
//        paramMap.put("${resultClass0}", "良好");
//        paramMap.put("${resultClass1}", "软件开发1");
//        paramMap.put("${resultClass2}", "Java开发2");
//        paramMap.put("${resultClass3}", "Java开发3");
//        paramMap.put("${resultName0}", "Java开发4");
//        paramMap.put("${resultName1}", "Java开发5");
//        paramMap.put("${resultName2}", "Java开发6");
//        paramMap.put("${resultName3}", "Java开发7");
//        paramMap.put("${resultTime0}", "Java开发8");
//        paramMap.put("${resultTime1}", "Java开发9");
//        paramMap.put("${resultTime2}", "Java开发0");
//        paramMap.put("${resultTime3}", "Java开发11");
//        paramMap.put("${rank0}", "Java开发111");
//        paramMap.put("${rank1}", "Java开发1111");
//        paramMap.put("${rank2}", "Java开发11111");
//        paramMap.put("${rank3}", "Java开发222");
//        paramMap.put("${score0}", "Java开发22");
//        paramMap.put("${score1}", "Java开发333");
//        paramMap.put("${score2}", "Java开发444");
//        paramMap.put("${score3}", "Java开发55");
//        paramMap.put("${scoreAll}", "Java开发555");
//
//        // 照片路径以及大小
////        Map<String, Object> phomap = new HashMap<>(8);
////        phomap.put("width", 100);
////        phomap.put("height", 130);
////        phomap.put("type", "png");
////        phomap.put("content", "E:\\Photo\\头像.jpg");
////        paramMap.put("${upho}", phomap);
//////
////        //查询员工家庭信息
////        List<EpPmenber> menberlist = new ArrayList<>();
////        for (int i = 1; i < 3; i++) {
////            EpPmenber pmenber = new EpPmenber();
////            pmenber.setUname("小王");
////            pmenber.setUconnection("父亲");
////            pmenber.setUbirthday("1962年10月2日");
////            pmenber.setUploity("群众");
////            pmenber.setUworkunit("广东xxx公司");
////            pmenber.setUstatus("无");
////            menberlist.add(pmenber);
////        }
////        paramMap.put("menberlist", menberlist);
////
////        //查询员工奖励情况
////        List<EpRewandpun> andpunlist = new ArrayList<>();
////        for (int i = 1; i < 3; i++) {
////            EpRewandpun rewandpun = new EpRewandpun();
////            rewandpun.setUrewdate("2020年5月1日");
////            rewandpun.setUrewunit("深圳xxx有限公司");
////            rewandpun.setUrewdesc("无");
////            andpunlist.add(rewandpun);
////        }
////        paramMap.put("andpunlist", andpunlist);
//
//        // 模板填充
//        XWPFDocument doc = WordUtil.generateWord(paramMap, template);
//        FileOutputStream fopts = new FileOutputStream("C:\\Users\\g1121\\Desktop\\导出Word表格样式2.docx");
//        doc.write(fopts);
//        fopts.close();
//

    }
}
