package github.clyoudu.model.pojo;

import lombok.*;

/**
 * Created by IntelliJ IDEA
 *
 * @author chenlei
 * @date 2020/1/12
 * @time 13:38
 * @desc User
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String email;

}
