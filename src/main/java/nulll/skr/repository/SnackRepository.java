package nulll.skr.repository;

import nulll.skr.pojo.Snack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SnackRepository extends JpaRepository<Snack,Integer> {
    public List<Snack> findAllByOrderByLikeNumDesc();
}
