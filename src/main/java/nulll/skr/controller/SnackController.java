package nulll.skr.controller;

import nulll.skr.pojo.Snack;
import nulll.skr.repository.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class SnackController {
    @Autowired
    private SnackRepository snackRepository;

    public Snack[] billboard(){
        List<Snack> snackList = snackRepository.findAllByOrderByLikeNumDesc();
        Snack[] array = snackList.toArray(new Snack[snackList.size()]);
        return array;
    }

    @GetMapping("/snacks/{pageNum}")
    public List<Snack> listSnacks(@PathVariable(name="pageNum")int pageNum){
        Pageable pageable = new PageRequest(pageNum,3, new Sort(Sort.Direction.DESC, "likeNum"));
        Page<Snack> snackPage = snackRepository.findAll(pageable);
        List<Snack> snackList = snackPage.getContent();
        System.out.println(snackList);
        return snackList;
    }
}
