package nulll.skr.repository;


import nulll.skr.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Integer > {

    public User findByUserName(String userName);

    public boolean findByPassword(String password);


}
