package com.doubanmovie.pojo;

import lombok.Data;
/**
 * @author leo-bin
 * @date 2019/7/23
 * @apiNote 单部电影实体类
 */
@Data
public class MovieDO {

    /**
     * 单部电影唯一Id
     */
    private Integer id;

    /**
     * 导演
     */
    private String directors;

    /**
     * 主要演员卡司
     */
    private String casts;

    /**
     * 封面url
     */
    private String cover;

    /**
     * 封面x坐标
     */
    private Integer coverX;

    /**
     * 封面y坐标
     */
    private Integer coverY;

    /**
     * 豆瓣电影评分，10分制
     */
    private Double rate;

    /**
     * 豆瓣电影评分,50分制
     */
    private String star;

    /**
     * 电影标题
     */
    private String title;

    /**
     * 电影首页url
     */
    private String url;
}
