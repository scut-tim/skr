package nulll.skr.repository;


import nulll.skr.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {

    public List<Comment> findAllByPost_Id(int id);

}
