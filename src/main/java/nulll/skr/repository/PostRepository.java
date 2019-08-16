package nulll.skr.repository;


import nulll.skr.pojo.Post;
import nulll.skr.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>, JpaSpecificationExecutor<Post> {

    public Set<Post> findByAuthor_Id(int id);

    public Page<Post> findAllByTitleOrContentContaining(String titleKeyword, String contentKeyword, Pageable pageable);




}
