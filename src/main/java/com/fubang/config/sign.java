package com.fubang.config;

import java.math.BigDecimal;

public class sign {

    public static void main(String[] args) {
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

        BigDecimal bigDecimal1 = new BigDecimal("2.925456").setScale(0, BigDecimal.ROUND_DOWN);
        System.out.println(bigDecimal1.toString());

        //pc.verifyUrl(null, null, null, null);

String x  ="";
        System.out.println(x);


    }
}
