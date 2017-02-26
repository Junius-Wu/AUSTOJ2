package cn.edu.aust.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.edu.aust.assemble.ContestAssemble;
import cn.edu.aust.dto.ContestDTO;
import cn.edu.aust.mapper.ContestMapper;
import cn.edu.aust.pojo.entity.Contest;
import lombok.extern.slf4j.Slf4j;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * 竞赛相关服务
 *
 * @author Niu Li
 * @since 2017/2/26
 */
@Service
@Slf4j
public class ContestService {
  @Resource
  private ContestMapper contestMapper;

  /**
   * 查看一个竞赛
   * @param id 该竞赛id
   * @return 该竞赛
   */
  public ContestDTO findDetail(Long id){
    Contest contest = contestMapper.selectByPrimaryKey(id);
    checkArgument(contest != null, "该竞赛不存在");
    // todo 该不在在service检查状态?还是应该在controller检查?
    checkArgument(contest.getDefunct() == 1,"该竞赛不存在");
    return ContestAssemble.assemble(contest);
  }

  /**
   * 判断一个竞赛是否可以显示
   *
   * @param id     该竞赛id
   * @param passwd 密码(如果需要)
   * @return 查询结果 异常交给全局统一处理
   */
  public boolean canView (Long id, String passwd) {
    //权限检查
    Contest contest = contestMapper.selectByPrimaryKey(id);
    checkArgument(contest != null, "该竞赛不存在");
    checkArgument(contest.getDefunct() == 1,"该竞赛不存在");
    if (contest.getType() == 1) {
      checkArgument(StringUtils.equals(contest.getPassword(), passwd), "密码错误");
    }
    //时间检查
    checkArgument(isStart(contest),"比赛还未开始,禁止查看");
    return true;
  }

  /**
   * 查询正在进行和已经失效的竞赛
   *
   * @return 结果集
   */
  public Map<String, List<ContestDTO>> queryAndKinds() {
    List<Contest> contests = contestMapper.selectAll();
    //筛选出失效和未失效的
    List<ContestDTO> expire = Lists.newArrayList();
    List<ContestDTO> noExpire = Lists.newArrayList();
    //无效的比赛不展示
    contests.stream().filter(contest -> contest.getDefunct() != 0)
            .forEach(contest -> {
              //去除该阶段没必要显示的字段
              contest.setDescription(null);
              if (isExpire(contest)) {
                expire.add(ContestAssemble.assemble(contest));
              } else {
                noExpire.add(ContestAssemble.assemble(contest));
              }
            });
    //返回结果
    Map<String, List<ContestDTO>> resultMap = Maps.newHashMap();
    resultMap.put("exprieContest", expire);
    resultMap.put("noExprieContest", noExpire);
    return resultMap;
  }

  /**
   * 判断一个比赛是否开始
   *
   * @param contest 该比赛
   * @return true已经开始
   */
  private boolean isStart(Contest contest) {
    Date now = new Date();
    if (contest.getStartTime().before(now)){
      return true;
    }
    return false;
  }

  /**
   * 判断一个竞赛是否过期
   *
   * @param contest 该竞赛
   * @return true过期
   */
  private boolean isExpire(Contest contest) {
    //时间
    Date now = new Date();
    if (contest.getEndTime().before(now)) {
      return true;
    }
    return false;
  }
}
