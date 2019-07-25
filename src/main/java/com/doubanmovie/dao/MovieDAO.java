package com.doubanmovie.dao;

import com.doubanmovie.pojo.MovieDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author leo-bin
 * @date 2019/7/24
 * @apiNote 电影持久层接口
 */
public interface MovieDAO {

    /**
     * 爬取电影
     * @param movieDO
     * @return
     */
    int insert(MovieDO movieDO);

    /**
     * 查询所有电影数据
     * @return
     */
    List<MovieDO> getAll();

    /**
     * 根据电影标题查询电影
     * @param title
     * @return
     */
    List<MovieDO>  getMovieByTitle(String title);

    /**
     * 根据评分区间查询电影资源
     * @param start
     * @param end
     * @return
     */
    List<MovieDO>  getMoviesByRate(@Param("start") Integer start,@Param("end") Integer end);
}
