package cn.edu.aust.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * bootstrap table参数请求封装
 * @author Niu Li
 * @date 2017/1/29
 */
@Data
public class PageRequest implements Serializable{
    private static final long serialVersionUID = -3930180379790344299L;

    private Integer limit = 15;

    private Integer offset = 1;
    //页码针对自定义分页,框架则依据框架自己标准
    private Integer pageNum = 0;

    private String order = "desc";

    private String ordername = "id";

    /** 搜索属性 */
    private String search;

}
