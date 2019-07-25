package com.doubanmovie.service;

import com.doubanmovie.dao.MovieDAO;
import com.doubanmovie.pojo.MovieDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
import java.io.IOException;
import java.util.List;

/**
 * @author leo-bin
 * @date 2019/7/24
 * @apiNote 电影爬取服务层
 */
public class MovieCrawlingService {

    /**
     * 豆瓣电影分类区url
     */
    private static final String URL="https://movie.douban.com/j/new_search_subjects?sort=U&range=0,10&tags=&start=";

    /**
     * 模拟登陆
     * @return
     */
    public void login(){
        //创建http默认连接服务器
        HttpClient httpClient = HttpClientBuilder.create().build();
        Integer start;
        Integer end=9979;
         for (start=0;start<=end;start+=20){
            //对url进行get请求
            HttpGet httpGet=new HttpGet(URL+start);
            //HttpGet httpGet=new HttpGet(URL);
            //创建http响应体,用来接受get请求返回的数据
            HttpResponse httpResponse;
            try {
                //执行get请求,并接收
                httpResponse = httpClient.execute(httpGet);
                //对得到的响应体进行解析，得到具体的实体数据
                HttpEntity en = httpResponse.getEntity();
                decodeMovie(en,start);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 对得到的实体进行进一步的的解析并写入数据库
     */
    public void decodeMovie(HttpEntity en,Integer start) {
        //定义配置文件路径
        String resource = "mybatis-config.xml";
        Integer end=9979;
        InputStream inputStream = null;
        try {
            //读取配置文件
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //注册mybatis 工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //得到连接对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //从mybatis中得到dao对象
        MovieDAO moviedao = sqlSession.getMapper(MovieDAO.class);

        String con = null;
        try {
            //自动关闭结果集,并将实体转换为字符串类型
            con = EntityUtils.toString(en, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isNotBlank(con)){
            //将字符串解析成json对象
            JSONObject jo =JSONObject.parseObject(con);
            //将json对象解析成json对象数组
            JSONArray json = jo.getJSONArray("data");
            //将json对象数组解析成list对象列表
            List<MovieDO> movieDOS = JSON.parseArray(json.toString(), MovieDO.class);
            for (MovieDO movieDO:movieDOS){
                int i=moviedao.insert(movieDO);
                //提交到数据库
                sqlSession.commit();
                if (i>=1){
                    System.out.println(movieDO);
                }
                else{
                    System.out.println("插入失败");
                }
            }
        }
        if (start<=end){
            System.out.println("当前页已经到底了，请刷新一下。正在帮你刷新中。。。");
            sqlSession.close();
        }
    }
}
