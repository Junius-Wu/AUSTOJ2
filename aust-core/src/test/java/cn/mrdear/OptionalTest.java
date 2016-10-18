package cn.mrdear;

import java.util.Optional;

/**
 * @author Niu Li
 * @date 2016/10/14
 */
public class OptionalTest {

    public void testOptionalTest(Optional<String> str){
        str.orElse("no str");
    }

}
