package cn.edu.aust.judger.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 程序测试点的Model.
 * 对应数据库中的voj_problem_checkpoints数据表.
 * 
 * @author Haozhe Xie
 */
@Data
public class Checkpoint implements Serializable {
	/**
	 * CheckPoint的默认构造函数.
	 */
	public Checkpoint() { }
	
	/**
	 * CheckPoint的构造函数.
	 * @param problemId - 试题的唯一标识符
	 * @param checkpointId - 测试点的唯一标识符
	 * @param isExactlyMatch - 是否精确匹配测试点
	 * @param score - 测试点的分值
	 * @param input - 测试点的标准输入
	 * @param output - 测试点的标准输出
	 */
	public Checkpoint(long problemId, int checkpointId, boolean isExactlyMatch, int score, String input, String output) { 
		this.problemId = problemId;
		this.checkpointId = checkpointId;
		this.isExactlyMatch = isExactlyMatch;
		this.score = score;
		this.input = input;
		this.output = output;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("CheckPoint [ProblemId=%d, CheckpointId=%d, Score=%d]",
				problemId, checkpointId, score);
	}

	/**
	 * 试题的唯一标识符.
	 */
	private long problemId;
	
	/**
	 * 测试点的唯一标识符.
	 */
	private int checkpointId;
	
	/**
	 * 是否精确匹配测试点.
	 */
	private boolean isExactlyMatch;
	
	/**
	 * 测试点分值.
	 */
	private int score;
	
	/**
	 * 测试点的标准输入.
	 */
	private String input;
	
	/**
	 * 测试点的标准输出.
	 */
	private String output;
	
	/**
	 * 唯一的序列化标识符.
	 */
	private static final long serialVersionUID = -8129670699537187948L;
}
