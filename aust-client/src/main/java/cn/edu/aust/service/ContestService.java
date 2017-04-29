package cn.edu.aust.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import cn.edu.aust.common.constant.ContestStatus;
import cn.edu.aust.common.util.DateUtil;
import cn.edu.aust.convert.ContestConvert;
import cn.edu.aust.dto.ContestDTO;
import cn.edu.aust.mapper.ContestMapper;
import cn.edu.aust.pojo.entity.ContestDO;
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
  @Resource
  private StringRedisTemplate redisTemplate;
  @Resource
  private SolutionService solutionService;

  private static final String USER_VISITED = "contest.user:";
  /**
   * 查看一个竞赛
   * @param id 该竞赛id
   * @return 该竞赛
   */
  public ContestDTO findDetail(Long id){
    ContestDO contestDO = contestMapper.selectByPrimaryKey(id);
    checkArgument(contestDO != null, "该竞赛不存在");
    return ContestConvert.assemble(contestDO);
  }

  /**
   * 判断用户是否访问过该竞赛
   * @param contestId 竞赛id
   * @param userId 用户id
   * @return true访问过
   */
  public Boolean isVisited(Long contestId,Long userId){
    //先看redis中是否有记录
    Boolean member = redisTemplate.opsForSet().isMember(USER_VISITED + userId, contestId.toString());
    if (member) {
      return true;
    }
    //再看判题记录中是否有记录
    return solutionService.isJudgeContest(contestId, userId);
  }

  /**
   * 判断一个竞赛是否可以显示
   *
   * @param contestid     该竞赛id
   * @param passwd 密码(如果需要)
   * @return 查询结果 异常交给全局统一处理
   */
  public boolean canView (Long contestid,Long userId, String passwd) {
    //权限检查
    ContestDO contestDO = contestMapper.selectByPrimaryKey(contestid);
    checkArgument(contestDO != null, "该竞赛不存在");
    checkArgument(contestDO.getStatus() != ContestStatus.NORMAL.value,"该竞赛不存在");
    if (contestDO.getType() == 1) {
      checkArgument(StringUtils.equals(contestDO.getPassword(), passwd), "密码错误");
    }
    //时间检查
    checkArgument(DateUtil.isExpire(contestDO.getStartTime()),"比赛还未开始,禁止查看");

    //保存记录到redis
    redisTemplate.opsForSet().add(USER_VISITED+userId,contestid.toString());
    return true;
  }

  /**
   * 是否可以判题
   * @param contestDTO 竞赛实体
   * @return true可以
   */
  public boolean canJudger(ContestDTO contestDTO){
    if (Objects.isNull(contestDTO)) {
      return false;
    }
    //是否开始
    if (!DateUtil.isExpire(contestDTO.getStartTime())) {
      return false;
    }
    //是否过期
    if (DateUtil.isExpire(contestDTO.getEndTime())) {
      return false;
    }
    return true;
  }


  /**
   * 查询正在进行和已经失效的竞赛
   *
   * @return 结果集
   */
  public Map<String, List<ContestDTO>> queryAndKinds() {
    List<ContestDO> contestDOS = contestMapper.selectAll();
    //筛选出失效和未失效的
    List<ContestDTO> expire = Lists.newArrayList();
    List<ContestDTO> noExpire = Lists.newArrayList();
    //无效的比赛不展示
    contestDOS.stream().filter(contestDO -> contestDO.getStatus() == ContestStatus.NORMAL.value)
            .forEach(contestDO -> {
              //去除该阶段没必要显示的字段
              contestDO.setDescription(null);
              if (DateUtil.isExpire(contestDO.getEndTime())) {
                expire.add(ContestConvert.assemble(contestDO));
              } else {
                noExpire.add(ContestConvert.assemble(contestDO));
              }
            });
    //返回结果
    Map<String, List<ContestDTO>> resultMap = Maps.newHashMap();
    resultMap.put("exprieContest", expire);
    resultMap.put("noExprieContest", noExpire);
    return resultMap;
  }

}
