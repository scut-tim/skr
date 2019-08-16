package nulll.skr.controller;

import nulll.skr.pojo.Post;
import nulll.skr.pojo.User;

import java.util.*;


public class recommend {
    //计算用户相似度
    public double similarDistance(User user1, User user2){
        int sim = 0;
        for (Post post: user1.getPostsOfLike()
        ) {
            if(user2.getPostsOfLike().contains(post))
                sim++;
        }
        double sq = Math.sqrt(user1.getPostsOfLike().size() * user2.getPostsOfLike().size());
        return (double) sim/sq;
    }

    //推荐相似度最高的用户的喜爱的帖子...有点水...
    public Set<Post> matchAndRecommend(User user, List<User> list){
        double simLimit = 0.5;
        User re = null;
        for (User temp:list
             ) {
            if(similarDistance(user,temp) > simLimit) {
                re = temp;
                simLimit = similarDistance(user,temp);
            }
        }
        return re.getPostsOfLike();
    }
}
