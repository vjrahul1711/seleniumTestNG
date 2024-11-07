package DemoQACommonFiles;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import pojo.customerDetailsPojo;
import pojo.customerDetailsReadValuePojo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class JsonToJava {
    //public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

    @Test
            public void testCaseDBCOnnection() throws ClassNotFoundException, SQLException, IOException{
        ArrayList<customerDetailsPojo> firstArrayList = new ArrayList<customerDetailsPojo>();
        JSONArray jsonArreyObj = new JSONArray();

        ObjectMapper objectMapperObj = new ObjectMapper();

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = null;
        conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Business","vijay","Password@123");
        Statement st = conn.createStatement();
        ResultSet result= st.executeQuery("select * from CustomerInfo where purchasedDate='2024-09-19' and  Location ='Asia';");
        while (result.next()){
            customerDetailsPojo customerDetailsObj = new customerDetailsPojo();

            customerDetailsObj.setCourseName(result.getString(1));
            customerDetailsObj.setPurchasedDate(result.getString(2));
            customerDetailsObj.setAmount(result.getInt(3));
            customerDetailsObj.setLocation(result.getString(4));
            firstArrayList.add(customerDetailsObj);
        }
        for (int i=0;i<firstArrayList.size();i++){

            objectMapperObj.writeValue(new File("D:\\seleniumTestNGJava\\src\\main\\resources\\customerDetails"+i+".json"),firstArrayList.get(i));

            Gson g = new Gson();
            String jsonString = g.toJson(firstArrayList.get(i));
            jsonArreyObj.add(jsonString);
        }
        //JSON simple
        JSONObject jo = new JSONObject();
        jo.put("data",jsonArreyObj);
        System.out.println(jo.toJSONString());
        String Unescaped1= StringEscapeUtils.unescapeJava(jo.toJSONString());
        System.out.println(Unescaped1);
        String Unescaped2= Unescaped1.replace("\"{","{");
        String finalString = Unescaped2.replace("}\"","}");
        System.out.println(finalString);

        FileWriter file = new FileWriter("D:\\seleniumTestNGJava\\src\\main\\resources\\customerDetailsFinalJson.json");
        file.write(finalString);
        file.close();

        //read values from a json file
        customerDetailsReadValuePojo readerObj = objectMapperObj.readValue(new File("D:\\seleniumTestNGJava\\src\\main\\resources\\customerDetailsReadValue.json"), customerDetailsReadValuePojo.class);
        String courseName = readerObj.getCourseName();
        System.out.println(courseName);

        conn.close();

    }
}
