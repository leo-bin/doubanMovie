package com.doubanmovie;

import com.doubanmovie.service.MovieCrawlingService;

/**
 * @author leo-bin
 * @date 2019/7/23
 * @apiNote 豆瓣电影爬取main 技术栈：maven+mybatis
 */
public class main {

    /**
     * 启动入口
     * @param args
     */
    public static void main(String[] args) {
        MovieCrawlingService movieCrawlingService = new MovieCrawlingService();
        movieCrawlingService.login();
    }
}
