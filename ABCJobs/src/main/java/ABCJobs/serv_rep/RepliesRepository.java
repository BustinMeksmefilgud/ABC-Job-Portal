package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ABCJobs.bean.Replies;

@Repository
public interface RepliesRepository extends JpaRepository<Replies, Long>  {
	@Query(value="SELECT * FROM replies WHERE commentID LIKE :keyword",nativeQuery = true)
	public List<Replies> findCommentReplies(@Param("keyword") Long keyword);
	
	@Query(value="SELECT repliesID FROM replies WHERE commentID LIKE :keyword",nativeQuery = true)
	public Long[] findRid(@Param("keyword") Long keyword);
}
