package cn.edu.aust.query;

import cn.edu.aust.pojo.entity.ProblemDO;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 题目的包装类
 * @author Niu Li
 * @date 2017/1/29
 */
@Data
@NoArgsConstructor
@ToString
public class ProblemPK extends ProblemDO {
    /**
     * 上传作者名,来源user表
     */
    private String auchor;
    /**
     * 所属目录
     */
    private String categlogName;
}
