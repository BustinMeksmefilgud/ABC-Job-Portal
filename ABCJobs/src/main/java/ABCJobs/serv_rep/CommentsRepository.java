package ABCJobs.serv_rep;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ABCJobs.bean.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
	@Query(value="SELECT * FROM comments WHERE threadsID LIKE :keyword",nativeQuery = true)
	public List<Comments> findThreadComments(@Param("keyword") Long keyword);
	
	@Query(value="SELECT commentID FROM comments WHERE threadsID LIKE :keyword",nativeQuery = true)
	public Long[] findCid(@Param("keyword") Long keyword);
}
