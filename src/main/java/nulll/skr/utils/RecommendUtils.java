package nulll.skr.utils;

import nulll.skr.pojo.Post;
import nulll.skr.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class RecommendUtils {

    //计算用户相似度
    public double similarDistance(User user1, User user2){
        int similarity = 0;
        for (Post post: user1.getPostsOfLike()) {
            if(user2.getPostsOfLike().contains(post))
                similarity++;
        }
        double sq = Math.sqrt(user1.getPostsOfLike().size() * user2.getPostsOfLike().size());
        return (double) similarity/sq;
    }

    //推荐相似度最高的用户的喜爱的帖子...有点水...
    public Set<Post> matchAndRecommend(User user, List<User> userList){
        double similarityLimit = 0.5;
        User user1 = null;
        double similarity = 0;
        for (User temp: userList) {
            similarity = similarDistance(user,temp);
            if(similarity > similarityLimit) {
                user1 = temp;
                similarityLimit = similarity;
            }
        }
        return user1.getPostsOfLike();
    }
}
