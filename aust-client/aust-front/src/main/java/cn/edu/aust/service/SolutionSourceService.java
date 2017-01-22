package cn.edu.aust.service;


/**
 * @author Niu Li
 * @date 2016/9/6
 */
public interface SolutionSourceService {
    int deleteByPrimaryKey(Integer id);

    int insert(SolutionSource record);

    int insertSelective(SolutionSource record);

    SolutionSource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SolutionSource record);

    int updateByPrimaryKeyWithBLOBs(SolutionSource record);

    int updateByPrimaryKey(SolutionSource record);
}
