package nulll.skr.controller;

import nulll.skr.pojo.Snack;
import nulll.skr.repository.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
}
