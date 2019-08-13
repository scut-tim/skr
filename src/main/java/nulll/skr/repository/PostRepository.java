package nulll.skr.repository;


import nulll.skr.pojo.Post;
import nulll.skr.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>, JpaSpecificationExecutor<Post> {

    public Set<Post> findAllByAuthor(User user);

    public List<Post> findByTitleLike(String keyWord);

    public List<Post> findByContentLike(String keyWord);

    public List<Post> findByAuthor_UserNameLike(String keyWord);



}
