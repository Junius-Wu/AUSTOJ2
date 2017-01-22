package cn.mrdear;

import org.junit.Test;

import java.util.Optional;

/**
 * @author Niu Li
 * @date 2016/10/14
 */
public class OptionalTest {

    @Test
    public void testOptionalTest() {

        User user = new User();
//        user.setNickname("hahaha");
        user.setPassword("1234");
        Optional.ofNullable(user)
                .filter(user1 -> {
                    return user1.getNickname() != null;
                })
                .map(user1 -> {
                    user1.setPassword(null);
                    return user1;
                })
                .orElse(new User());

        System.out.println(user.toString());

    }

}
