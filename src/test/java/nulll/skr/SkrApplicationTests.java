package nulll.skr;

import nulll.skr.repository.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkrApplicationTests {


    @Autowired
    private PostRepository postRepository;



    @Test
    @Transactional
    public void contextLoads() {



    }

}
